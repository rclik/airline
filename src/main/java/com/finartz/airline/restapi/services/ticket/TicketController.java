package com.finartz.airline.restapi.services.ticket;

import javax.persistence.EntityNotFoundException;
import javax.websocket.server.PathParam;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finartz.airline.restapi.services.RestApiController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class TicketController extends RestApiController {

	private static final Ticket EMPTY_TICKET = new Ticket();
	private TicketService service;

	public TicketController(TicketService service) {
		this.service = service;
	}

	@ApiOperation(value = "Add ticket to Database", notes = "It adds tickets to the database", response = ResponseEntity.class)
	@PostMapping("/ticket")
	public ResponseEntity<Ticket> buyTicket(@PathParam("flightId") Long flightId,
			@ApiParam(required = true, value = "Ticket to be added") @RequestBody Ticket ticket)
			throws EntityNotFoundException, ConstraintViolationException {
		return new ResponseEntity<Ticket>(service.addTicket(flightId, ticket), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Find ticket by flight id", notes = "It returns the details of the ticket whose id is provided. If does not found then empty ticket is returned", response = Ticket.class)
	@GetMapping("/ticket/{id}")
	public Ticket getTicket(
			@ApiParam(name = "Ticket id", required = true, value = "Ticket id for looking up the ticket") @PathVariable("id") Long ticketId) {
		return service.getTicketById(ticketId).orElse(EMPTY_TICKET);
	}

	@ApiOperation(value = "Delete ticket by flight id", notes = "It cancelsthe ticket whose id is provided", response = Ticket.class)
	@DeleteMapping("/ticket/{id}")
	public void cancelTicket(
			@ApiParam(name = "Ticket id", required = true, value = "Ticket id for cancelling") @PathVariable("id") Long id) {
		service.deleteTicketById(id);
	}

}
