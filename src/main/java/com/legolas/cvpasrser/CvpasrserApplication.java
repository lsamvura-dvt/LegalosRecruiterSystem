package com.legolas.cvpasrser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CvpasrserApplication {

	public static void main(String[] args) {
		System.out.println("Swagger-UI  http://localhost:8082/swagger-ui/index.html");

		SpringApplication.run(CvpasrserApplication.class, args);

	}

}
