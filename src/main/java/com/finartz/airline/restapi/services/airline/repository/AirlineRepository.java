package com.finartz.airline.restapi.services.airline.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.finartz.airline.restapi.services.airline.entity.Airline;

@Repository
public interface AirlineRepository extends CrudRepository<Airline, Long> {

}
