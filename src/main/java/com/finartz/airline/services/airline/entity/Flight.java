package com.finartz.airline.services.airline.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.finartz.airline.services.route.entity.Route;

@Entity
public class Flight {

	@Id
	@GeneratedValue
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private Integer maxPassengerNumber;
	private String name;
	private Double minPrice; // this is given when flight is added 
	private Double currentPrice; // this is given when flight is added 
	
	@OneToOne
	private Airline company;
	@OneToOne(cascade = { CascadeType.ALL })
	private Route route;

	public Flight() {
	}

	public Flight(Route route, Integer maxPassengerNumber, Double minPrice) {
		this.route = route;
		this.maxPassengerNumber = maxPassengerNumber;
		this.minPrice = minPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date dateTime) {
		this.date = dateTime;
	}

	public Integer getMaxPassengerNumber() {
		return maxPassengerNumber;
	}

	public void setMaxPassengerNumber(Integer maxPassengerNumber) {
		this.maxPassengerNumber = maxPassengerNumber;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Airline getCompany() {
		return company;
	}

	public void setCompany(Airline company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}

}
