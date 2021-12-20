package org.serratec.ecommerce.cliente.dto;

import org.serratec.ecommerce.endereco.dto.EnderecoRespDTO;
import org.serratec.ecommerce.pedido.dto.PedidoRespDTO;

public class ClienteEmailDTO {
	
	private String email;
	private String nome;
	private String sobrenome;
	private EnderecoRespDTO endereco;
	private PedidoRespDTO pedido;

	public ClienteEmailDTO(ClienteRespDTO cliente) {
		this.email = cliente.getEmail();
		this.nome = cliente.getNome();
		this.sobrenome = cliente.getSobrenome();
		this.endereco = cliente.getEndereco();
	}
	
	public ClienteEmailDTO(ClienteRespDTO cliente, PedidoRespDTO pedido) {
		this(cliente);
		this.pedido = pedido;
	}

	public String toStringCadastro(String assunto) {
		return "*** " + assunto + "***\n\n" +
				"\nEmail: " + email +
				"\nNome: " + nome +
				"\nSobrenome: " + sobrenome +
				endereco.toString();

	}

	public String toStringPedido(String assunto) {
		return toStringCadastro(assunto) +
				pedido.toString();
	}

	public ClienteEmailDTO() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public EnderecoRespDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoRespDTO endereco) {
		this.endereco = endereco;
	}

	public PedidoRespDTO getPedido() {
		return pedido;
	}

	public void setPedido(PedidoRespDTO pedido) {
		this.pedido = pedido;
	}
	

}
