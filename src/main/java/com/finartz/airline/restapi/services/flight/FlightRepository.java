package com.finartz.airline.restapi.services.flight;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
	public Optional<Flight> findByName(String name);
}
