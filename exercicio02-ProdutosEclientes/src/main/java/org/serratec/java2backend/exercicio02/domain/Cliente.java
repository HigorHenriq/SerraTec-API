package org.serratec.java2backend.exercicio02.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente", unique = true)
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 60)
	@NotBlank(message = "Nome não pode ser nulo")
	@Size(max = 60)
	private String nome;
	
	@Column(name = "cpf", nullable = false, length = 11)
	@CPF
	@Size(max = 11, message = "CPF invalido,verifique o tamanho")
	@NotBlank(message = "CPF Não pode ser nulo!")
	private String cpf;
	
	@Column(name = "email", nullable = false, length = 50)
	@Email
	@Size(max = 50, message = "Tamanho Máximo de 50 Caracteres")
	@NotBlank(message = "Email Não pode ser nulo!")
	private String email;
	
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	@Embedded
	private Endereco endereco;

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

	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	
}
