package org.serratec.backend.exercicio01.domain;

public class Aluno {

	private String nome;
	private Long matricula;
	private String telefone;
	
	
	
	public Aluno(String nome, Long matricula, String telefone) {
		this.nome = nome;
		this.matricula = matricula;
		this.telefone = telefone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public Long getMatricula() {
		return matricula;
	}
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
