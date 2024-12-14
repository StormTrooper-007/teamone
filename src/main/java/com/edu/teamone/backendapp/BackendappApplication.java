package com.edu.teamone.backendapp;

import com.edu.teamone.backendapp.models.CourseDetails;
import com.edu.teamone.backendapp.security.AppUser;
import com.edu.teamone.backendapp.services.CourseDetailsService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BackendappApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendappApplication.class, args);
	}
}

