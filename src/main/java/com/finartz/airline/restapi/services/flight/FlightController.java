package com.finartz.airline.restapi.services.flight;

import javax.persistence.EntityNotFoundException;
import javax.websocket.server.PathParam;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finartz.airline.restapi.services.RestApiController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class FlightController extends RestApiController {

	private static final Flight EMPTY_FLIGHT = new Flight();
	private FlightService service;

	public FlightController(FlightService service) {
		this.service = service;
	}

	@ApiOperation(value = "Creates the Flight", notes = "It creates the flight and if succeded returns created flight", response = ResponseEntity.class)
	@PostMapping("/flight")
	public ResponseEntity<Flight> createFlight(
			@ApiParam(name = "Airline id", required = true, value = "Airline id for the given flight") @PathParam("airlineId") Long airlineId,
			@ApiParam(name = "Route id", required = true, value = "Route id for the given flight") @PathParam("routeId") Long routeId,
			@ApiParam(name = "Flight details", required = true, value = "Details for flight to be created") @RequestBody Flight flight)
			throws EntityNotFoundException, ConstraintViolationException {
		return new ResponseEntity<Flight>(service.addFlight(airlineId, routeId, flight), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Find flight by flight id", notes = "It returns the details of the flight whose id is provided. If does not found then empty flight is returned", response = Flight.class)
	@GetMapping("/flight/{id}")
	public Flight getFlightById(
			@ApiParam(name = "Flight id", required = true, value = "Flight id for looking up the flight") @PathVariable("id") Long id) {
		return service.getFlightById(id).orElse(EMPTY_FLIGHT);
	}

	@ApiOperation(value = "Find flight by flight name", notes = "It returns the details of the flight whose name is provided. If does not found then empty flight is returned", response = Flight.class)
	@GetMapping("/flight")
	public Flight getFlightByName(
			@ApiParam(name = "Flight name", required = true, value = "Flight id for looking up the flight") @PathParam("name") String name) {
		return service.getFlightByName(name).orElse(EMPTY_FLIGHT);
	}

}
