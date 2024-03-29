package com.finartz.airline.restapi.services.route.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.finartz.airline.restapi.services.airport.entity.Airport;
import com.sun.istack.NotNull;

@Entity
public class Route {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@OneToOne(cascade = { CascadeType.ALL })
	private Airport fromAirport;

	@NotNull
	@OneToOne(cascade = { CascadeType.ALL })
	private Airport toAirport;

	public Route() {
	}

	public Route(Airport fromAirport, Airport toAirport) {
		this.fromAirport = fromAirport;
		this.toAirport = toAirport;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFromAirport(Airport fromAirport) {
		this.fromAirport = fromAirport;
	}

	public void setToAirport(Airport toAirport) {
		this.toAirport = toAirport;
	}

	public Airport getFromAirport() {
		return fromAirport;
	}

	public Airport getToAirport() {
		return toAirport;
	}

}
