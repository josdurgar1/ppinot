package org.springframework.samples.ppinot.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.CountMeasureForm;
import org.springframework.samples.ppinot.domain.DataMeasureForm;
import org.springframework.samples.ppinot.domain.DurationMeasureForm;
import org.springframework.samples.ppinot.domain.Log;
import org.springframework.samples.ppinot.domain.MeasureRedefined;
import org.springframework.samples.ppinot.domain.Metric;
import org.springframework.samples.ppinot.domain.TimeMeasureForm;
import org.springframework.samples.ppinot.domain.WhenState;
import org.springframework.samples.ppinot.repository.MetricRepository;
import org.springframework.stereotype.Service;

import es.us.isa.ppinot.evaluation.Measure;
import es.us.isa.ppinot.evaluation.evaluators.LogMeasureEvaluator;
import es.us.isa.ppinot.evaluation.evaluators.MeasureEvaluator;
import es.us.isa.ppinot.evaluation.logs.LogProvider;
import es.us.isa.ppinot.evaluation.logs.MXMLLog;
import es.us.isa.ppinot.model.DataContentSelection;
import es.us.isa.ppinot.model.MeasureDefinition;
import es.us.isa.ppinot.model.base.CountMeasure;
import es.us.isa.ppinot.model.base.DataMeasure;
import es.us.isa.ppinot.model.base.DurationMeasure;
import es.us.isa.ppinot.model.base.TimeMeasure;
import es.us.isa.ppinot.model.condition.Condition;
import es.us.isa.ppinot.model.condition.TimeInstantCondition;
import es.us.isa.ppinot.model.scope.Period;
import es.us.isa.ppinot.model.scope.SimpleTimeFilter;
import es.us.isa.ppinot.model.state.BPMNState;
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
		countMeasure.setWhen(new TimeInstantCondition(countMeasureForm.getAppliesWhen(), state(countMeasureForm.getWhen())));
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
	
	
	public void addDataMeasure(String logId, DataMeasureForm dataMeasureForm) throws Exception {
		Metric result = new Metric();
		DataMeasure dataMeasure = new DataMeasure();
		dataMeasure.setName(dataMeasureForm.getName());
		dataMeasure.setDescription(dataMeasureForm.getDescription());
		dataMeasure.setScale(dataMeasureForm.getScale());
		dataMeasure.setUnitOfMeasure(dataMeasureForm.getUnitOfMeasure());
		dataMeasure.setFirst(dataMeasureForm.getFirst());
		Condition precondition =new TimeInstantCondition(dataMeasureForm.getPrecondition(),state(dataMeasureForm.getWhen()));
		dataMeasure.setPrecondition(precondition);
		DataContentSelection dataContentSelection=new DataContentSelection(dataMeasureForm.getSelection(),"");
		dataMeasure.setDataContentSelection(dataContentSelection);
		List<Measure> measures = compute(dataMeasure, logId);
		result.setName(dataMeasure.getName());
		Date date = new Date();
		result.setCreationDate(date);
		result.setLogId(logId);
		result.setDescription(dataMeasure.getDescription());
//		result.setWhen(countMeasure.getWhen());
		result.setScale(dataMeasure.getScale());
		result.setUnitOfMeasure(dataMeasure.getUnitOfMeasure());
		result.setTypeMeasure(dataMeasure.getClass().toString().substring(34));
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
	
	
	public void addDurationMeasure(String logId, @Valid DurationMeasureForm durationMeasureForm) throws Exception {

		Metric result = new Metric();
		DurationMeasure durationMeasure = new DurationMeasure();
		durationMeasure.setName(durationMeasureForm.getName());
		durationMeasure.setDescription(durationMeasureForm.getDescription());
		durationMeasure.setScale(durationMeasureForm.getScale());
		durationMeasure.setUnitOfMeasure(durationMeasureForm.getUnitOfMeasure());
		durationMeasure.setTimeMeasureType(durationMeasureForm.getTimeMeasureType());
		durationMeasure.setFrom(new TimeInstantCondition(durationMeasureForm.getAppliesFrom(), durationMeasureForm.getFrom()));
		durationMeasure.setTo(new TimeInstantCondition(durationMeasureForm.getAppliesTo(), durationMeasureForm.getTo()));
		durationMeasure.setSingleInstanceAggFunction(durationMeasureForm.getSingleInstanceAggFunction());
		List<Measure> measures = compute(durationMeasure, logId);
//		log.getAssignedMetrics().add(result);
		result.setName(durationMeasure.getName());
		Date date = new Date();
		result.setCreationDate(date);
		result.setLogId(logId);
		result.setDescription(durationMeasure.getDescription());
//		result.setFrom(timeMeasure.getFrom());
//		result.setTo(timeMeasure.getTo());
		result.setScale(durationMeasure.getScale());
		result.setTimeMeasureType(durationMeasure.getTimeMeasureType());
		result.setUnitOfMeasure(durationMeasure.getUnitOfMeasure());
		result.setTypeMeasure(durationMeasure.getClass().toString().substring(34));
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
	
	
	
	private RuntimeState state(WhenState text) {
		RuntimeState result = null;
		
		switch (text) {
		case START:
			result = GenericState.START;
			break;
		case END:
			result = GenericState.END;
			break;
		case READY:
			result = BPMNState.READY;
			break;
		case ACTIVE:
			result = BPMNState.ACTIVE;
			break;
		case WITHDRAWN:
			result = BPMNState.WITHDRAWN;
			break;
		case COMPLETING:
			result = BPMNState.COMPLETING;
			break;
		case COMPLETED:
			result = BPMNState.COMPLETED;
			break;
		case FAILING:
			result = BPMNState.FAILING;
			break;
		case FAILED:
			result = BPMNState.FAILED;
			break;
		case TERMINATING:
			result = BPMNState.TERMINATING;
			break;
		case TERMINATED:
			result = BPMNState.TERMINATED;
			break;
		case COMPENSATING:
			result = BPMNState.COMPENSATING;
			break;
		case COMPENSATED:
			result = BPMNState.COMPENSATED;
			break;
		case EXECUTING:
			result = BPMNState.EXECUTING;
			break;
		case DELETED:
			result = BPMNState.DELETED;
			break;
		}		
		
		return result;
		
	}
}
