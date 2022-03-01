package org.springframework.samples.ppinot.domain;

import java.util.Collection;
import java.util.Map;

import es.us.isa.ppinot.evaluation.MeasureScope;
import lombok.Data;

@Data
public class MeasureScopeRedefined implements MeasureScope{

	private Collection<String> instances;
	private String processId;
	private Map<String, Object> scopeInfo;
	

}