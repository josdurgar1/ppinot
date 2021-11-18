package org.springframework.samples.ppinot.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import es.us.isa.ppinot.model.MeasureDefinition;
import lombok.Data;

@Data
@Document
public class Log implements Serializable{

	protected static final long	serialVersionUID	= 1L;
	
	public Log() {
		super();
	}
	
	public Log(String id, String title, byte[] file, Date uploadDate,List<String> assignedMetrics, String userId) {
		this.id=id;
		this.title=title;
		this.file=file;
		this.uploadDate=uploadDate;
		this.assignedMetrics=assignedMetrics;
		this.userId=userId;
	}
	
	@Id
	private String id;
	
	@NotBlank
	private String title;
	
	@NotNull
	private byte[]  file;
	
//	@NotNull
//	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date uploadDate;
		
	private List<String> assignedMetrics;
	
	@NotNull
	private String userId;
	

	private String downloadUri;
	
//	public File getFile() {
//		File result;
//
//		result = ((File) this.file).getAbsoluteFile();
//
//		return result;
//	}

}
