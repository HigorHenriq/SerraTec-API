package org.serratec.ecommerce.pedidoItem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.serratec.ecommerce.pedidoItem.PedidoItem;
import org.serratec.ecommerce.produto.dto.ProdutoRespDTO;

import java.io.Serializable;
import java.math.BigDecimal;
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PedidoItemRespDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("Produto")
	ProdutoRespDTO produto;

	@JsonProperty("Quantidade")
	private Integer quantidade;

	@JsonProperty("Valor Total do Item")
	private BigDecimal valorTotal = new BigDecimal(0);

	public PedidoItemRespDTO(PedidoItem pedidoItem) {
		this.produto = new ProdutoRespDTO(pedidoItem.getProduto());
		this.quantidade = pedidoItem.getQuantidade();
		totalizar();
	}

	public void totalizar() { this.valorTotal = this.getProduto().getValor().multiply(BigDecimal.valueOf(this.getQuantidade())); }

	public ProdutoRespDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoRespDTO produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return produto.toString() +
				"\nQuantidade: " + quantidade +
				"\nTotal do Item: R$" + valorTotal;
	}
}
