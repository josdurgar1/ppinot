package org.springframework.samples.ppinot.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.ppinot.repository.UsuarioRepository;
import org.springframework.samples.ppinot.security.LoginService;
import org.springframework.samples.ppinot.security.UserAccount;
import org.springframework.samples.ppinot.security.UserAccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Transactional
public class UsuarioService {

	// Managed repository-----------------------------------------------------

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Supporting services----------------------------------------------------

	@Autowired
	private LoginService loginService;


	// Constructors-----------------------------------------------------------

	public UsuarioService() {
		super();
	}

	// Simple CRUD methods----------------------------------------------------

	public UserAccount create() {

		UserAccount res;

		res = new UserAccount();

		res.setAccountNonExpired(true);
		res.setAccountNonLocked(true);
		res.setCredentialsNonExpired(true);
		res.setEnabled(true);
		res.addRole("ROLE_USER");

		return res;
	}

	public UserAccount findOne(String usuarioId) {
		Assert.notNull(usuarioId);

		UserAccount result;

		result = userAccountRepository.findOne(usuarioId);

		return result;

	}

	public Collection<UserAccount> findAll() {

		Collection<UserAccount> result;

		result = userAccountRepository.findAll();

		return result;
	}

	public void save(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Assert.notNull(userAccount.getUsername());
		Assert.notNull(userAccount.getPassword());
		
		PasswordEncoder encoder = null;
		//encoder = new rawPassword ;

		String password = userAccount.getPassword();
		password = encoder.encode(password);
		userAccount.setPassword(password);

		userAccountRepository.save(userAccount);
	}

	public void delete(UserAccount usuario) {
		Assert.notNull(usuario);

		userAccountRepository.delete(usuario);

	}

	// Other business methods-------------------------------------------------
	public UserAccount findByUserId(String id) {
		UserAccount res;

		res = userAccountRepository.findById(id).get();

		return res;
	}

	public UserAccount findPrincipal() {
		UserAccount res;
		User user;

		user = loginService.getPrincipal2();

		res = userAccountRepository.findByUsername(user.getUsername());

		return res;
	}


	public void modificaUserAccount(UserAccount usuario) {

		UserAccount userAccount = findPrincipal();

		userAccount.setApellidos(usuario.getApellidos());
		userAccount.setEmail(usuario.getEmail());
		userAccount.setNombre(usuario.getNombre());
		userAccount.setTelefono(usuario.getTelefono());

		userAccountRepository.save(userAccount);
	}

}