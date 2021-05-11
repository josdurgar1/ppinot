package org.springframework.samples.ppinot.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import es.us.isa.ppinot.model.condition.TimeInstantCondition;
import lombok.Data;

@Data
@Document(collection = "countMeasure")
public class CountMeasure {

	@Id
	private String id;
	// Nombre de la medida
	private String name;
	// Descripcion de la medida
	private String description;
	// Escala de la medida
	//private String scale;
	// Unidad de medida del indicador
	//private String unitOfMeasure;
	// Momento en que se aplica la medida
	private TimeInstantCondition when;
	
	public CountMeasure(String name, String description, TimeInstantCondition when) {
		this.name = name;
		this.description = description;
		this.when = when;
	}
}
