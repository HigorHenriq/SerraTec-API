package org.serratec.ecommerce.pedido;

import org.serratec.ecommerce.cliente.Cliente;
import org.serratec.ecommerce.pedido.dto.PedidoInserirDTO;
import org.serratec.ecommerce.pedido.dto.PedidoRespDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface PedidoService {

    /** Pesquisa todos os pedidos.
     * @return {@link ResponseEntity}<{@link List}<{@link Pedido}>>
     */
    ResponseEntity<Set<PedidoRespDTO>> pesquisar();

    /**
     * Pesquisa um pedido pelo ID
     * @param id
     * @return {@link ResponseEntity}<{@link Pedido}>
     */
    ResponseEntity<PedidoRespDTO> pesquisar(Long id);

    /**
     * Adiciona um novo {@link Pedido}
     * @param idCliente ID do {@link Cliente}
     * @param pedidosDTO Corpo do {@link PedidoInserirDTO}
     * @return {@link ResponseEntity}<{@link PedidoRespDTO}>
     */
    ResponseEntity<PedidoRespDTO> adicionarPedido(Long idCliente, PedidoInserirDTO pedidosDTO);

    /**
     * Editar um {@link Pedido}
     * @param idPedido ID do {@link Pedido}
     * @param pedidosDTO Corpo do {@link PedidoInserirDTO}
     * @return {@link ResponseEntity}<{@link PedidoRespDTO}>
     */
    ResponseEntity<PedidoRespDTO> editarPedido(Long idPedido, PedidoInserirDTO pedidosDTO);

    /**
     * Remove um {@link Pedido} por ID
     * @param id ID do {@link Pedido}
     * @return {@link ResponseEntity}<{@link Pedido}>
     */
    ResponseEntity<Pedido> remover(Long id);
}
