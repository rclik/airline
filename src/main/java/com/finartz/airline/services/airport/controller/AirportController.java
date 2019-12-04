package com.finartz.airline.services.airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finartz.airline.services.airport.entity.Airport;
import com.finartz.airline.services.airport.service.AirportService;

@RestController
public class AirportController {

	private AirportService service;
	private static final Airport EMPTY_AIRPORT = new Airport();

	@Autowired
	public AirportController(AirportService service) {
		this.service = service;
	}

	@GetMapping("/airport/{id}")
	public Airport getAirport(@PathVariable("id") Long id) {
		return service.getAirportBtId(id).orElse(EMPTY_AIRPORT);
	}

	@PostMapping("/airport")
	public ResponseEntity<Airport> addAirport(@RequestBody Airport airport) {
		return new ResponseEntity<Airport>(service.addAirport(airport), HttpStatus.CREATED);
	}

}
