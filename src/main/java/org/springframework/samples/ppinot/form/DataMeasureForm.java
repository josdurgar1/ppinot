package org.springframework.samples.ppinot.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import es.us.isa.ppinot.model.state.GenericState;
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
	private GenericState when;
	@NotBlank
	private String selection;
//	@NotBlank
//	private String dataObjectId;
	
	
	
	
}
