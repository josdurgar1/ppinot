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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
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
@Document(collection = "owners")
public class Usuario extends Actor{



	@Column(name = "address")
	@NotEmpty
	private String address;

	@Column(name = "city")
	@NotEmpty
	private String city;

	@Column(name = "telephone")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;

//	//
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "username", referencedColumnName = "username")
//	private User user;
//	//

	
	public Usuario(String address, String city, String telephone,String firstName, String lastName, String userAccountId) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.userAccountId=userAccountId;
		this.address = address;
		this.city = city;
		this.telephone = telephone;
	}

//	@Override
//	public String toString() {
//		return new ToStringCreator(this)
//
//				.append("id", this.getId()).append("new", this.isNew()).append("lastName", this.getLastName())
//				.append("firstName", this.getFirstName()).append("address", this.address).append("city", this.city)
//				.append("telephone", this.telephone).toString();
//	}

}
