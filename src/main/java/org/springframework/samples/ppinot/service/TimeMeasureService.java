package org.springframework.samples.ppinot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.MeasureRedefined;
import org.springframework.samples.ppinot.domain.Metric;
import org.springframework.samples.ppinot.form.TimeMeasureForm;
import org.springframework.samples.ppinot.util.MeasureList;
import org.springframework.stereotype.Service;

import es.us.isa.ppinot.evaluation.Measure;
import es.us.isa.ppinot.model.base.TimeMeasure;
import es.us.isa.ppinot.model.condition.TimeInstantCondition;
import es.us.isa.ppinot.model.condition.TimeMeasureType;

@Service
public class TimeMeasureService {
	
	@Autowired
	private MeasureList measureList;
	
	public Metric addTimeMeasure(String logId, @Valid TimeMeasureForm timeMeasureForm) throws Exception {

		Metric result = new Metric();
		TimeMeasure timeMeasure = new TimeMeasure();
		timeMeasure.setName(timeMeasureForm.getName());
		timeMeasure.setDescription(timeMeasureForm.getDescription());
		timeMeasure.setScale(timeMeasureForm.getScale());
		timeMeasure.setUnitOfMeasure(timeMeasureForm.getUnitOfMeasure());
		timeMeasure.setTimeMeasureType(timeMeasureForm.getTimeMeasureType());
		if(timeMeasure.getTimeMeasureType().equals(TimeMeasureType.CYCLIC)) {
			timeMeasure.setSingleInstanceAggFunction(timeMeasureForm.getSingleInstanceAggFunction());
		}
		timeMeasure.setFrom(new TimeInstantCondition(timeMeasureForm.getAppliesFrom(), timeMeasureForm.getFrom()));
		timeMeasure.setTo(new TimeInstantCondition(timeMeasureForm.getAppliesTo(), timeMeasureForm.getTo()));
		List<Measure> measures = measureList.compute(timeMeasure, logId);
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
		if(timeMeasure.getTimeMeasureType().equals(TimeMeasureType.CYCLIC)) {
			result.setSingleInstanceAggFunction(timeMeasure.getSingleInstanceAggFunction());
		}
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
		return result;
	}

}
