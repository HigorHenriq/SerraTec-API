package org.serratec.ecommerce.produto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.serratec.ecommerce.categoria.Categoria;
import org.serratec.ecommerce.produto.Produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoRespDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("Produto ID")
    private Long id;

    @JsonProperty("Nome")
    private String nome;

    @JsonProperty("Descrição")
    private String descricao;

    @JsonProperty("Data de Fabricação")
    private LocalDate dataFabricacao;

    @JsonProperty("Valor Unitário")
    private BigDecimal valor;

    @JsonProperty("Categoria")
    private Categoria categoria;

    public ProdutoRespDTO() {
    }

    public ProdutoRespDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.dataFabricacao = produto.getDataFabricacao();
        this.valor = produto.getValor();
        this.categoria = produto.getCategoria();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "\n\uD83D\uDECD PRODUTO" +
                "\nNome:" + nome +
                "\nDescrição: " + descricao +
                "\nData de Fabricação: " + dataFabricacao +
                "\nValor Unitário: R$" + valor;
//                categoria.toString();
    }
}
