package org.serratec.backend.pessoa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PessoaFisica extends Fornecedor{

	@Column
	private String rg;
	
	@Column
	private String orgaoEmissor;
	
	@Column
	private String cpf;

	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
