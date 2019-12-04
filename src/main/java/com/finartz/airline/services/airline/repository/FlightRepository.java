package com.finartz.airline.services.airline.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.finartz.airline.services.airline.entity.Flight;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
	public Optional<Flight> findByName(String name);
}
