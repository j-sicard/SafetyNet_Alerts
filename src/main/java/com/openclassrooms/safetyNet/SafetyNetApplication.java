package com.openclassrooms.safetyNet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

@SpringBootApplication
public class SafetyNetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetApplication.class, args);
	}

}
