package org.springframework.samples.ppinot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.ppinot.security.Authority;



public interface AuthorityRepository extends CrudRepository<Authority, String>{
	
}
