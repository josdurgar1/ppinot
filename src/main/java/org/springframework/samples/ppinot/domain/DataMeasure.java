package org.springframework.samples.ppinot.domain;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import es.us.isa.ppinot.model.state.RuntimeState;
import lombok.Data;

@Data
@Document
public class DataMeasure implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataMeasure() {
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
	private String selection;
	
	private String dataobjectid;
	
	private String appliesTo;

	private String restriction;

	private RuntimeState state;

}
