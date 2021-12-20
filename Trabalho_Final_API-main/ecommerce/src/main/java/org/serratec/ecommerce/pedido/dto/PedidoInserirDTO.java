package org.serratec.ecommerce.pedido.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.serratec.ecommerce.pedido.StatusPedido;
import org.serratec.ecommerce.pedidoItem.dto.PedidoItemRequestDTO;

import javax.validation.constraints.NotNull;
import java.util.Set;

import static org.serratec.ecommerce.utils.Mensagens.NotNullBlank.STATUS_NOTNULL;

public class PedidoInserirDTO {

    @NotNull(message = STATUS_NOTNULL)
    @JsonProperty("Status do Pedido")
    private StatusPedido statusPedido;

    @NotNull(message = "Lista de itens não pode ser nula.")
    @JsonProperty("Itens")
    private Set<PedidoItemRequestDTO> itens;

    public PedidoInserirDTO(StatusPedido statusPedido, Set<PedidoItemRequestDTO> itens) {
        this.statusPedido = statusPedido;
        this.itens = itens;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Set<PedidoItemRequestDTO> getItens() {
        return itens;
    }

    public void setItens(Set<PedidoItemRequestDTO> itens) {
        this.itens = itens;
    }
}
