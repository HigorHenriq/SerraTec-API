package org.serratec.ecommerce.produto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.serratec.ecommerce.categoria.Categoria;
import org.serratec.ecommerce.pedidoItem.PedidoItem;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.serratec.ecommerce.utils.Mensagens.NotNullBlank.*;
import static org.serratec.ecommerce.utils.Mensagens.SwaggerMessages.CATEGORIA_PRODUTO;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    @ApiModelProperty(hidden = true)
    @JsonProperty("Produto ID")
    private Long id;

    @NotBlank(message = NOME_NOTNULL)
    @Column(name = "nome", nullable = false)
    @ApiModelProperty(value = "Nome do Produto")
    @JsonProperty("Nome")
    private String nome;

    @NotBlank(message = DESCRICAO_NOTNULL)
    @Column(name = "descricao")
    @ApiModelProperty(value = "Descrição do Produto", position = 3)
    @JsonProperty("Descrição")
    private String descricao;

    @NotNull(message = DATA_NOTNULL)
    @Column(name = "data_fabricacao", nullable = false)
    @PastOrPresent(message = "A data deve ser anterior ou igual à data atual.")
    @ApiModelProperty(value = "Data de Fabricação do Produto", position = 4)
    @JsonProperty("Data de Fabricação")
    private LocalDate dataFabricacao;

    @NotNull(message = VALOR_NOTNULL)
    @Column(name = "valor", nullable = false)
    @Min(value = 0, message = "O valor mínimo é: {value}")
    @ApiModelProperty(value = "Valor do Produto")
    @JsonProperty("Valor Unitário")
    private BigDecimal valor = BigDecimal.ZERO;

    @NotNull(message = VALOR_NOTNULL)
    @Column(name = "qtd_estoque", nullable = false)
    @Min(value = 0, message = "O valor mínimo é: {value}")
    @ApiModelProperty(value = "Quantidade em Estoque do Produto")
    @JsonProperty("Quantidade em Estoque")
    private int quantidadeEstoque = 0;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id_categoria", nullable = false)
    @ApiModelProperty(value = CATEGORIA_PRODUTO)
    @Valid
    @JsonProperty("Categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @ApiModelProperty(hidden = true)
    @Valid
    @JsonProperty(value = "Itens", access = JsonProperty.Access.WRITE_ONLY)
    private Set<PedidoItem> pedidoItens;



    public Set<PedidoItem> getPedidoItens() {
        return pedidoItens;
    }

    public void setPedidoItens(Set<PedidoItem> pedidoItens) {
        this.pedidoItens = pedidoItens;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}
