package org.springframework.samples.ppinot.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.Log;
import org.springframework.samples.ppinot.service.LogService;
import org.springframework.stereotype.Service;

import es.us.isa.ppinot.evaluation.Measure;
import es.us.isa.ppinot.evaluation.evaluators.LogMeasureEvaluator;
import es.us.isa.ppinot.evaluation.evaluators.MeasureEvaluator;
import es.us.isa.ppinot.evaluation.logs.LogProvider;
import es.us.isa.ppinot.evaluation.logs.MXMLLog;
import es.us.isa.ppinot.model.MeasureDefinition;
import es.us.isa.ppinot.model.scope.Period;
import es.us.isa.ppinot.model.scope.SimpleTimeFilter;

@Service
public class MeasureList {
	
	@Autowired
	private LogService logService;
	
	public List<Measure> compute(MeasureDefinition measure, String logId) throws Exception {
		Log log = logService.findById(logId);
//		InputStream is= new FileInputStream(log.getTitle()+".mxml");
		InputStream is = new ByteArrayInputStream(log.getFile());
//		is.read(log.getFile());
//		is.close(); 

		LogProvider mxmlLog = new MXMLLog(is, null);

		MeasureEvaluator evaluator = new LogMeasureEvaluator(mxmlLog);

		return evaluator.eval(measure, new SimpleTimeFilter(Period.MONTHLY, 1, false));

	}

}
