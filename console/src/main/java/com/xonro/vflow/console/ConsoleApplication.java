package com.xonro.vflow.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Alex
 * @date 2018/1/23
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
@ComponentScan(basePackages = "com.xonro.vflow")
public class ConsoleApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ConsoleApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsoleApplication.class, args);
	}
}
