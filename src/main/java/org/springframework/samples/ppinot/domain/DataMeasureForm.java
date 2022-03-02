package org.springframework.samples.ppinot.domain;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataMeasureForm {

	public DataMeasureForm() {

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
	private Boolean first;
	@NotNull
	private String precondition;
	@NotNull
	private WhenState when;
	@NotNull
	private String selection;
//	@NotNull
//	private String dataObjectId;
	
	
	
	
}
