package org.springframework.samples.ppinot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.MeasureRedefined;
import org.springframework.samples.ppinot.domain.Metric;
import org.springframework.samples.ppinot.form.DurationMeasureForm;
import org.springframework.samples.ppinot.util.MeasureList;
import org.springframework.stereotype.Service;

import es.us.isa.ppinot.evaluation.Measure;
import es.us.isa.ppinot.model.base.DurationMeasure;
import es.us.isa.ppinot.model.condition.TimeInstantCondition;

@Service
public class DurationMeasureService {
	
	@Autowired
	private MeasureList measureList;

	public Metric addDurationMeasure(String logId, @Valid DurationMeasureForm durationMeasureForm) throws Exception {

		Metric result = new Metric();
		DurationMeasure durationMeasure = new DurationMeasure();
		durationMeasure.setName(durationMeasureForm.getName());
		durationMeasure.setDescription(durationMeasureForm.getDescription());
		durationMeasure.setScale(durationMeasureForm.getScale());
		durationMeasure.setUnitOfMeasure(durationMeasureForm.getUnitOfMeasure());
		durationMeasure.setTimeMeasureType(durationMeasureForm.getTimeMeasureType());
		durationMeasure
				.setFrom(new TimeInstantCondition(durationMeasureForm.getAppliesFrom(), durationMeasureForm.getFrom()));
		durationMeasure
				.setTo(new TimeInstantCondition(durationMeasureForm.getAppliesTo(), durationMeasureForm.getTo()));
		durationMeasure.setSingleInstanceAggFunction(durationMeasureForm.getSingleInstanceAggFunction());
		List<Measure> measures = measureList.compute(durationMeasure, logId);
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
		return result;
	}
}
