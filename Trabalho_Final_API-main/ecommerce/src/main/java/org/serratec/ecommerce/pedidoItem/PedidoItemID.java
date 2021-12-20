package org.serratec.ecommerce.pedidoItem;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static org.serratec.ecommerce.utils.Mensagens.NotNullBlank.ID_PEDIDO_NOTNULL;
import static org.serratec.ecommerce.utils.Mensagens.SwaggerMessages.CLIENTE_PEDIDO;

@Embeddable
public class PedidoItemID implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "item_id_pedido", nullable = false)
    @ApiModelProperty(value = CLIENTE_PEDIDO, hidden = true)
    private Long pedidoID;

    @Column(name = "item_id_produto", nullable = false)
    @NotNull(message = ID_PEDIDO_NOTNULL)
    @JsonProperty("Produto ID")
    private Long produtoID;

    public PedidoItemID(Long pedidoID, Long produtoID) {
        this.pedidoID = pedidoID;
        this.produtoID = produtoID;
    }

    public PedidoItemID() {

    }

    public Long getPedidoID() {
        return pedidoID;
    }

    public void setPedidoID(Long pedidoID) {
        this.pedidoID = pedidoID;
    }

    public Long getProdutoID() {
        return produtoID;
    }

    public void setProdutoID(Long produtoID) {
        this.produtoID = produtoID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PedidoItemID that = (PedidoItemID) o;

        if (!pedidoID.equals(that.pedidoID)) return false;
        return produtoID.equals(that.produtoID);
    }

    @Override
    public int hashCode() {
        int result = pedidoID.hashCode();
        result = 31 * result + produtoID.hashCode();
        return result;
    }
}
