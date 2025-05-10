package com.airtribe.employeetrackingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EmployeetrackingsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeetrackingsystemApplication.class, args);
	}

}
