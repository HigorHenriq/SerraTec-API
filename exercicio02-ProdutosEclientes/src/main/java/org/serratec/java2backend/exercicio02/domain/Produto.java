package org.serratec.java2backend.exercicio02.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto", unique = true)
	private Long id;
	
	@Column(name = "descricao", nullable = false, length = 40)
	@NotBlank //FUNÇÃO DO BEAN PARA NÃO ACEITAR O JSON COM A DESCRIÇÃO VAZIA E RETORNAR UM ERRO 400 PRO CLIENTE E FALAR QUE NÃO PODE SER NULO OU VAZIO
	@Size(max = 40, message = "Tamanho maximo de 40")//FUNÇÃO DO BEAN PARA ACEITAR NO MAXIMO 40LETRAS; 
	private String descricao;
	
	@Column
	@DecimalMax(value = "5000", message = "O preço não pode ser maior que ${value}.00")
	@DecimalMin(value = "10", message = "O preço não pode ser menor que ${value}.00)")
	private BigDecimal valor;
	
	@Column(name = "data_cadastro")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}