package org.springframework.samples.ppinot.domain;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import es.us.isa.ppinot.model.state.RuntimeState;
import lombok.Data;

@Data
@Document
public class CountMeasure implements Serializable {

	protected static final long serialVersionUID = 1L;

	public CountMeasure() {
		super();
	}

	@Id
	private String id;
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

	private String appliesTo;

	private RuntimeState state;

}
