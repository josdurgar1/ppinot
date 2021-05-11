package org.springframework.samples.ppinot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.samples.ppinot.domain.CountMeasure;

public interface CountMeasureRepository  extends MongoRepository<CountMeasure, String> {



}
