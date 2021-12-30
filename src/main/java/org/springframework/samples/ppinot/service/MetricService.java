package org.springframework.samples.ppinot.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.CountMeasureForm;
import org.springframework.samples.ppinot.domain.Log;
import org.springframework.samples.ppinot.domain.MeasureRedefined;
import org.springframework.samples.ppinot.domain.Metric;
import org.springframework.samples.ppinot.domain.TimeMeasureForm;
import org.springframework.samples.ppinot.repository.MetricRepository;
import org.springframework.stereotype.Service;

import es.us.isa.ppinot.evaluation.Measure;
import es.us.isa.ppinot.evaluation.evaluators.LogMeasureEvaluator;
import es.us.isa.ppinot.evaluation.evaluators.MeasureEvaluator;
import es.us.isa.ppinot.evaluation.logs.LogProvider;
import es.us.isa.ppinot.evaluation.logs.MXMLLog;
import es.us.isa.ppinot.model.MeasureDefinition;
import es.us.isa.ppinot.model.base.CountMeasure;
import es.us.isa.ppinot.model.base.TimeMeasure;
import es.us.isa.ppinot.model.condition.TimeInstantCondition;
import es.us.isa.ppinot.model.scope.Period;
import es.us.isa.ppinot.model.scope.SimpleTimeFilter;
import es.us.isa.ppinot.model.state.GenericState;
import es.us.isa.ppinot.model.state.RuntimeState;

@Service
public class MetricService {

	@Autowired
	private MetricRepository metricRepository;
	@Autowired
	private LogService logService;

	public void addTimeMeasure(String logId, @Valid TimeMeasureForm timeMeasureForm) throws Exception {

		Metric result = new Metric();
		TimeMeasure timeMeasure = new TimeMeasure();
		timeMeasure.setName(timeMeasureForm.getName());
		timeMeasure.setDescription(timeMeasureForm.getDescription());
		timeMeasure.setScale(timeMeasureForm.getScale());
		timeMeasure.setUnitOfMeasure(timeMeasureForm.getUnitOfMeasure());
		timeMeasure.setTimeMeasureType(timeMeasureForm.getTimeMeasureType());
		timeMeasure.setFrom(new TimeInstantCondition(timeMeasureForm.getAppliesFrom(), timeMeasureForm.getFrom()));
		timeMeasure.setTo(new TimeInstantCondition(timeMeasureForm.getAppliesTo(), timeMeasureForm.getTo()));
		List<Measure> measures = compute(timeMeasure, logId);
//		log.getAssignedMetrics().add(result);
		result.setName(timeMeasure.getName());
		Date date = new Date();
		result.setCreationDate(date);
		result.setLogId(logId);
		result.setDescription(timeMeasure.getDescription());
//		result.setFrom(timeMeasure.getFrom());
//		result.setTo(timeMeasure.getTo());
		result.setScale(timeMeasure.getScale());
		result.setTimeMeasureType(timeMeasure.getTimeMeasureType());
		result.setUnitOfMeasure(timeMeasure.getUnitOfMeasure());
		result.setTypeMeasure(timeMeasure.getClass().toString().substring(34));
		List<MeasureRedefined> mR = new ArrayList<MeasureRedefined>();
		for (Measure m : measures) {
			MeasureRedefined r = new MeasureRedefined();
			r.setEvidences(m.getEvidences());
			r.setMeasureScopeRedefined(m.getMeasureScope());
			r.setValue(r.getValue());
			mR.add(r);
		}
		result.setMeasure(mR);
		metricRepository.save(result);
	}

	private List<Measure> compute(MeasureDefinition measure, String logId) throws Exception {
		Log log = logService.findById(logId);
//		InputStream is= new FileInputStream(log.getTitle()+".mxml");
		InputStream is = new ByteArrayInputStream(log.getFile());
//		is.read(log.getFile());
//		is.close(); 

		LogProvider mxmlLog = new MXMLLog(is, null);

		MeasureEvaluator evaluator = new LogMeasureEvaluator(mxmlLog);

		return evaluator.eval(measure, new SimpleTimeFilter(Period.MONTHLY, 1, false));

	}

	public void deleteAssociateMetric(String logId) {

		List<Metric> metrics = metricRepository.findByLogId(logId);
		for (Metric t : metrics) {
			metricRepository.delete(t);
		}

	}

	public List<Metric> findByLogId(String logId) {
		List<Metric> result;
		String logId2 = logId;
		result = metricRepository.findByLogId(logId2);
		return result;

	}

	public void addCountMeasure(String logId, CountMeasureForm countMeasureForm) throws Exception {
		Metric result = new Metric();
		CountMeasure countMeasure = new CountMeasure();
		countMeasure.setName(countMeasureForm.getName());
		countMeasure.setDescription(countMeasureForm.getDescription());
		countMeasure.setScale(countMeasureForm.getScale());
		countMeasure.setUnitOfMeasure(countMeasureForm.getUnitOfMeasure());
		countMeasure.setWhen(new TimeInstantCondition(countMeasureForm.getAppliesWhen(), countMeasureForm.getWhen()));
		List<Measure> measures = compute(countMeasure, logId);
		result.setName(countMeasure.getName());
		Date date = new Date();
		result.setCreationDate(date);
		result.setLogId(logId);
		result.setDescription(countMeasure.getDescription());
//		result.setWhen(countMeasure.getWhen());
		result.setScale(countMeasure.getScale());
		result.setUnitOfMeasure(countMeasure.getUnitOfMeasure());
		result.setTypeMeasure(countMeasure.getClass().toString().substring(34));
		List<MeasureRedefined> mR = new ArrayList<MeasureRedefined>();
		for (Measure m : measures) {
			MeasureRedefined r = new MeasureRedefined();
			r.setEvidences(m.getEvidences());
			r.setMeasureScopeRedefined(m.getMeasureScope());
			r.setValue(r.getValue());
			mR.add(r);
		}
		result.setMeasure(mR);
		metricRepository.save(result);

	}
}
