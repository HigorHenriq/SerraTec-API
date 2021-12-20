package org.serratec.ecommerce.pedidoItem;

import org.serratec.ecommerce.pedido.Pedido;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PedidoItemService {
	
	 /** Pesquisa todos os {@link PedidoItem}.
	 * @return Uma lista de {@link PedidoItem}
	 */
	ResponseEntity<List<PedidoItem>> pesquisar();

	/**
     * Pesquisa uma {@link PedidoItem} pelo id
     * @param id
     * @return {@link PedidoItem}
     */
//	ResponseEntity<PedidoItem> pesquisar(Long id);

    /**
     * Insere um {@link PedidoItem}
     * @param pedidoItem
     * @return {@link PedidoItem}
     */
	PedidoItem inserir(PedidoItem pedidoItem);


	  /**
     * Atualiza um {@link PedidoItem}
     * @param pedidoItem
     * @param id
     * @return ResponseEntity<PedidoItem>*/
//	ResponseEntity<PedidoItem> editar(PedidoItem pedidoItem, Long id) throws GeneralException;


	/**
	 * Remove um {@link Pedido} por ID
	 * @param id ID do {@link Pedido}
	 * @return {@link ResponseEntity}<{@link Pedido}>
	 */
	ResponseEntity<Void> remover(PedidoItemID id);

}
