package com.gocity.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gocity.demo.entity.Attractions;
import com.gocity.demo.entity.Destinations;
import com.gocity.demo.repository.AttractionRepository;
import com.gocity.demo.repository.DestinationRepository;

@SpringBootApplication
@EnableAutoConfiguration
public class TechTestBackendApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TechTestBackendApplication.class);

	public static void main(String[] args) {
		LOGGER.info("main - SpringApplication.run: begin");
			SpringApplication.run(TechTestBackendApplication.class, args);
	}
	
	public CommandLineRunner mappingDemo(DestinationRepository destinationRepository, AttractionRepository attractionRepository) 
	{
		LOGGER.info("main - SpringApplication.mappingDemo: begin");
	        return args -> {
	        	Destinations destinations = new Destinations("Napoli", "Napoli.google.map.gmail.com");
				destinationRepository.save(destinations);
			
				attractionRepository.save(new Attractions("Napoli", "info.Napoli@gmail.com", "060606", 1, 0, "Museum", destinations));
				attractionRepository.save(new Attractions("Napoli", "info.Napoli@gmail.com", "060606", 1, 1, "Colosseoum", destinations));
				attractionRepository.save(new Attractions("Napoli", "info.Napoli@gmail.com", "060606", 1, 2, "Library", destinations));
	       
				destinations = new Destinations("Milan", "Milan.google.map.gmail.com");
				destinationRepository.save(destinations);
				
				attractionRepository.save(new Attractions("Duomo", "info.milan@gmail.com", "060606", 1, 0, "Catthedral", destinations));
				attractionRepository.save(new Attractions("900 Gallery", "info.milan@gmail.com", "060606", 1, 1, "Gallery", destinations));
			};
	    }
	

}
