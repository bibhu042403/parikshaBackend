package com.example.pariksha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ParikshaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParikshaApplication.class, args);
	}

}
