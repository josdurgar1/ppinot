package org.springframework.samples.ppinot.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.Log;
import org.springframework.samples.ppinot.domain.Metric;
import org.springframework.samples.ppinot.repository.MetricRepository;
import org.springframework.stereotype.Service;

import es.us.isa.ppinot.evaluation.Measure;
import es.us.isa.ppinot.evaluation.evaluators.LogMeasureEvaluator;
import es.us.isa.ppinot.evaluation.evaluators.MeasureEvaluator;
import es.us.isa.ppinot.evaluation.logs.LogProvider;
import es.us.isa.ppinot.evaluation.logs.MXMLLog;
import es.us.isa.ppinot.model.MeasureDefinition;
import es.us.isa.ppinot.model.base.TimeMeasure;
import es.us.isa.ppinot.model.scope.Period;
import es.us.isa.ppinot.model.scope.SimpleTimeFilter;

@Service
public class MetricService {

	
	@Autowired
	private MetricRepository metricRepository;
	@Autowired
	private LogService logService;
	
	
	public void addTimeMeasure(String logId, @Valid TimeMeasure timeMeasure) throws Exception {

		Metric result=new Metric();
		List<Measure> measure = compute(timeMeasure, logId);
//		log.getAssignedMetrics().add(result);
		result.setName(timeMeasure.getName());
		Date date = new Date();
		result.setCreationDate(date);
		result.setLogId(logId);
		result.setMeasure(measure);
		metricRepository.save(result);
	}

	private List<Measure> compute(MeasureDefinition measure, String logId) throws Exception {
		Log log = logService.findById(logId);
//		InputStream is= new FileInputStream(log.getTitle()+".mxml");
		InputStream is= new ByteArrayInputStream(log.getFile());
//		is.read(log.getFile());
//		is.close(); 
		
		LogProvider mxmlLog = new MXMLLog(is, null);

		MeasureEvaluator evaluator = new LogMeasureEvaluator(mxmlLog);

		return evaluator.eval(measure, new SimpleTimeFilter(Period.MONTHLY, 1, false));

	}


	public void deleteAssociateMetric(String logId) {

		List<Metric> metrics=metricRepository.findByLogId(logId);
		for(Metric t:metrics) {
			metricRepository.delete(t);
		}
		
	}
	
	public List<Metric> findByLogId(String logId){
		List<Metric> result;
		result=metricRepository.findByLogId(logId);
		return result;
		
	}
}
