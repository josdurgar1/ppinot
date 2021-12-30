package org.springframework.samples.ppinot.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import es.us.isa.ppinot.evaluation.Measure;
import es.us.isa.ppinot.model.base.TimeMeasure;
import es.us.isa.ppinot.model.condition.TimeInstantCondition;
import es.us.isa.ppinot.model.condition.TimeMeasureType;
import lombok.Data;

@Data
@Document
public class Metric  implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Metric() {
		
	}
	
	@Id
	private String id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;

	private TimeInstantCondition from;
	private TimeInstantCondition to;
	private TimeInstantCondition when;
	private TimeMeasureType timeMeasureType;
	private String scale;
	private String unitOfMeasure;
	private String typeMeasure;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date creationDate;
	
	private List<MeasureRedefined> measure;
	
	@NotNull
	private String logId;
}
