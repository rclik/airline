package com.finartz.airline.services.airline.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.finartz.airline.services.airline.entity.Airline;

@Repository
public interface AirlineRepository extends CrudRepository<Airline, Long> {

}
