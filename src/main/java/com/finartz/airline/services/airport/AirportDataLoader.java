package com.finartz.airline.services.airport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finartz.airline.services.airport.entity.Airport;
import com.finartz.airline.services.airport.repository.AirportRepository;

/**
 * 
 * Component is responsible for updating the db when the code run
 *
 */
@Component
public class AirportDataLoader {

	private AirportRepository repository;

	@Autowired
	public AirportDataLoader(AirportRepository repository) {
		this.repository = repository;
	}

	public void load() {
		createInitialAirlines().forEach(airport -> {
			repository.save(airport);
		});
	}

	private List<Airport> createInitialAirlines() {
		List<Airport> list = new ArrayList<Airport>();
		list.add(new Airport("Sabiha Gokcen", "ISTANBUL", "TURKEY"));
		list.add(new Airport("Ataturk", "ISTANBUL", "TURKEY"));
		list.add(new Airport("Esenboga", "ANKARA", "TURKEY"));
		list.add(new Airport("Adnan Menderes", "IZMIR", "TURKEY"));
		return list;
	}

}
