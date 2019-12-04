package com.finartz.airline;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.finartz.airline.services.airline.AirlineDataLoader;
import com.finartz.airline.services.airport.AirportDataLoader;
import com.finartz.airline.services.route.RouteDataLoader;

@SpringBootApplication
@EnableJpaRepositories("com.finartz.airline.services")
public class AirlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadAirlineData(AirlineDataLoader dataLoaderComponent) {
		return arg -> dataLoaderComponent.load();
	}

	@Bean
	public CommandLineRunner loadAirportData(AirportDataLoader dataLoaderComponent) {
		return arg -> dataLoaderComponent.load();
	}

	@Bean
	public CommandLineRunner loadRouteData(RouteDataLoader dataLoaderComponent) {
		return arg -> dataLoaderComponent.load();
	}
}
