package org.springframework.samples.ppinot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.samples.ppinot.model.User;


public interface UserRepository extends MongoRepository<User, String>{
	
}
