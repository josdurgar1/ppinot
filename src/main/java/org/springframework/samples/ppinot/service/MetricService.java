package org.springframework.samples.ppinot.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
import es.us.isa.ppinot.model.condition.TimeInstantCondition;
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
		result.setLogId(logId);
		result.setMeasure(measure);
		metricRepository.save(result);
	}

	private List<Measure> compute(MeasureDefinition measure, String logId) throws Exception {

		File file=generateFile(logId);
		
		LogProvider mxmlLog = new MXMLLog(new FileInputStream(file), null);

		MeasureEvaluator evaluator = new LogMeasureEvaluator(mxmlLog);

		return evaluator.eval(measure, new SimpleTimeFilter(Period.MONTHLY, 1, false));

	}

	private File generateFile(String logId) throws FileNotFoundException {
		Log log = logService.findById(logId);
		String FILEPATH = "C:"+"\\"+log.getTitle();
		File file = new File(FILEPATH);
		try {
			OutputStream os = new FileOutputStream(file);
			// Starts writing the bytes in it
			os.write(log.getFile());
			// Close the file
			os.close();
		}

		catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		return file;
	}

	public void deleteAssociateMetric(String logId) {

		List<Metric> metrics=metricRepository.findByLogId(logId);
		for(Metric t:metrics) {
			metricRepository.delete(t);
		}
		
	}
}
