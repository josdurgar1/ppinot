package org.springframework.samples.ppinot.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import es.us.isa.ppinot.evaluation.Measure;
import lombok.Data;

@Data
@Document
public class Metric  implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@NotNull
	private String name;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date creationDate;
	
	private List<Measure> measure;
	
	@NotNull
	private String logId;
}
