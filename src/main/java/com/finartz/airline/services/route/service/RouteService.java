package com.finartz.airline.services.route.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finartz.airline.services.airport.entity.Airport;
import com.finartz.airline.services.airport.repository.AirportRepository;
import com.finartz.airline.services.route.entity.Route;
import com.finartz.airline.services.route.repository.RouteRepository;

@Service
public class RouteService {

	private RouteRepository repository;
	private AirportRepository airportRepository;

	@Autowired
	public RouteService(RouteRepository repository) {
		this.repository = repository;
	}

	@Autowired
	public void setAirportRespository(AirportRepository repository) {
		this.airportRepository = repository;
	}

	public Route addRoute(Long fromAirportId, Long toAirportId)
			throws ConstraintViolationException, EntityNotFoundException {
		Airport fromAiport = airportRepository.findById(fromAirportId).orElseThrow(() -> {
			throw new EntityNotFoundException("There is no from airport with given id: " + fromAirportId);
		});
		Airport toAiport = airportRepository.findById(toAirportId).orElseThrow(() -> {
			throw new EntityNotFoundException("There is no to airport with given id: " + toAirportId);
		});

		Route route = new Route(fromAiport, toAiport);
		return repository.save(route);
	}

	public Optional<Route> getRouteById(Long id) {
		return repository.findById(id);
	}

	public Iterable<Route> getAllRoutes() {
		return repository.findAll();
	}

}
