package com.finartz.airline.restapi.services.ticket;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finartz.airline.restapi.services.flight.Flight;
import com.finartz.airline.restapi.services.flight.FlightRepository;

@Service
public class TicketService {

	private TicketRepository ticketRepository;
	private FlightRepository flightRepository;

	@Autowired
	public TicketService(TicketRepository repository) {
		this.ticketRepository = repository;
	}

	@Autowired
	public void setFlightRepository(FlightRepository repository) {
		this.flightRepository = repository;
	}

	public Ticket addTicket(Long flightId, Ticket ticket) throws EntityNotFoundException, ConstraintViolationException {
		// finding flight
		Optional<Flight> optFlight = flightRepository.findById(flightId);
		Flight flight = optFlight
				.orElseThrow(() -> new EntityNotFoundException("There is no flight with given id: " + flightId));

		int currentTicketCount = ticketRepository.countByFlightId(flight.getId());

		// check if there is available ticket, then save its price
		checkForAvailableTicket(flight, currentTicketCount);
		ticket.setPrice(flight.getCurrentPrice());

		// update current price for flight if necessary and set tickets flight
		updateFlightNextPrice(flight, currentTicketCount + 1);
		ticket.setFlight(flight);

		return ticketRepository.save(ticket);
	}

	private void checkForAvailableTicket(Flight flight, int currentTicketCount)
			throws EntityNotFoundException, ConstraintViolationException {
		if (flight.getMaxPassengerNumber() == currentTicketCount) {
			throw new EntityNotFoundException("There is no awailable ticket.");
		}
	}

	private void updateFlightNextPrice(Flight flight, int currentTicketCount) {
		int flightMaxNumber = flight.getMaxPassengerNumber();
		int incrementRate = currentTicketCount * 100 / flightMaxNumber;
		if (incrementRate % 10 == 0) { // need to update current flight price
			Double nextPrice = flight.getMinPrice() + flight.getMinPrice() * incrementRate / 100;
			flight.setCurrentPrice(nextPrice);
		}
	}

	public Optional<Ticket> getTicketById(Long ticketId) {
		return ticketRepository.findById(ticketId);
	}

	public void deleteTicketById(Long ticketId) throws EntityNotFoundException {
		Optional<Ticket> ticket = ticketRepository.findById(ticketId);
		ticket.orElseThrow(() -> {
			throw new EntityNotFoundException("There is no ticket with id: " + ticketId);
		});
		ticketRepository.deleteById(ticketId);
	}

}
