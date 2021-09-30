package org.springframework.samples.ppinot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.ppinot.security.Authority;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorityRepository extends CrudRepository<Authority, String>{
	
}
