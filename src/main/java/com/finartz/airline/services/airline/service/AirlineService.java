package com.finartz.airline.services.airline.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finartz.airline.services.airline.entity.Airline;
import com.finartz.airline.services.airline.entity.Flight;
import com.finartz.airline.services.airline.entity.Ticket;
import com.finartz.airline.services.airline.repository.AirlineRepository;
import com.finartz.airline.services.airline.repository.FlightRepository;
import com.finartz.airline.services.airline.repository.TicketRepository;
import com.finartz.airline.services.route.entity.Route;
import com.finartz.airline.services.route.repository.RouteRepository;

@Service
public class AirlineService {

	private AirlineRepository repository;
	private RouteRepository routeRepository;
	private FlightRepository flightRepository;
	private TicketRepository ticketRepository;

	@Autowired
	public AirlineService(AirlineRepository repository) {
		this.repository = repository;
	}

	@Autowired
	public void setRouteRepository(RouteRepository repository) {
		this.routeRepository = repository;
	}

	@Autowired
	public void setFlightRepository(FlightRepository repository) {
		this.flightRepository = repository;
	}

	@Autowired
	public void setTicketRepository(TicketRepository repository) {
		this.ticketRepository = repository;
	}

	public Airline addAirline(Airline airline) {
		return repository.save(airline);
	}

	public Iterable<Airline> getAllAirlines() {
		return repository.findAll();
	}

	public Optional<Airline> getAirlineById(Long id) {
		return repository.findById(id);
	}

	public Flight addFlight(Long airlineId, Long routeId, Flight flight) throws EntityNotFoundException {
		Optional<Airline> optionalAirline = repository.findById(airlineId);
		Airline airline = optionalAirline
				.orElseThrow(() -> new EntityNotFoundException("There is no airline with given id: " + airlineId));
		flight.setCompany(airline);

		Optional<Route> optionalRoute = routeRepository.findById(routeId);
		Route route = optionalRoute
				.orElseThrow(() -> new EntityNotFoundException("There is no route with given id: " + routeId));
		flight.setRoute(route);

		flightRepository.save(flight);
		return flight;
	}

	public Optional<Flight> getFlightById(Long flightId) {
		return flightRepository.findById(flightId);
	}

	public Optional<Flight> getFlightByName(String name) {
		return flightRepository.findByName(name);
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

	private void checkForAvailableTicket(Flight flight, int currentTicketCount) throws EntityNotFoundException , ConstraintViolationException{
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

	public void deleteTicketById(Long ticketId) {
		ticketRepository.deleteById(ticketId);
	}
}
