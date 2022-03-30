package org.springframework.samples.ppinot.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DerivedSingleInstanceMeasureForm {

	public DerivedSingleInstanceMeasureForm() {
		super();
	}

	// Nombre de la medida
	@NotBlank
	private String name;
	// Descripcion de la medida
	@NotBlank
	private String description;
	// Escala de la medida
	@NotBlank
	private String scale;
	// Unidad de medida del indicador
	@NotBlank
	private String unitOfMeasure;
	@NotBlank
	private String function;
}
