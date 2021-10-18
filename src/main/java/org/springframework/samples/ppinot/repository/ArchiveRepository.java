package org.springframework.samples.ppinot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.samples.ppinot.domain.Archive;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRepository extends MongoRepository<Archive, String>{

	public List<Archive> findByUserId(String userId);

	

}
