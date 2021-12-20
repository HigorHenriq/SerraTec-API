package org.serratec.ecommerce.pedidoItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, PedidoItemID> {

	Optional<PedidoItem> findById(PedidoItemID id);

//	boolean existsById(Long idPedido, Long idProduto);

}
	
	
