package org.serratec.backend.h2banco.domain;

import javax.validation.ValidationException;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Categoria {

	HATCH, SEDAN, PICAPE, SUV, CONVERSÍVEL, MINIVAN;
	
	@JsonCreator
	public static Categoria verifica(String value) throws ValidationException{
		for(Categoria cat : values()) {
			if(values().equals(cat.name())) {
				return cat;
			}
		}
		throw new ValidationException("Categoria Preenchida Incorretamente");
	}
}