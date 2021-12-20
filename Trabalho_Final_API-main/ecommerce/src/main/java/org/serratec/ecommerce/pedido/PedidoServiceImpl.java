package org.serratec.ecommerce.pedido;

import org.modelmapper.ModelMapper;
import org.serratec.ecommerce.cliente.Cliente;
import org.serratec.ecommerce.cliente.ClienteRepository;
import org.serratec.ecommerce.cliente.ClienteService;
import org.serratec.ecommerce.cliente.dto.ClienteEmailDTO;
import org.serratec.ecommerce.cliente.dto.ClienteRespDTO;
import org.serratec.ecommerce.email.MailConfigCadastro;
import org.serratec.ecommerce.email.StatusDeEnvio;
import org.serratec.ecommerce.exception.ErroResposta;
import org.serratec.ecommerce.exception.GeneralException;
import org.serratec.ecommerce.pedido.dto.PedidoInserirDTO;
import org.serratec.ecommerce.pedido.dto.PedidoRespDTO;
import org.serratec.ecommerce.pedidoItem.PedidoItem;
import org.serratec.ecommerce.pedidoItem.PedidoItemID;
import org.serratec.ecommerce.pedidoItem.PedidoItemRepository;
import org.serratec.ecommerce.pedidoItem.dto.PedidoItemRequestDTO;
import org.serratec.ecommerce.produto.Produto;
import org.serratec.ecommerce.produto.ProdutoRepository;
import org.serratec.ecommerce.utils.Existencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static org.serratec.ecommerce.utils.Mensagens.Exceptions.*;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MailConfigCadastro mailConfigCadastro;

    @Override
    public ResponseEntity<Set<PedidoRespDTO>> pesquisar() {
        List<Pedido> pedidos = this.pedidoRepository.findAll();
        return Optional.of(PedidoRespDTO.toDTO(pedidos))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<PedidoRespDTO> pesquisar(Long id) {
        Optional<PedidoRespDTO> pedidoDTO = Optional.of(new PedidoRespDTO(this.pedidoRepository.findById(id).get()));
        return pedidoDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    @Override
    public ResponseEntity<PedidoRespDTO> adicionarPedido(Long idCliente, PedidoInserirDTO pedidoInserirDTO) {
        Existencia.existeID(idCliente, this.clienteRepository, HttpStatus.NOT_FOUND, INSERCAO_FALHOU, PESQUISA_CLIENTE_FALHOU);


        Pedido pedido = new Pedido();
        this.pedidoRepository.save(pedido);

        pedido.setPedidoItens(carregarItens(pedido, pedidoInserirDTO, false));
        pedido.setStatusPedido(pedidoInserirDTO.getStatusPedido());
        pedido.setCliente(this.clienteRepository.findById(idCliente).get());

        try {
            this.pedidoRepository.save(pedido);
        }catch (IllegalArgumentException ex){
            throw new GeneralException(new ErroResposta(
                    HttpStatus.BAD_REQUEST,
                    INSERCAO_PEDIDO_FALHOU,
                    Arrays.asList(SALVAR_FALHOU + ex.getMessage())));
        } catch (IllegalStateException ex){
            throw new GeneralException(new ErroResposta(
                    HttpStatus.BAD_REQUEST,
                    INSERCAO_PEDIDO_FALHOU,
                    Arrays.asList(ITEM_DUPLICADO_FALHOU + ex.getMessage())));
        }

        Optional<PedidoRespDTO> pedidoRespDTO = Optional.of(new PedidoRespDTO(pedido));

        enviarEmailPedido(pedidoRespDTO.get(), "PEDIDO REALIZADO COM SUCESSO.");

        return pedidoRespDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    @Override
    public ResponseEntity<PedidoRespDTO> editarPedido(Long idPedido, PedidoInserirDTO pedidoInserirDTO) {
        Existencia.existeID(idPedido, this.pedidoRepository, HttpStatus.NOT_FOUND, INSERCAO_FALHOU, PESQUISA_PEDIDO_FALHOU);

        Pedido pedidoEditado = this.pedidoRepository.findById(idPedido).get();

        pedidoEditado.setPedidoItens(carregarItens(pedidoEditado, pedidoInserirDTO, true));
        pedidoEditado.setStatusPedido(pedidoInserirDTO.getStatusPedido());
        pedidoEditado.setCliente(pedidoEditado.getCliente());

        this.pedidoRepository.save(pedidoEditado);

        Optional<PedidoRespDTO> pedidoRespDTO = Optional.of(new PedidoRespDTO(pedidoEditado));

        enviarEmailPedido(pedidoRespDTO.get(), "PEDIDO EDITADO COM SUCESSO.");

        return pedidoRespDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private Set<PedidoItem> carregarItens(Pedido pedido, PedidoInserirDTO pedidoInserirDTO, boolean editando) {

        Set<PedidoItem> pedidoItens = new HashSet<>();

        for (PedidoItemRequestDTO pedidoItemRequestDTO: pedidoInserirDTO.getItens()) {

            Existencia.existeID(pedidoItemRequestDTO.getPedidoItemID().getProdutoID(),
                    this.produtoRepository,
                    HttpStatus.NOT_FOUND,
                    INSERCAO_FALHOU,
                    PESQUISA_PRODUTO_FALHOU);


            Produto produto = this.produtoRepository.findById(pedidoItemRequestDTO.getPedidoItemID().getProdutoID()).get();

            // Verifica Estoque
            checkEstoque(pedidoItemRequestDTO.getQuantidade(), produto.getQuantidadeEstoque());

            PedidoItem pedidoItem = new PedidoItem();
            pedidoItem.setPedido(pedido);
            pedidoItem.setProduto(produto);
            pedidoItem.setQuantidade(pedidoItemRequestDTO.getQuantidade());

            PedidoItemID pedidoItemID = new PedidoItemID();
            pedidoItemID.setPedidoID(pedidoItem.getPedido().getId());
            pedidoItemID.setProdutoID(pedidoItem.getProduto().getId());

            pedidoItem.setId(pedidoItemID);

            if(!editando && this.pedidoItemRepository.existsById(pedidoItemID)){
                throw new GeneralException(new ErroResposta(HttpStatus.NOT_FOUND,
                        INSERCAO_PEDIDO_FALHOU,
                        Arrays.asList(INSERCAO_PRODUTO_EXISTENTE_FALHOU + pedidoItemRequestDTO.getPedidoItemID().getProdutoID())));
            }

            pedidoItens.add(pedidoItem);
        }

        return pedidoItens;
    }

    private void checkEstoque(int solicitado, int estoque){
        if (estoque < solicitado){
            throw new GeneralException(new ErroResposta(HttpStatus.NOT_FOUND,
                    INSERCAO_PEDIDO_FALHOU,
                    Arrays.asList(ESTOQUE_INFERIOR + estoque)));
        }
    }

    private StatusDeEnvio enviarEmailPedido(PedidoRespDTO pedidoRespDTO, String assunto) throws GeneralException {

        Optional<Cliente> clienteDB = clienteRepository.findByEmail(pedidoRespDTO.getEmailCliente());

        // Cliente não Existe!
        if (!clienteDB.isPresent()){
            throw new GeneralException(new ErroResposta(
                    HttpStatus.BAD_REQUEST,
                    INSERCAO_FALHOU,
                    Arrays.asList(INSERCAO_EMAIL_EXISTENTE_FALHOU + pedidoRespDTO.getEmailCliente())));
        }

        // Cliente existe!
        try {
            ClienteEmailDTO emailDTO = new ClienteEmailDTO(modelMapper.map(clienteDB.get(), ClienteRespDTO.class), pedidoRespDTO);
            return mailConfigCadastro.enviarEmail(
                    clienteDB.get().getEmail(),
                    assunto,
                    emailDTO.toStringPedido(assunto.toUpperCase()));

        } catch (IllegalArgumentException ex) {
            throw new GeneralException(new ErroResposta(
                    HttpStatus.BAD_REQUEST,
                    ENVIO_EMAIL_FALHOU,
                    Arrays.asList(ERRO_EMAIL + pedidoRespDTO.getEmailCliente())));
        }
    }

    @Override
    public ResponseEntity<Pedido> remover(Long id) throws GeneralException {
        if (!this.pedidoRepository.existsById(id)) {
            throw new GeneralException(new ErroResposta(HttpStatus.NOT_FOUND,
                    EXCLUSAO_PEDIDO_FALHOU,
                    Arrays.asList(PESQUISA_FALHOU + id)));
        }

        //Pedido pedido = this.pedidoRepository.getById(id);
        this.pedidoRepository.deleteById(id);
        //return ResponseEntity.ok(pedido);  // With pedido response
        return ResponseEntity.noContent().build();
    }
}