package org.springframework.samples.ppinot.domain;

import java.io.File;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.samples.ppinot.model.User;

import lombok.Data;

@Data
@Document
public class Archive {
	
	@Id
	private String id;
	
	@NotNull
	private String title;
	
	@NotNull
	private File file;
	
	@NotNull
	private User user;

}
