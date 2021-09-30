/* UserAccountRepository.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package org.springframework.samples.ppinot.security;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

	UserAccount findByUsername(String username);
		
	Optional<UserAccount> findById(String id);

	//UserAccount findOne(String id);
	

}
