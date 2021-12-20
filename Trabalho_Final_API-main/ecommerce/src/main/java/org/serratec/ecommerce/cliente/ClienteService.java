package org.serratec.ecommerce.cliente;

import org.serratec.ecommerce.cliente.dto.ClienteRequestDTO;
import org.serratec.ecommerce.cliente.dto.ClienteRespDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteService {

	/**
	 * Pesquisar todos os {@link Cliente}
	 * @return Lista de {@link Cliente}
	 */
	ResponseEntity<List<ClienteRespDTO>> pesquisar();

	/**
	 * Pesquisar {@link Cliente} por ID
	 * @return {@link Cliente}
	 */
	ResponseEntity<ClienteRespDTO> pesquisar(Long id);

	/**
	 * Inserir um {@link Cliente}
	 * @param corpo do {@link Cliente}
	 * @return {@link Cliente}
	 */
	ResponseEntity<ClienteRespDTO> inserir(ClienteRequestDTO clienteRequestDTO);

	/**
	 * Atualizar dados do {@link Cliente}
	 * @param idCliente do {@link Cliente}
	 * @param corpo do {@link Cliente}
	 * @return
	 */
	ResponseEntity<ClienteRespDTO> editar(Long idCliente, ClienteRequestDTO clienteRequestDTO);

	/**
	 * Deletar um {@link Cliente}
	 * @param id do {@link Cliente}
	 * @return
	 */
	ResponseEntity<Void> deletar(Long id);

//	ResponseEntity<Object> inserirPedido(Long idCliente, Pedido pedido);
//	ResponseEntity<Object> fazerPedido(Long idCliente, PedidoItem pedidoItem);
//
//	ResponseEntity<Pedido> inserirProduto(Long idPedido, Long idProduto, int quantidade);
}
