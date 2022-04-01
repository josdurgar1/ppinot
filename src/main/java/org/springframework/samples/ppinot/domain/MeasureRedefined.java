package org.springframework.samples.ppinot.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import es.us.isa.ppinot.evaluation.Measure;
import es.us.isa.ppinot.evaluation.MeasureScope;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class MeasureRedefined {
	
	private MeasureScopeRedefined measureScope;
    private Double value;
    private Map<String, Map<String, Measure>> evidences = new HashMap<String, Map<String, Measure>>();

    public MeasureRedefined() {
    }
    
    public MeasureRedefined(MeasureScopeRedefined scope, Double value) {
        this.measureScope = scope;
        this.value = value;
    }
    
    public void setMeasureScopeRedefined(MeasureScope m) {
    	MeasureScopeRedefined mR=new MeasureScopeRedefined();
    	mR.setInstances(m.getInstances());
    	mR.setProcessId(m.getProcessId());
    	mR.setScopeInfo(m.getScopeInfo());
    	this.measureScope=mR;
    }
}
