package com.genie.springboot.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.genie.springboot.helloworld","com.genie.springboot.service"})
public class HelloworldApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);
	}
}
