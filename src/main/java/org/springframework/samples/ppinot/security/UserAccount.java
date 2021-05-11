package org.springframework.samples.ppinot.security;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "userAccount")
public class UserAccount {

	@Id
	private String id;

	@Indexed(unique = true)
	private String username;

	private String password;
	private String nombre;
	private String apellidos;
	private String email;
	private String telefono;
	private Collection<Authority> authorities;

	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;

	private Set<String> roles = new HashSet<String>();

	public void addRole(String role) {
		roles.add(role);
	}

}