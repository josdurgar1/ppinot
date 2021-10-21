
package org.springframework.samples.ppinot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.samples.ppinot.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public List<User> findByAddress(String address);

	public User findByEmail(String email);

	public Optional<User> findByUsername(String username);



}
