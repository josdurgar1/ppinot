package org.springframework.samples.ppinot.util;

import es.us.isa.ppinot.model.MeasureDefinition;
import es.us.isa.ppinot.model.base.TimeMeasure;

public class CreateMeasureType {
	
	public static MeasureDefinition measureType(String text) {
	MeasureDefinition result = null;
		
		switch (text) {
	case "TimeMeasure":
		result = new TimeMeasure();
		break;
	case "DataMeasure":
		result = new TimeMeasure();
		break;
	case "CountMeasure":
		result = new TimeMeasure();
		break;
	}		
	
	return result;
	
}


}
