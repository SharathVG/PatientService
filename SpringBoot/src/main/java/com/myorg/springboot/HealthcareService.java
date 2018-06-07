package com.myorg.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.myorg.springboot.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.myorg.springboot"})
public class HealthcareService {

	public static void main(String[] args) {
		SpringApplication.run(HealthcareService.class, args);
	}
}
