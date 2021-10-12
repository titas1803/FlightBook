package com.cg.service;

import java.util.List;

import com.cg.Dto.FlightDetailsDto;
import com.cg.exceptions.AirlineExceptions;
import com.cg.exceptions.FlightException;
import com.cg.model.FlightDetails;

public interface FlightDetailsService {
	
	public FlightDetails addFlight(FlightDetailsDto flightDto) throws AirlineExceptions;
	public String deleteFlight(Integer flightId) throws FlightException;
	public FlightDetails viewById(Integer flightId) throws FlightException;
	public List<FlightDetails> viewAllFlights() throws FlightException;

}
