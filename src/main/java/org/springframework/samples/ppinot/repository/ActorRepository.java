package org.springframework.samples.ppinot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.ppinot.model.Actor;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends CrudRepository<Actor, String>{
	Actor findByUserAccountId(String userAccountId);
	
}
