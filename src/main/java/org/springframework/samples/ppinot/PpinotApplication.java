package org.springframework.samples.ppinot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.samples.ppinot.domain.CountMeasure;
import org.springframework.samples.ppinot.model.Usuario;
import org.springframework.samples.ppinot.repository.CountMeasureRepository;
import org.springframework.samples.ppinot.repository.UsuarioRepository;
import org.springframework.samples.ppinot.repository.ActorRepository;

import es.us.isa.ppinot.model.condition.TimeInstantCondition;

@EnableMongoRepositories(basePackageClasses = ActorRepository.class)
@SpringBootApplication()
public class PpinotApplication implements CommandLineRunner{

	@Autowired
	UsuarioRepository ownerRepository;
	@Autowired
	CountMeasureRepository countMeasureRepository;

	public static void main(String[] args) {
		SpringApplication.run(PpinotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		deleteAll();
		addSampleData();
	}

	public void deleteAll() {
		 System.out.println("Deleting all records..");
		 ownerRepository.deleteAll();
		 }
	
	public void addSampleData() {
		System.out.println("Adding sample data");
		ownerRepository.save(new Usuario("Address1", "City1", "+3465654645", "FirstName1", "LastName1", "1"));
		ownerRepository.save(new Usuario("Address2", "City2", "+3465654645", "FirstName2", "LastName2", "2"));
		ownerRepository.save(new Usuario("Address3", "City3", "+3465654645", "FirstName3", "LastName3", "3"));
		ownerRepository.save(new Usuario("Address4", "City4", "+3465654645", "FirstName4", "LastName4", "4"));
		countMeasureRepository.save(new CountMeasure("CountMeasure1", "CountMeasure Description 1", new TimeInstantCondition()));
		System.out.println("Fin Adding sample data");
	}

}
