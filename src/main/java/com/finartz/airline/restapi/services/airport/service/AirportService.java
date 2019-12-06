package com.finartz.airline.restapi.services.airport.service;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finartz.airline.restapi.services.airport.entity.Airport;
import com.finartz.airline.restapi.services.airport.repository.AirportRepository;

@Service
public class AirportService {

	private AirportRepository respository;

	@Autowired
	public AirportService(AirportRepository respository) {
		this.respository = respository;
	}

	public Optional<Airport> getAirportBtId(Long id) {
		return respository.findById(id);
	}

	public Airport addAirport(Airport airport) throws ConstraintViolationException {
		return respository.save(airport);
	}
}
