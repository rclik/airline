package com.finartz.airline.restapi.services.route.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.websocket.server.PathParam;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finartz.airline.restapi.services.RestApiController;
import com.finartz.airline.restapi.services.route.entity.Route;
import com.finartz.airline.restapi.services.route.service.RouteService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class RouteController extends RestApiController {

	private RouteService service;
	private static final Route EMPTY_ROUTE = new Route();

	@Autowired
	public RouteController(RouteService service) {
		this.service = service;
	}

	@ApiOperation(value = "Add route to Database", notes = "It adds route to the database", response = ResponseEntity.class)
	@PostMapping("/route")
	public ResponseEntity<Route> addRoute(
			@ApiParam(name = "From airport id", required = true, value = "Airport id that is used for route`s beginning airport") @PathParam("fromAirportId") Long fromAirportId,
			@ApiParam(name = "To airport id", required = true, value = "Airport id that is used for route`s end airport") @PathParam("toAirportId") Long toAirportId)
			throws ConstraintViolationException, EntityNotFoundException {
		return new ResponseEntity<Route>(service.addRoute(fromAirportId, toAirportId), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Find route by route id", notes = "It returns the details of the route whose id is provided. If does not found then empty route is returned", response = Route.class)
	@GetMapping("/route/{id}")
	public Route getRouteById(
			@ApiParam(name = "Route id", required = true, value = "Route id for looking up route") @PathVariable("id") Long id) {
		return service.getRouteById(id).orElse(EMPTY_ROUTE);
	}

	@ApiOperation(value = "Get all routes from database", notes = "Returns Iterable Route objects included in the database", response = List.class)
	@GetMapping("/routes")
	public Iterable<Route> getRouteById() {
		return service.getAllRoutes();
	}

}
