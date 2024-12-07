package com.edu.teamone.backendapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BackendappApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendappApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> System.out.println("hello world");
	}
}

