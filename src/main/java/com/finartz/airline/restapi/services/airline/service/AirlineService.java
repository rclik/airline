package com.finartz.airline.restapi.services.airline.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finartz.airline.restapi.services.airline.entity.Airline;
import com.finartz.airline.restapi.services.airline.repository.AirlineRepository;

@Service
public class AirlineService {

	private AirlineRepository repository;

	@Autowired
	public AirlineService(AirlineRepository repository) {
		this.repository = repository;
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
}
