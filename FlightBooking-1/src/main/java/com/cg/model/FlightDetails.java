package com.cg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "Flight_Details")
public class FlightDetails {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq3" )
	@SequenceGenerator(name="seq3", sequenceName = "flight_sequence", allocationSize = 1, initialValue = 100)
	private Integer flightId;
	private Integer price;
	private Integer noOfSeats;
	
	@ManyToOne
	private AirlineDetails airLineDetails;
	
	public Integer getFlightId() {
		return flightId;
	}
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public AirlineDetails getAirLineDetails() {
		return airLineDetails;
	}
	public void setAirLineDetails(AirlineDetails airLineDetails) {
		this.airLineDetails = airLineDetails;
	}
	
	
	
} 
