package org.serratec.pag.model;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Funcionario {

	private Long id;
	
	private String nome;
	
	private String email;
	
	private LocalDate dataNascimento;
	
	private Double salario;

	public Funcionario() {
		super();
	}

	public Funcionario(Long id, String nome, String email, LocalDate dataNascimento, Double salario) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	
}
