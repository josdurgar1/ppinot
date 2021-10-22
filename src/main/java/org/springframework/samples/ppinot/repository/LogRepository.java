package org.springframework.samples.ppinot.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.samples.ppinot.domain.Log;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<Log, String>{

	public Set<Log> findByUserId(String userId);

	public Optional<Log> findByTitle(String title);

	

}
