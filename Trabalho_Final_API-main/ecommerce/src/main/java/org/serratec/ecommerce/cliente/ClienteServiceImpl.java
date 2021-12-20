package org.serratec.ecommerce.cliente;

import org.modelmapper.ModelMapper;
import org.serratec.ecommerce.cliente.dto.ClienteRequestDTO;
import org.serratec.ecommerce.cliente.dto.ClienteRespDTO;
import org.serratec.ecommerce.cliente.dto.ClienteEmailDTO;
import org.serratec.ecommerce.email.MailConfigCadastro;
import org.serratec.ecommerce.email.StatusDeEnvio;
import org.serratec.ecommerce.endereco.EnderecoService;
import org.serratec.ecommerce.exception.ErroResposta;
import org.serratec.ecommerce.exception.GeneralException;
import org.serratec.ecommerce.utils.Existencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.serratec.ecommerce.utils.Mensagens.Exceptions.*;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private MailConfigCadastro mailConfigCadastro;

    @Autowired
    private ModelMapper modelMapper;


    private StatusDeEnvio enviarEmailCriacao(ClienteRespDTO clienteRespDTO, String assunto) throws GeneralException {

        Optional<Cliente> clienteDB = clienteRepository.findByEmail(clienteRespDTO.getEmail());

        // Cliente não Existe!
        if (!clienteDB.isPresent()){
            throw new GeneralException(new ErroResposta(
                    HttpStatus.BAD_REQUEST,
                    INSERCAO_FALHOU,
                    Arrays.asList(INSERCAO_EMAIL_EXISTENTE_FALHOU + clienteRespDTO.getEmail())));
        }

        // Cliente existe!
        try {
            ClienteEmailDTO emailDTO = new ClienteEmailDTO(clienteRespDTO);
            return mailConfigCadastro.enviarEmail(emailDTO.getEmail(),
                    assunto,
                    emailDTO.toStringCadastro(assunto.toUpperCase()));

        } catch (IllegalArgumentException ex) {
            throw new GeneralException(new ErroResposta(
                    HttpStatus.BAD_REQUEST,
                    ENVIO_EMAIL_FALHOU,
                    Arrays.asList(ERRO_EMAIL + clienteRespDTO.getEmail())));
        }
    }

    @Transactional
    @Override
    public ResponseEntity<ClienteRespDTO> inserir(ClienteRequestDTO clienteRequestDTO) {


        clienteRequestDTO.setEndereco(this.enderecoService.inserir(clienteRequestDTO.getEndereco()));

        try {
            Cliente cliente = modelMapper.map(clienteRequestDTO, Cliente.class);
            this.clienteRepository.save(cliente);
            ClienteRespDTO clienteRespDTO = modelMapper.map(cliente, ClienteRespDTO.class);
            enviarEmailCriacao(clienteRespDTO, "CADASTRO EFETUADO COM SUCESSO");
            return ResponseEntity.ok(modelMapper.map(cliente, ClienteRespDTO.class));

        } catch (IllegalArgumentException ex) {
            throw new GeneralException(new ErroResposta(
                    HttpStatus.BAD_REQUEST,
                    INSERCAO_FALHOU,
                    Arrays.asList(INSERCAO_CLIENTE_FALHOU + clienteRequestDTO.getNome())));
        }
    }

    @Override
    public ResponseEntity<List<ClienteRespDTO>> pesquisar() {
        List<Cliente> clientes = this.clienteRepository.findAll();
        return Optional.of(toDTO(clientes))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private List<ClienteRespDTO> toDTO(List<Cliente> clientes){
        return clientes.stream().map(cliente -> modelMapper.map(cliente, ClienteRespDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<ClienteRespDTO> pesquisar(Long id) {
        try {
            Optional<Cliente> cliente = this.clienteRepository.findById(id);
            return Optional.of(modelMapper.map(cliente.get(), ClienteRespDTO.class))
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());

        }catch (NoSuchElementException | NullPointerException ex){
            throw new GeneralException(new ErroResposta(
                    HttpStatus.BAD_REQUEST,
                    PESQUISA_FALHOU,
                    Arrays.asList(PESQUISA_CLIENTE_FALHOU + id)));
        }
    }

    @Transactional
    @Override
    public ResponseEntity<ClienteRespDTO> editar(Long idCliente, ClienteRequestDTO clienteRequestDTO) {
        Existencia.existeID(idCliente, this.clienteRepository, HttpStatus.BAD_REQUEST, EDICAO_FALHOU, PESQUISA_CLIENTE_FALHOU);

        try {
            Cliente cliente = modelMapper.map(clienteRequestDTO, Cliente.class);
            cliente.setId(idCliente);
            cliente.setEndereco(this.enderecoService.inserir(cliente.getEndereco()));
            cliente = this.clienteRepository.save(cliente);
            ClienteRespDTO clienteRespDTO = modelMapper.map(cliente, ClienteRespDTO.class);
            enviarEmailCriacao(clienteRespDTO, "CADASTRO EDITADO COM SUCESSO");
            return ResponseEntity.ok(modelMapper.map(cliente, ClienteRespDTO.class));

        } catch (IllegalArgumentException ex) {
            throw new GeneralException(new ErroResposta(HttpStatus.BAD_REQUEST,
                    EDICAO_FALHOU,
                    Arrays.asList(EDICAO_CLIENTE_FALHOU + clienteRequestDTO.getNome())));
        }
    }

    @Override
    public ResponseEntity<Void> deletar(Long id) {
        Existencia.existeID(id, this.clienteRepository, HttpStatus.BAD_REQUEST, EXCLUSAO_FALHOU, PESQUISA_CLIENTE_FALHOU);

        try {
            this.clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            throw new GeneralException(new ErroResposta(HttpStatus.BAD_REQUEST,
                    EXCLUSAO_FALHOU,
                    Arrays.asList(EXCLUSAO_CLIENTE_FALHOU + id)));
        }
    }

    public boolean existe(Long id, String titulo, String erro) {
        try {
            return this.clienteRepository.existsById(id);
        } catch (IllegalArgumentException ex) {
            throw new GeneralException(new ErroResposta(HttpStatus.BAD_REQUEST,
                    titulo,
                    Arrays.asList(erro + id)));
        }
    }
}

