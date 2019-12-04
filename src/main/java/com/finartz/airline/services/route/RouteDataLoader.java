package com.finartz.airline.services.route;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finartz.airline.services.airport.entity.Airport;
import com.finartz.airline.services.route.entity.Route;
import com.finartz.airline.services.route.repository.RouteRepository;

@Component
public class RouteDataLoader {

	private RouteRepository repository;

	@Autowired
	public RouteDataLoader(RouteRepository repository) {
		this.repository = repository;
	}

	public void load() {
		createList().forEach(airport -> {
			repository.save(airport);
		});
	}

	private List<Route> createList() {
		return Stream.of(
				new Route(new Airport("Sabiha Gokcen", "TURKEY", "ISTANBUL"),
						new Airport("Esenboga", "TURKEY", "ANKARA")),
				new Route(new Airport("Ataturk", "TURKEY", "ISTANBUL"),
						new Airport("Adnan Menderes", "TURKEY", "IZMIR")),
				new Route(new Airport("Ataturk", "TURKEY", "ISTANBUL"),
						new Airport("Esenboga", "TURKEY", "ANKARA")),
				new Route(new Airport("Sabiha Gokcen", "TURKEY", "ISTANBUL"), 
						new Airport("Esenboga", "TURKEY", "ANKARA")))
				.collect(Collectors.toList());
	}

}
