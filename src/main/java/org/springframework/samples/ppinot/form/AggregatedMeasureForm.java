package org.springframework.samples.ppinot.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import es.us.isa.ppinot.model.MeasureDefinition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AggregatedMeasureForm {

	public AggregatedMeasureForm() {
		super();
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
	@NotBlank
	private String unitOfMeasure;
	@NotBlank
	private String aggregationFunction;
	@NotBlank
	private String samplingFrequency;
	@NotNull
	private MeasureDefinition baseMeasure;
	@NotNull
	private MeasureDefinition filter;

}
