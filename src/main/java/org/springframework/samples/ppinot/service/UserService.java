package org.springframework.samples.ppinot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.model.Role;
import org.springframework.samples.ppinot.model.User;
import org.springframework.samples.ppinot.repository.RoleRepository;
import org.springframework.samples.ppinot.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService  {

	// Managed repository-----------------------------------------------------

	
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User findUserByEmail(String email) {
	    return userRepository.findByEmail(email);
	}
	
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public void saveUser(User user) {
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    user.setEnabled(true);
	    Role userRole = roleRepository.findByRole("USER");
	    user.setRoles(new HashSet<>(Arrays.asList(userRole)));
	    userRepository.save(user);
	}
	
	public void saveAdmin(User user) {
		 user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		 user.setEnabled(true);
		 Role userRole = roleRepository.findByRole("ADMIN");
		    user.setRoles(new HashSet<>(Arrays.asList(userRole)));
		    userRepository.save(user);
	}
	
	public UserDetails  loadUserByUsername(String username) throws UsernameNotFoundException {

	    User user = userRepository.findByUsername(username);
	    if(user != null) {
	        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
	        return buildUserForAuthentication(user, authorities);
	    } else {
	        throw new UsernameNotFoundException("username not found");
	    }
	}
	
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    userRoles.forEach((role) -> {
	        roles.add(new SimpleGrantedAuthority(role.getRole()));
	    });

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}
	
	private UserDetails  buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
	    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

	public void addSampleAdmin() {
		User u=new User();
		u.setAddress("C/Administrador");
		u.setCity("La Parra");
		u.setEmail("admin@ppinot.es");
		u.setFirstName("Jose");
		u.setLastName("Duran");
		u.setPassword("$2a$10$//y/Bn0u0em.K63iQwRe.OKnDCSVVuZdJJIK0weTyX3Cqj963TR8W");
		u.setTelephone("666777888");
		u.setUsername("admin");
		userRepository.save(u);
		
	}
}