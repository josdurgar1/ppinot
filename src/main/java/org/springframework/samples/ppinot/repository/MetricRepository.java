package org.springframework.samples.ppinot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.samples.ppinot.domain.Metric;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends MongoRepository<Metric, String>{
	
	public List<Metric> findByLogId(String logId);

}
