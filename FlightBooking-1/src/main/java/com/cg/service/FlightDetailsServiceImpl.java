package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.Dto.FlightDetailsDto;
import com.cg.exceptions.NotFoundException;
import com.cg.model.AirlineDetails;
import com.cg.model.FlightDetails;
import com.cg.repository.AirlineDetailsRepository;
import com.cg.repository.FlightRepository;
import com.cg.util.FlightBookingConstants;

@Service
public class FlightDetailsServiceImpl implements FlightDetailsService {
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private AirlineDetailsRepository airlineRepo;

	@Override
	@Transactional
	public FlightDetails addFlight(FlightDetailsDto flightDto) throws NotFoundException {
		
		Optional<AirlineDetails> optAirline = airlineRepo.findById(flightDto.getAirLineId());
		if(!optAirline.isPresent())
		{
			throw new NotFoundException(FlightBookingConstants.AIRLINE_ID_NOT_FOUND);
		}
		
		FlightDetails flight=new FlightDetails();
		flight.setNoOfSeats(flightDto.getNoOfSeats());
		flight.setPrice(flightDto.getPrice());
		flight.setAirLineDetails(optAirline.get());
		FlightDetails fl=flightRepo.saveAndFlush(flight);
		
		return fl;
	}

	@Override
	public String deleteFlight(Integer flightId) throws NotFoundException {
		Optional<FlightDetails> optFlight=flightRepo.findById(flightId);
		if(!optFlight.isPresent())
			throw new NotFoundException(FlightBookingConstants.FLIGHT_ID_NOT_FOUND);
		flightRepo.deleteById(flightId);
		
		return FlightBookingConstants.SUCCESSFULLY_DELETED+flightId;
	}

	@Override
	public FlightDetails viewById(Integer flightId) throws NotFoundException {
		Optional<FlightDetails> optFlight=flightRepo.findById(flightId);
		if(!optFlight.isPresent())
			throw new NotFoundException(FlightBookingConstants.FLIGHT_ID_NOT_FOUND);
		return optFlight.get();
	}

	@Override
	public List<FlightDetails> viewAllFlights() throws NotFoundException {
		List<FlightDetails> lst=flightRepo.findAll();
		if(lst.isEmpty())
			throw new NotFoundException(FlightBookingConstants.FLIGHT_NOT_FOUND);
		
		return lst;
	}

}
