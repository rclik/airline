package com.finartz.airline.services.route.repository;

import org.springframework.data.repository.CrudRepository;

import com.finartz.airline.services.route.entity.Route;

public interface RouteRepository extends CrudRepository<Route, Long> {

}
