package org.springframework.samples.ppinot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.samples.ppinot.domain.CountMeasure;
import org.springframework.stereotype.Repository;

@Repository
public interface CountMeasureRepository  extends MongoRepository<CountMeasure, String> {



}
