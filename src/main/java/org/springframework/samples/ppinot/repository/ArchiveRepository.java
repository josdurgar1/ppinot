package org.springframework.samples.ppinot.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.samples.ppinot.domain.Archive;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRepository extends MongoRepository<Archive, String>{

	public Set<Archive> findByUserId(String userId);

	public Optional<Archive> findByTitle(String title);

	

}
