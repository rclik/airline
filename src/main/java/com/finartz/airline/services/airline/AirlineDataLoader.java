package com.finartz.airline.services.airline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finartz.airline.services.airline.entity.Airline;
import com.finartz.airline.services.airline.repository.AirlineRepository;

/**
 * 
 * Component is responsible for updating the db when the code run
 *
 */
@Component
public class AirlineDataLoader {

	private AirlineRepository repository;

	@Autowired
	public AirlineDataLoader(AirlineRepository repository) {
		this.repository = repository;
	}

	public void load() {
		createInitialAirlines().forEach(airline -> {
			repository.save(airline);
		});
	}

	private List<Airline> createInitialAirlines() {
		List<Airline> list = new ArrayList<Airline>();
		list.add(new Airline("Turkish Airlines"));
		list.add(new Airline("Katart Airways"));
		list.add(new Airline("Ryanair"));
		return list;
	}

}
