package org.serratec.ecommerce.pedidoItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoItemServiceImpl implements PedidoItemService{
	
	@Autowired
	private PedidoItemRepository pedidoItemRepository;
	
	@Autowired
	private PedidoItemController pedidoItemController;

	@Override
	public ResponseEntity<List<PedidoItem>> pesquisar() {
		List<PedidoItem> pedidoItem = this.pedidoItemRepository.findAll();
		return ResponseEntity.ok(pedidoItem);
	}

	@Override
	public PedidoItem inserir(PedidoItem pedidoItem) {

		return this.pedidoItemRepository.save(pedidoItem);
	}

	@Override
	public ResponseEntity<Void> remover(PedidoItemID id) {
		if (!this.pedidoItemRepository.existsById(id)){
			System.out.println("FAIL");
		}

		this.pedidoItemRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
