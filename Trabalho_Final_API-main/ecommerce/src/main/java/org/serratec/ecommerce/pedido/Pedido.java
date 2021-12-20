package org.serratec.ecommerce.pedido;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.serratec.ecommerce.cliente.Cliente;
import org.serratec.ecommerce.pedidoItem.PedidoItem;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.serratec.ecommerce.utils.Mensagens.NotNullBlank.STATUS_NOTNULL;
import static org.serratec.ecommerce.utils.Mensagens.SwaggerMessages.*;

/** Classe de Pedido {@link Pedido}
 */
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    @ApiModelProperty(value = IDENTIFICADOR_PEDIDO, hidden = true)
    private Long id;

    @Column(name = "data_pedido", nullable = false)
    @ApiModelProperty(value = DATA_PEDIDO, hidden = true)
    private LocalDate dataPedido = LocalDate.now();

    @Enumerated(value = EnumType.ORDINAL)
    @NotNull(message = STATUS_NOTNULL)
    @ApiModelProperty(value = STATUS_PEDIDO)
    private StatusPedido statusPedido = StatusPedido.PROCESSANDO;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @ApiModelProperty(value = CLIENTE_PEDIDO, hidden = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    Set<PedidoItem> pedidoItens = new HashSet<>();


    public void addPedidoItem(PedidoItem pedidoItem){
        this.pedidoItens.add(pedidoItem);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Cliente getCliente() { return cliente;}

    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Set<PedidoItem> getPedidoItens() {
        return pedidoItens;
    }

    public void setPedidoItens(Set<PedidoItem> pedidoItens) {
        this.pedidoItens = pedidoItens;
    }
}
