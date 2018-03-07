package com.xonro.vflow.bases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author louie
 */
@SpringBootApplication
@EnableCaching
public class BasesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasesApplication.class, args);
	}
}
