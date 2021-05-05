package org.springframework.samples.ppinot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.samples.ppinot.repository.UserRepository;
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@SpringBootApplication()
public class PpinotApplication {

	public static void main(String[] args) {
		SpringApplication.run(PpinotApplication.class, args);
	}

}
