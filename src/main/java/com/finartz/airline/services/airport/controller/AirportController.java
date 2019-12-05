package com.finartz.airline.services.airport.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finartz.airline.services.RestApiController;
import com.finartz.airline.services.airport.entity.Airport;
import com.finartz.airline.services.airport.service.AirportService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class AirportController extends RestApiController {

	private AirportService service;
	private static final Airport EMPTY_AIRPORT = new Airport();

	@Autowired
	public AirportController(AirportService service) {
		this.service = service;
	}

	@ApiOperation(value = "Find airport by airport id", notes = "It returns the details of the aiport whose id is provided. If does not found then empty airport is returned", response = Airport.class)
	@GetMapping("/airport/{id}")
	public Airport getAirport(
			@ApiParam(name = "Airport id", required = true, value = "Airport id for looking up the airport") @PathVariable("id") Long id) {
		return service.getAirportBtId(id).orElse(EMPTY_AIRPORT);
	}

	@ApiOperation(value = "Add airport to Database", notes = "It adds airport to the database", response = ResponseEntity.class)
	@PostMapping("/airport")
	public ResponseEntity<Airport> addAirport(
			@ApiParam(required = true, value = "Airport to be added") @RequestBody Airport airport)
			throws ConstraintViolationException {
		return new ResponseEntity<Airport>(service.addAirport(airport), HttpStatus.CREATED);
	}

}
