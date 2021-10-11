package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.Dto.FlightDetailsDto;
import com.cg.exceptions.FlightException;
import com.cg.model.FlightDetails;
import com.cg.repository.FlightRepository;
import com.cg.util.FlightBookingConstants;

@Service
public class FlightDetailsServiceImpl implements FlightDetailsService {
	
	@Autowired
	private FlightRepository flightRepo;

	@Override
	public FlightDetails addFlight(FlightDetailsDto flightDto) {
		
		FlightDetails flight=new FlightDetails();
		flight.setNoOfSeats(flightDto.getNoOfSeats());
		flight.setPrice(flightDto.getPrice());
		FlightDetails fl=flightRepo.saveAndFlush(flight);
		
		return fl;
	}

	@Override
	public String deleteFlight(Integer flightId) throws FlightException {
		Optional<FlightDetails> optFlight=flightRepo.findById(flightId);
		if(!optFlight.isPresent())
			throw new FlightException();
		flightRepo.deleteById(flightId);
		
		return FlightBookingConstants.SUCCESSFULLY_DELETED+flightId;
	}

	@Override
	public FlightDetails viewById(Integer flightId) throws FlightException {
		Optional<FlightDetails> optFlight=flightRepo.findById(flightId);
		if(!optFlight.isPresent())
			throw new FlightException(FlightBookingConstants.FLIGHT_ID_NOT_FOUND);
		return optFlight.get();
	}

	@Override
	public List<FlightDetails> viewAllFlights() throws FlightException {
		List<FlightDetails> lst=flightRepo.findAll();
		if(lst.isEmpty())
			throw new FlightException(FlightBookingConstants.FLIGHT_NOT_FOUND);
		
		return lst;
	}

}
