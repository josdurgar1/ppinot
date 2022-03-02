package org.springframework.samples.ppinot.domain;

import javax.validation.constraints.NotNull;

import es.us.isa.ppinot.model.condition.TimeMeasureType;
import es.us.isa.ppinot.model.state.GenericState;
import lombok.Data;

@Data
public class DurationMeasureForm {

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
	// Momento en el cual se toma la medida en la actividad inicial (el inicio o el
	// final)
	@NotNull
	private String appliesFrom;
	// Momento en el cual se toma la medida en la actividad final (el inicio o el
	// final)
	@NotNull
	private String appliesTo;
	@NotNull
	private GenericState from;
	@NotNull
	private GenericState to;
	// Tipo de la medida (ciclica o lineal)
	@NotNull
	private TimeMeasureType timeMeasureType;
	@NotNull
	private String singleInstanceAggFunction;

}
