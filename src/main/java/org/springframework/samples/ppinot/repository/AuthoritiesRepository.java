package org.springframework.samples.ppinot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.samples.ppinot.model.Authorities;



public interface AuthoritiesRepository extends MongoRepository<Authorities, String>{
	
}
