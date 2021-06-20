package com.finartz.airline.restapi.services.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finartz.airline.restapi.services.RestApiController;
import com.finartz.airline.restapi.services.airline.entity.Airline;
import com.finartz.airline.restapi.services.airline.service.AirlineService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class AirlineController extends RestApiController {

	private static final Airline EMPTY_AIRLINE = new Airline();

	private AirlineService service;

	@Autowired
	public AirlineController(AirlineService service) {
		this.service = service;
	}

	@ApiOperation(value = "Get all airline companies from database", notes = "Returns list of Airline objects included in the database", response = Iterable.class)
	@GetMapping("/airlines")
	public Iterable<Airline> getAllAirlines() {
		return service.getAllAirlines();
	}

	@ApiOperation(value = "Find airline by airline id", notes = "It returns the details of the airline whose id is provided. If does not found then empty airline is returned", response = Airline.class)
	@GetMapping("/airline/{id}")
	public Airline getAirline(
			@ApiParam(name = "Airline id", required = true, value = "Airline id for looking up the airline") @PathVariable("id") Long id) {
		return service.getAirlineById(id).orElse(EMPTY_AIRLINE);
	}

	@PostMapping("/airline")
	public ResponseEntity<Airline> createAirline(@RequestBody Airline airline) {
		return new ResponseEntity<Airline>(service.addAirline(airline), HttpStatus.CREATED);
	}
}
