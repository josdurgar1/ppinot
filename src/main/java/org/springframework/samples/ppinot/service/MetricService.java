package org.springframework.samples.ppinot.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.domain.Metric;
import org.springframework.samples.ppinot.form.CountMeasureForm;
import org.springframework.samples.ppinot.form.DataMeasureForm;
import org.springframework.samples.ppinot.form.DerivedSingleInstanceMeasureForm;
import org.springframework.samples.ppinot.form.DurationMeasureForm;
import org.springframework.samples.ppinot.form.TimeMeasureForm;
import org.springframework.samples.ppinot.repository.MetricRepository;
import org.springframework.stereotype.Service;

@Service
public class MetricService {

	@Autowired
	private MetricRepository metricRepository;
	@Autowired
	private TimeMeasureService timeMeasureService;
	@Autowired
	private CountMeasureService countMeasureService;
	@Autowired
	private DataMeasureService dataMeasureService;
	@Autowired
	private DurationMeasureService durationMeasureService;
	@Autowired
	private DerivedSingleInstanceMeasureService derivedSingleInstanceMeasureService;

	public void addTimeMeasure(String logId, @Valid TimeMeasureForm timeMeasureForm) throws Exception {
		Metric result;
		result = timeMeasureService.addTimeMeasure(logId, timeMeasureForm);

		metricRepository.save(result);
	}

	public void addCountMeasure(String logId, CountMeasureForm countMeasureForm) throws Exception {
		Metric result;
		result = countMeasureService.addCountMeasure(logId, countMeasureForm);
		metricRepository.save(result);

	}

	public void addDataMeasure(String logId, DataMeasureForm dataMeasureForm) throws Exception {
		Metric result;
		result = dataMeasureService.addDataMeasure(logId, dataMeasureForm);
		metricRepository.save(result);
	}

	public void addDurationMeasure(String logId, DurationMeasureForm durationMeasureForm) throws Exception {
		Metric result;
		result = durationMeasureService.addDurationMeasure(logId, durationMeasureForm);
		metricRepository.save(result);
	}
	
	
	public void addDerivedSingleInstanceMeasure(String logId, DerivedSingleInstanceMeasureForm derivedSingleInstanceForm) throws Exception{
		Metric result;
		result= derivedSingleInstanceMeasureService.addDerivedSingleInstaceMeasure(logId, derivedSingleInstanceForm);
		metricRepository.save(result);
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

}
