package com.cg.Dto;

import javax.validation.constraints.NotBlank;

import com.cg.util.FlightBookingConstants;

import lombok.Data;

@Data
public class AirlinesDetailsDto {
	
	private Integer airlineId;
	@NotBlank(message = FlightBookingConstants.AIRLINE_NAME_REQUIRED)
	private String airlineName;
	private String airlineLogo;
	
	public Integer getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(Integer airlineId) {
		this.airlineId = airlineId;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getAirlineLogo() {
		return airlineLogo;
	}
	public void setAirlineLogo(String airlineLogo) {
		this.airlineLogo = airlineLogo;
	}
	
	
	
}
