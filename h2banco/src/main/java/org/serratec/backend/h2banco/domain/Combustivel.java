package org.serratec.backend.h2banco.domain;

import javax.validation.ValidationException;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Combustivel {

	ALCOOL(1, "Álcool"), GASOLINA(2, "Gasolina"), DIESEL(3, "Diesel"), FLEX(4, "Flex");
	
	private Integer codigo;
	private String tipo;
	
	
	private Combustivel(Integer codigo, String tipo) {
		this.codigo = codigo;
		this.tipo = tipo;
	}


	public Integer getCodigo() {
		return codigo;
	}


	public String getTipo() {
		return tipo;
	}
	
	@JsonCreator
	public static Combustivel verifica(String value) throws ValidationException{
		for(Combustivel combustivel : values()) {
			if(values().equals(combustivel.name())) {
				return combustivel;
			}
		}
		throw new ValidationException("Combustivel Preenchida Incorretamente");
	}
}
