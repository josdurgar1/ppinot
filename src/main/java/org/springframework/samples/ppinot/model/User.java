/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.ppinot.model;

import java.util.Set;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.core.style.ToStringCreator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * Simple JavaBean domain object representing an owner.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
@Getter
@Setter
@Document(collection = "users")
public class User{

	@Id
	private String id;

	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	
	@NotEmpty
	@Size(min = 4, max = 32)
	@Column(unique = true)
	private String username;
	
	@NotEmpty
	@Size(min = 4, max = 32)
	private String password;
	
	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@NotEmpty
	@Email
	private String email;

	
	@NotEmpty
	private String address;

	
	@NotEmpty
	private String city;

	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;
	
	
	
	
	private boolean enabled;
	@DBRef
	private Set<Role> roles;

	public boolean isNew() {
		return this.id == null;
	}

//	//
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "username", referencedColumnName = "username")
//	private User user;
//	//

	
//	public Usuario(String address, String city, String telephone,String firstName, String lastName, String userAccountId) {
//		this.firstName=firstName;
//		this.lastName=lastName;
//		this.userAccountId=userAccountId;
//		this.address = address;
//		this.city = city;
//		this.telephone = telephone;
//	}

	@Override
	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId()).append("new", this.isNew()).append("lastName", this.getLastName())
				.append("firstName", this.getFirstName()).append("address", this.address).append("city", this.city)
				.append("telephone", this.telephone).toString();
	}
	

}
