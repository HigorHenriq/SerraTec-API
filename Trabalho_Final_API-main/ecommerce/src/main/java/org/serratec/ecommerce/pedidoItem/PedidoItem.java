package org.serratec.ecommerce.pedidoItem;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.serratec.ecommerce.pedido.Pedido;
import org.serratec.ecommerce.produto.Produto;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static org.serratec.ecommerce.utils.Mensagens.NotNullBlank.QUANTIDADE_NOTNULL;
import static org.serratec.ecommerce.utils.Mensagens.SwaggerMessages.IDENTIFICADOR_PEDIDO;
import static org.serratec.ecommerce.utils.Mensagens.SwaggerMessages.QUANTIDADE_PRODUTOS_PEDIDO_ITEM;

@Entity
@Table(name = "pedido_item")
public class PedidoItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_item", nullable = false)
    @ApiModelProperty(value = IDENTIFICADOR_PEDIDO, hidden = true)
    private PedidoItemID id;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    @ApiModelProperty(hidden = true)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Pedido pedido;

    @NotNull(message = QUANTIDADE_NOTNULL)
    @Column(name = "quantidade", nullable = false)
    @ApiModelProperty(value = QUANTIDADE_PRODUTOS_PEDIDO_ITEM)
    @Min(value = 1, message = "O valor mínimo é: {value}")
    private Integer quantidade;

    public PedidoItemID getId() {
        return id;
    }

    public void setId(PedidoItemID id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
