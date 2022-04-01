package org.springframework.samples.ppinot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.MeasureRedefined;
import org.springframework.samples.ppinot.domain.Metric;
import org.springframework.samples.ppinot.form.DataMeasureForm;
import org.springframework.samples.ppinot.util.MeasureList;
import org.springframework.stereotype.Service;

import es.us.isa.ppinot.evaluation.Measure;
import es.us.isa.ppinot.model.DataContentSelection;
import es.us.isa.ppinot.model.base.DataMeasure;
import es.us.isa.ppinot.model.condition.Condition;
import es.us.isa.ppinot.model.condition.TimeInstantCondition;

@Service
public class DataMeasureService {

	@Autowired
	private MeasureList measureList;

	public Metric addDataMeasure(String logId, DataMeasureForm dataMeasureForm) throws Exception {

		Metric result = new Metric();
		DataMeasure dataMeasure = new DataMeasure();
		dataMeasure.setName(dataMeasureForm.getName());
		dataMeasure.setDescription(dataMeasureForm.getDescription());
		dataMeasure.setScale(dataMeasureForm.getScale());
		dataMeasure.setUnitOfMeasure(dataMeasureForm.getUnitOfMeasure());
		dataMeasure.setFirst(dataMeasureForm.getFirst());
		Condition precondition = new TimeInstantCondition(dataMeasureForm.getPrecondition(), dataMeasureForm.getWhen());
		dataMeasure.setPrecondition(precondition);
		DataContentSelection dataContentSelection = new DataContentSelection(dataMeasureForm.getSelection(), "");
		dataMeasure.setDataContentSelection(dataContentSelection);
		List<Measure> measures = measureList.compute(dataMeasure, logId);
		result.setName(dataMeasure.getName());
		Date date = new Date();
		result.setCreationDate(date);
		result.setLogId(logId);
		result.setDescription(dataMeasure.getDescription());
//		result.setWhen(dataMeasure.getWhen());
		result.setScale(dataMeasure.getScale());
		result.setUnitOfMeasure(dataMeasure.getUnitOfMeasure());
		result.setTypeMeasure(dataMeasure.getClass().toString().substring(34));
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
