package com.finartz.airline.services.airport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.finartz.airline.services.airport.entity.Airport;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {
}
