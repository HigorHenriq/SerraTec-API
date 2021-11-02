package org.serratec.backend.funcionario.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Gerente extends Funcionario{

	@Column
	@NotBlank(message = "Setor não pode vazio")
	private String setor;

	
	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}
	
	
}
