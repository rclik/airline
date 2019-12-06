package com.finartz.airline;

import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.finartz.airline.restapi.services.airline.AirlineDataLoader;
import com.finartz.airline.restapi.services.airport.AirportDataLoader;
import com.finartz.airline.restapi.services.route.RouteDataLoader;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories("com.finartz.airline.restapi.services")
@EnableSwagger2
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

	@Bean
	public Docket configuraSwagger() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/api/**")) // main api path
				.apis(RequestHandlerSelectors.basePackage("com.finartz.airline.restapi.services")).build()
				.apiInfo(buildApiInfo());
	}

	private ApiInfo buildApiInfo() {
		return new ApiInfo("Airline API", "Sample Airline For Airline Operations", "1.0", "Free to Use",
				new Contact("Rahman Celik", "rahmancelik@gmail.com", "rahmancelik@gmail.com"), "API Licence",
				"licence_url", Collections.emptyList());
	}
}
