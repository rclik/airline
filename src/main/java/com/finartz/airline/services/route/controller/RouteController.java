package com.finartz.airline.services.route.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finartz.airline.services.route.entity.Route;
import com.finartz.airline.services.route.service.RouteService;

@RestController
public class RouteController {

	private RouteService service;
	private static final Route EMPTY_ROUTE = new Route();

	@Autowired
	public RouteController(RouteService service) {
		this.service = service;
	}

	@PostMapping("/route")
	public ResponseEntity<Route> addRoute(@RequestBody Route route) {
		return new ResponseEntity<Route>(service.addRoute(route), HttpStatus.CREATED);
	}

	@GetMapping("/route/{id}")
	public Route getRouteById(@PathVariable("id") Long id) {
		return service.getRouteById(id).orElse(EMPTY_ROUTE);
	}

	@GetMapping("/routes")
	public Iterable<Route> getRouteById() {
		return service.getAllRoutes();
	}

}
