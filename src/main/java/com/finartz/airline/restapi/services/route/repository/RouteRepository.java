package com.finartz.airline.restapi.services.route.repository;

import org.springframework.data.repository.CrudRepository;

import com.finartz.airline.restapi.services.route.entity.Route;

public interface RouteRepository extends CrudRepository<Route, Long> {

}
