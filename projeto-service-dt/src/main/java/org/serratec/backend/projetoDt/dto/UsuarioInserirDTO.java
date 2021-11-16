package org.serratec.backend.projetoDt.dto;

import java.util.HashSet;
import java.util.Set;

import org.serratec.backend.projetoDt.domain.Usuario;
import org.serratec.backend.projetoDt.domain.UsuarioPerfil;

public class UsuarioInserirDTO {

	private String nome;
	private String email;
	private String senha;
	
	private Set<UsuarioPerfil> usuarioPerfis = new HashSet<>();
	
	public UsuarioInserirDTO() {
		super();
	}


	public UsuarioInserirDTO(Usuario usuario) {
		super();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
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


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public Set<UsuarioPerfil> getUsuarioPerfis() {
		return usuarioPerfis;
	}


	public void setUsuarioPerfis(Set<UsuarioPerfil> usuarioPerfis) {
		this.usuarioPerfis = usuarioPerfis;
	}
	
	
	
}
