package org.springframework.samples.ppinot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.MeasureRedefined;
import org.springframework.samples.ppinot.domain.Metric;
import org.springframework.samples.ppinot.form.CountMeasureForm;
import org.springframework.samples.ppinot.util.MeasureList;
import org.springframework.stereotype.Service;

import es.us.isa.ppinot.evaluation.Measure;
import es.us.isa.ppinot.model.base.CountMeasure;
import es.us.isa.ppinot.model.condition.TimeInstantCondition;

@Service
public class CountMeasureService {
	
	@Autowired
	private MeasureList measureList;

	public Metric addCountMeasure(String logId, CountMeasureForm countMeasureForm) throws Exception {
		Metric result = new Metric();
		CountMeasure countMeasure = new CountMeasure();
		countMeasure.setName(countMeasureForm.getName());
		countMeasure.setDescription(countMeasureForm.getDescription());
		countMeasure.setScale(countMeasureForm.getScale());
		countMeasure.setUnitOfMeasure(countMeasureForm.getUnitOfMeasure());
		countMeasure.setWhen(new TimeInstantCondition(countMeasureForm.getAppliesWhen(), countMeasureForm.getWhen()));
		// countMeasure.setWhen(new
		// TimeInstantCondition(countMeasureForm.getAppliesWhen(),
		// state(countMeasureForm.getWhen())));
		List<Measure> measures = measureList.compute(countMeasure, logId);
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
			r.setValue(m.getValue());
			mR.add(r);
		}
		result.setMeasure(mR);
		return result;

	}
}
