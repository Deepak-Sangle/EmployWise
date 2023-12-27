package com.groupware.employwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class EmployWiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployWiseApplication.class, args);
	}

}
