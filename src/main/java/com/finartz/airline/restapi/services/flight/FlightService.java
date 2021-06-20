package com.finartz.airline.restapi.services.flight;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finartz.airline.restapi.services.airline.entity.Airline;
import com.finartz.airline.restapi.services.airline.repository.AirlineRepository;
import com.finartz.airline.restapi.services.route.entity.Route;
import com.finartz.airline.restapi.services.route.repository.RouteRepository;

@Service
public class FlightService {

	private FlightRepository flightRepository;
	private AirlineRepository airlineRepository;
	private RouteRepository routeRepository;

	@Autowired
	public FlightService(FlightRepository repository) {
		this.flightRepository = repository;
	}

	@Autowired
	public void setAirlineRepository(AirlineRepository repository) {
		this.airlineRepository = repository;
	}

	@Autowired
	public void setRouteRepository(RouteRepository repository) {
		this.routeRepository = repository;
	}

	public Flight addFlight(Long airlineId, Long routeId, Flight flight) throws EntityNotFoundException {
		Optional<Airline> optionalAirline = airlineRepository.findById(airlineId);
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
}
