package com.finartz.airline.services.airline.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.finartz.airline.services.airline.entity.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
	
	int countByFlightId(Long flightId);

}
