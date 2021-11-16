package com.sd.email.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


public class EmailDTO {

	@NotBlank
	private String proprietario;
	
	@NotBlank
	@Email
	private String emailDe;
	
	@NotBlank
	@Email
	private String emailPara;
	
	@NotBlank
	private String titulo;
	
	@Column(columnDefinition = "TEXT")
	private String corpoMensagem;

	
	public EmailDTO() {

	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public String getEmailDe() {
		return emailDe;
	}

	public void setEmailDe(String emailDe) {
		this.emailDe = emailDe;
	}

	public String getEmailPara() {
		return emailPara;
	}

	public void setEmailPara(String emailPara) {
		this.emailPara = emailPara;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCorpoMensagem() {
		return corpoMensagem;
	}

	public void setCorpoMensagem(String corpoMensagem) {
		this.corpoMensagem = corpoMensagem;
	}
	
}
