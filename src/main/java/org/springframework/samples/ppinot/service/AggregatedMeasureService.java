package org.springframework.samples.ppinot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.util.MeasureList;
import org.springframework.stereotype.Service;

@Service
public class AggregatedMeasureService {
	@Autowired
	private MeasureList measureList;

}
