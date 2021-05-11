package org.springframework.samples.ppinot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.ppinot.model.Actor;


public interface ActorRepository extends CrudRepository<Actor, String>{
	Actor findByUserAccountId(String userAccountId);
	
}
