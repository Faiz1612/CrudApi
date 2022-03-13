package com.faiz.crud.api;

import com.faiz.crud.api.repositories.UserRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CrudApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApiApplication.class, args);
	}

}
