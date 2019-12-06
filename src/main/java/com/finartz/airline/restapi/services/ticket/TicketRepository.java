package com.finartz.airline.restapi.services.ticket;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
	int countByFlightId(Long flightId);
}
