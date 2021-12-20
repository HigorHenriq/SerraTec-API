package org.serratec.ecommerce.pedido.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.serratec.ecommerce.pedido.Pedido;
import org.serratec.ecommerce.pedido.StatusPedido;
import org.serratec.ecommerce.pedidoItem.dto.PedidoItemRespDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PedidoRespDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("Pedido ID")
    private Long id;

    @JsonProperty("Data")
    private LocalDate dataPedido = LocalDate.now();

    @JsonProperty("Status")
    private StatusPedido statusPedido = StatusPedido.PROCESSANDO;

    @JsonProperty("Nome do Cliente")
    private String nomeCliente;

    @JsonProperty("E-mail do Cliente")
    private String emailCliente;

    @JsonProperty("Itens")
    Set<PedidoItemRespDTO> itens = new HashSet<>();

    @JsonProperty("Valor Total do Pedido")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    public PedidoRespDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.dataPedido = pedido.getDataPedido();
        this.nomeCliente = pedido.getCliente().getNome();
        this.statusPedido = pedido.getStatusPedido();
        this.emailCliente = pedido.getCliente().getEmail();
        loadPedidos(pedido);
        totalizar();
    }

    public static Set<PedidoRespDTO> toDTO(List<Pedido> pedidos){
        return pedidos.stream().map(PedidoRespDTO::new).collect(Collectors.toSet());
    }

    public void totalizar(){
        this.valorTotal = BigDecimal.ZERO;
        this.itens.forEach( item -> this.valorTotal = this.valorTotal.add(item.getValorTotal()));
    }

    private void loadPedidos(Pedido pedido){
        pedido.getPedidoItens().forEach( pedidoItem -> this.itens.add(new PedidoItemRespDTO(pedidoItem)));
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

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Set<PedidoItemRespDTO> getItens() {
        return itens;
    }

    public void setItens(Set<PedidoItemRespDTO> itens) {
        this.itens = itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    @Override
    public String toString() {
        return "__________________________________________" +
                "\n\n⭐ PEDIDO ⭐" +
                "\n\nNúmero do Pedido: " + id +
                "\nData: " + dataPedido +
                "\nStatus: " + statusPedido +
                "\n\n\uD83D\uDED2 ITENS \uD83D\uDED2\n" + itens.stream().map(PedidoItemRespDTO::toString).collect(Collectors.joining("\n")) +
                "\n__________________________________________" +
                "\n\n\uD83E\uDDFE Valor Total do Pedido: R$" + valorTotal;
    }
}
