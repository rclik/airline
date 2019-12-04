package com.finartz.airline.services.route.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finartz.airline.services.route.entity.Route;
import com.finartz.airline.services.route.repository.RouteRepository;

@Service
public class RouteService {

	private RouteRepository repository;

	@Autowired
	public RouteService(RouteRepository repository) {
		this.repository = repository;
	}

	public Route addRoute(Route route) {
		return repository.save(route);
	}

	public Optional<Route> getRouteById(Long id) {
		return repository.findById(id);
	}

	public Iterable<Route> getAllRoutes() {
		return repository.findAll();
	}

}
