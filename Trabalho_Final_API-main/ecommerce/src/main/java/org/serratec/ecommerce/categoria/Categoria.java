package org.serratec.ecommerce.categoria;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.serratec.ecommerce.produto.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

import static org.serratec.ecommerce.utils.Mensagens.NotNullBlank.DESCRICAO_NOTNULL;
import static org.serratec.ecommerce.utils.Mensagens.NotNullBlank.NOME_NOTNULL;
import static org.serratec.ecommerce.utils.Mensagens.SwaggerMessages.IDENTIFICADOR_CATEGORIA;
import static org.serratec.ecommerce.utils.Mensagens.SwaggerMessages.NOME_CATEGORIA;

@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	@ApiModelProperty(value = IDENTIFICADOR_CATEGORIA, hidden = true)
	@JsonProperty("Categoria ID")
	private Long id;
		
	@NotBlank(message = NOME_NOTNULL)
	@Size (max = 50)
	@Column(name = "nome", nullable = false, length = 50)
	@ApiModelProperty(value = NOME_CATEGORIA)
	@JsonProperty("Nome")
	private String nome;
	
	@NotBlank(message = DESCRICAO_NOTNULL)
	@Size (max = 200)
	@Column (name = "descricao", nullable = false, length = 200)
	@JsonProperty("Descrição")
	private String descricao;

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ApiModelProperty(hidden = true)
	private List<Produto> produtos;

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long codCategoria) {
		this.id = codCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		this.nome = name;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "\nCATEGORIA" +
				"\nNome: " + nome +
				"\nDescrição: " + descricao;
	}
}
