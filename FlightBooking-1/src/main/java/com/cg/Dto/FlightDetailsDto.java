package com.cg.Dto;

import javax.validation.constraints.NotNull;

import com.cg.util.FlightBookingConstants;

public class FlightDetailsDto {
	
	private Integer flightId;
	@NotNull(message = FlightBookingConstants.PRICE_REQUIRED)
	private Integer price;
	@NotNull(message = FlightBookingConstants.NO_OF_SEATS_REQUIRED)
	private Integer noOfSeats;
	@NotNull(message = FlightBookingConstants.AIRLINE_ID_REQUIRED)
	private Integer airLineId;
	
	public Integer getFlightId() {
		return flightId;
	}
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
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
	public Integer getAirLineId() {
		return airLineId;
	}
	public void setAirLineId(Integer airLineId) {
		this.airLineId = airLineId;
	}
	
	
}
