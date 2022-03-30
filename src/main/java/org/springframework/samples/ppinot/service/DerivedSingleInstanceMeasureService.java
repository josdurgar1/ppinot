package org.springframework.samples.ppinot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.MeasureRedefined;
import org.springframework.samples.ppinot.domain.Metric;
import org.springframework.samples.ppinot.form.DerivedSingleInstanceMeasureForm;
import org.springframework.samples.ppinot.repository.MetricRepository;
import org.springframework.samples.ppinot.util.CreateMeasureType;
import org.springframework.samples.ppinot.util.MeasureList;
import org.springframework.stereotype.Service;

import es.us.isa.ppinot.evaluation.Measure;
import es.us.isa.ppinot.model.MeasureDefinition;
import es.us.isa.ppinot.model.derived.DerivedSingleInstanceMeasure;

@Service
public class DerivedSingleInstanceMeasureService {

	@Autowired
	private MeasureList measureList;
	@Autowired
	private MetricRepository metricRepository;

	public Metric addDerivedSingleInstaceMeasure(String logId, DerivedSingleInstanceMeasureForm derivedSingleInstanceMeasureForm) throws Exception {
		List<Metric> aux= metricRepository.findAll();
		
		MeasureDefinition md= CreateMeasureType.measureType(aux.get(0).getTypeMeasure());
		md.setDescription(aux.get(0).getDescription());
		md.setId(aux.get(0).getId());
		md.setName(aux.get(0).getName());
		md.setScale(aux.get(0).getScale());
		md.setUnitOfMeasure(aux.get(0).getUnitOfMeasure());
		
		Metric result = new Metric();
		DerivedSingleInstanceMeasure derivedSigleInstanceMeasure=new DerivedSingleInstanceMeasure();
		derivedSigleInstanceMeasure.setName(derivedSingleInstanceMeasureForm.getName());
		derivedSigleInstanceMeasure.setDescription(derivedSingleInstanceMeasureForm.getDescription());
		derivedSigleInstanceMeasure.setScale(derivedSingleInstanceMeasureForm.getScale());
		derivedSigleInstanceMeasure.setUnitOfMeasure(derivedSingleInstanceMeasureForm.getUnitOfMeasure());
		derivedSigleInstanceMeasure.setFunction(derivedSingleInstanceMeasureForm.getFunction());
		derivedSigleInstanceMeasure.addUsedMeasure("tresp", md);
		List<Measure> measures = measureList.compute(derivedSigleInstanceMeasure, logId);
		result.setName(derivedSigleInstanceMeasure.getName());
		Date date = new Date();
		result.setCreationDate(date);
		result.setLogId(logId);
		result.setDescription(derivedSigleInstanceMeasure.getDescription());
//		result.setWhen(dataMeasure.getWhen());
		result.setScale(derivedSigleInstanceMeasure.getScale());
		result.setUnitOfMeasure(derivedSigleInstanceMeasure.getUnitOfMeasure());
		result.setTypeMeasure(derivedSigleInstanceMeasure.getClass().toString().substring(34));
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
