package com.finartz.airline.services.airline.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finartz.airline.services.airline.entity.Airline;
import com.finartz.airline.services.airline.entity.Flight;
import com.finartz.airline.services.airline.entity.Ticket;
import com.finartz.airline.services.airline.service.AirlineService;

@RestController
public class AirlineController {

	private static final Airline EMPTY_AIRLINE = new Airline();
	private static final Flight EMPTY_FLIGHT = new Flight();
	private static final Ticket EMPTY_TICKET = new Ticket();

	private AirlineService service;

	@Autowired
	public AirlineController(AirlineService service) {
		this.service = service;
	}

	@GetMapping("/airlines")
	public Iterable<Airline> getAllAirlines() {
		return service.getAllAirlines();
	}

	@GetMapping("/airline/{id}")
	public Airline getAirline(@PathVariable("id") Long id) {
		return service.getAirlineById(id).orElse(EMPTY_AIRLINE);
	}

	@PostMapping("/airline")
	public ResponseEntity<Airline> createAirline(@RequestBody Airline airline) {
		return new ResponseEntity<Airline>(service.addAirline(airline), HttpStatus.CREATED);
	}

	@PostMapping("/airline/{id}/flight")
	public ResponseEntity<Flight> createFlight(@PathVariable("id") Long id, @RequestBody Flight flight,
			@PathParam("routeId") Long routeId) throws Exception {
		return new ResponseEntity<Flight>(service.addFlight(id, routeId, flight), HttpStatus.CREATED);
	}

//	@GetMapping("/flight/{id}")
//	public Flight getFlight(@PathVariable("id") Long id) {
//		return service.getFlightById(id).orElse(EMPTY_FLIGHT);
//	}

	@GetMapping("/flight/{name}")
	public Flight getFlightByName(@PathVariable("name") String name) {
		return service.getFlightByName(name).orElse(EMPTY_FLIGHT);
	}

	@PostMapping("/ticket")
	public ResponseEntity<Ticket> buyTicket(@PathParam("flightId") Long flightId, @RequestBody Ticket ticket)
			throws Exception {
		return new ResponseEntity<Ticket>(service.addTicket(flightId, ticket), HttpStatus.CREATED);
	}

	@GetMapping("/ticket/{id}")
	public Ticket getTicket(@PathVariable("id") Long ticketId) {
		return service.getTicketById(ticketId).orElse(EMPTY_TICKET);
	}

	@DeleteMapping("/ticket/{id}")
	public void cancelTicket(@PathVariable("id") Long id) {
		service.deleteTicketById(id);
	}

}
