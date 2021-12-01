package org.springframework.samples.ppinot;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.ppinot.model.Role;
import org.springframework.samples.ppinot.model.User;
import org.springframework.samples.ppinot.repository.LogRepository;
import org.springframework.samples.ppinot.repository.MetricRepository;
import org.springframework.samples.ppinot.repository.RoleRepository;
import org.springframework.samples.ppinot.repository.UserRepository;

//@EnableMongoRepositories(basePackageClasses = UsuarioRepository.class)
//@EnableAutoConfiguration
@ComponentScan(basePackages = { "org.springframework.samples.ppinot" })
@SpringBootApplication()
public class PpinotApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository role2Repository;
	@Autowired
	LogRepository archiveRepository;
	@Autowired
	MetricRepository metricRepository;

	public static void main(String[] args) {
		SpringApplication.run(PpinotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		deleteAll();
//		addSampleAdmin();
	}

	public void deleteAll() {
		System.out.println("Deleting all records..");
		userRepository.deleteAll();
		archiveRepository.deleteAll();
		metricRepository.deleteAll();
		System.out.println("Deleted!!");
		
	}

//	public void addSampleData() {
//		System.out.println("Adding sample data");
//		ownerRepository.save(new User("Address1", "City1", "+3465654645", "FirstName1", "LastName1", "1"));
//		ownerRepository.save(new User("Address2", "City2", "+3465654645", "FirstName2", "LastName2", "2"));
//		ownerRepository.save(new User("Address3", "City3", "+3465654645", "FirstName3", "LastName3", "3"));
//		ownerRepository.save(new User("Address4", "City4", "+3465654645", "FirstName4", "LastName4", "4"));
//		countMeasureRepository.save(new CountMeasure("CountMeasure1", "CountMeasure Description 1", new TimeInstantCondition()));
//		System.out.println("Fin Adding sample data");
//	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {

		return args -> {

			Role adminRole = roleRepository.findByRole("ADMIN");
			if (adminRole == null) {
				Role newAdminRole = new Role();
				newAdminRole.setRole("ADMIN");
				roleRepository.save(newAdminRole);
			}
			System.out.println("Adding sample Admin-> admin:admin");
			User u = new User();
			u.setAddress("C/Administrador");
			u.setCity("La Parra");
			u.setEmail("admin@ppinot.es");
			u.setFirstName("Jose");
			u.setLastName("Duran");
			u.setPassword("$2a$10$fCOSZNJYI/MXtc9KjChGZOPMojUmRNKKxXja6ft3DeS1iQST.RDvy");
			u.setTelephone("666777888");
			u.setUsername("admin");
			u.setEnabled(true);
			Role admin2Role = roleRepository.findByRole("ADMIN");
			Set<Role> role = new HashSet<Role>();
			role.add(admin2Role);
			u.setRoles(role);
			userRepository.save(u);
			System.out.println("Added!!");
			
			Role userRole = roleRepository.findByRole("USER");
			if (userRole == null) {
				Role newUserRole = new Role();
				newUserRole.setRole("USER");
				roleRepository.save(newUserRole);
				
				
			}
			System.out.println("Adding sample USER-> user:admin");
			User u2 = new User();
			u2.setAddress("C/Administrador");
			u2.setCity("La Parra");
			u2.setEmail("admin@ppinot.es");
			u2.setFirstName("Jose");
			u2.setLastName("Duran");
			u2.setPassword("$2a$10$2qtGZvl6uMWjvGyOUZZJRejC49d25Rmi8vBKe.lnA4Qn4FWbMCIA.");
			u2.setTelephone("666777888");
			u2.setUsername("user");
			u2.setEnabled(true);
			Role admin3Role = roleRepository.findByRole("USER");
			Set<Role> role2 = new HashSet<Role>();
			role2.add(admin3Role);
			u2.setRoles(role2);
			userRepository.save(u2);
			System.out.println("Added!!");
			System.out.println("Finished...");
		};
		

	}

//	public void addSampleAdmin() {
//		System.out.println("Adding sample Admin-> admin:admin");
//		User u = new User();
//		u.setAddress("C/Administrador");
//		u.setCity("La Parra");
//		u.setEmail("admin@ppinot.es");
//		u.setFirstName("Jose");
//		u.setLastName("Duran");
//		u.setPassword("$2a$10$//y/Bn0u0em.K63iQwRe.OKnDCSVVuZdJJIK0weTyX3Cqj963TR8W");
//		u.setTelephone("666777888");
//		u.setUsername("admin");
//		u.setEnabled(true);
//		Role adminRole = roleRepository.findByRole("ADMIN");
//		Set<Role> role = new HashSet<Role>();
//		role.add(adminRole);
//		u.setRoles(role);
//		userRepository.save(u);
//	}
}
