package com.cg.service;

import java.util.List;

import com.cg.Dto.FlightDetailsDto;
import com.cg.exceptions.NotFoundException;
import com.cg.model.FlightDetails;

public interface FlightDetailsService {
	
	public FlightDetails addFlight(FlightDetailsDto flightDto) throws NotFoundException;
	public String deleteFlight(Integer flightId) throws NotFoundException;
	public FlightDetails viewById(Integer flightId) throws NotFoundException;
	public List<FlightDetails> viewAllFlights() throws NotFoundException;

}
