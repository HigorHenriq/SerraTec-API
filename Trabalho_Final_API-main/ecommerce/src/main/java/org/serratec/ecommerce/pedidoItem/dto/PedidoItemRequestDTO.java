package org.serratec.ecommerce.pedidoItem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.serratec.ecommerce.pedidoItem.PedidoItemID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PedidoItemRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID do Item não pode ser nulo.")
    @JsonProperty("Produto")
    private PedidoItemID pedidoItemID;

    @NotNull(message = "Quantidade não pode ser nula.")
    @Min(value = 1, message = "O valor mínimo é: {value}")
    @JsonProperty("Quantidade")
    private int quantidade;

    public PedidoItemRequestDTO(PedidoItemID pedidoItemID, int quantidade) {
        this.pedidoItemID = pedidoItemID;
        this.quantidade = quantidade;
    }

    public PedidoItemID getPedidoItemID() {
        return pedidoItemID;
    }

    public void setPedidoItemID(PedidoItemID pedidoItemID) {
        this.pedidoItemID = pedidoItemID;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
