package org.springframework.samples.ppinot.domain;

import java.util.HashMap;
import java.util.Map;

import es.us.isa.ppinot.evaluation.Measure;
import es.us.isa.ppinot.evaluation.MeasureScope;
import lombok.Data;

@Data
public class MeasureRedefined {
	
	private MeasureScope measureScope;
    private Object value;
    private Map<String, Map<String, Measure>> evidences = new HashMap<String, Map<String, Measure>>();

    public MeasureRedefined() {
    }
    
    public MeasureRedefined(MeasureScope scope, Object value) {
        this.measureScope = scope;
        this.value = value;
    }
}
