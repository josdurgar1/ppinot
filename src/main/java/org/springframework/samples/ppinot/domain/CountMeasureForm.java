package org.springframework.samples.ppinot.domain;

import java.util.Map;

import javax.validation.constraints.NotNull;

import es.us.isa.ppinot.model.state.RuntimeState;
import lombok.Data;

@Data
public class CountMeasureForm {

	public CountMeasureForm() {

	}

	// Nombre de la medida
	@NotNull
	private String name;
	// Descripcion de la medida
	@NotNull
	private String description;
	// Escala de la medida
	@NotNull
	private String scale;
	// Unidad de medida del indicador
	@NotNull
	private String unitOfMeasure;
	@NotNull
	// Momento en que se aplica la medida
	private String appliesWhen;
	@NotNull
	private RuntimeState when;

}
