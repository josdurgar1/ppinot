package org.springframework.samples.ppinot.domain;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import es.us.isa.ppinot.model.condition.TimeMeasureType;
import es.us.isa.ppinot.model.state.RuntimeState;
import lombok.Data;

@Data
@Document
public class TimeMeasure implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TimeMeasure() {
		super();
	}
	@Id
	private String id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String Scale;
	
	@NotBlank
	private String unitOfMeasure;
	
	@NotBlank
	private String fromAppliesTo;
	
	private RuntimeState fromChangesToState;
	
	@NotBlank
	private String toAppliesTo;
	
	private RuntimeState toChangesToState;
	
	private TimeMeasureType timeMeasureType;
	
	private String singleInstanceAggFunction;

}
