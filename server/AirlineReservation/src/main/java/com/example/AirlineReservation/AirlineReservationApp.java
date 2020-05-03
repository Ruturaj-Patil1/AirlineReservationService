package com.example.AirlineReservation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AirlineReservationApp {
	
	private static Logger logger = LogManager.getLogger(AirlineReservationApp.class);
	public static void main(String[] args) {
		logger.info("Starting Spring Boot application..");
		
		SpringApplication.run(AirlineReservationApp.class, args);
		
	}
}
