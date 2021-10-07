package org.springframework.samples.ppinot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.samples.ppinot.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}
