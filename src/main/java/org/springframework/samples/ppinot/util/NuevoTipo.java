package org.springframework.samples.ppinot.util;

import lombok.Data;

@Data
public class NuevoTipo {

	public NuevoTipo() {
		super();
	}

	private String dato;
	private Double valor;

	public NuevoTipo(String dato, Double valor) {
		this.dato = dato;
		this.valor = valor;

	}

	@Override
	public String toString() {
		return "['"+dato+"'"+", " + valor+"]";
	}

}
