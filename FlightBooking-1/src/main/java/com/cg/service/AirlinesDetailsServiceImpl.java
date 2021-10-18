package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.Dto.AirlinesDetailsDto;
import com.cg.exceptions.NotFoundException;
import com.cg.model.AirlineDetails;
import com.cg.repository.AirlineDetailsRepository;
import com.cg.util.FlightBookingConstants;

@Service
public class AirlinesDetailsServiceImpl implements AirlinesDetailsService{
	
	@Autowired
	private AirlineDetailsRepository airlineRepo;

	@Override
	@Transactional
	public AirlineDetails addAirline(AirlinesDetailsDto airlinedto) {

		AirlineDetails airline = new AirlineDetails();
		airline.setAirLineName(airlinedto.getAirlineName());
		airline.setAirlineLogo(airlinedto.getAirlineLogo());
		AirlineDetails al = airlineRepo.saveAndFlush(airline);
		return al;
	}
	
	@Override
	public String deleteAirline(Integer airlineId) throws NotFoundException {
		Optional<AirlineDetails> optAirline = airlineRepo.findById(airlineId);
		if(!optAirline.isPresent()) {
			throw new NotFoundException(FlightBookingConstants.AIRLINE_NOT_FOUND);
		}
		airlineRepo.deleteById(airlineId);
		return FlightBookingConstants.SUCCESSFULLY_DELETED+airlineId;	
	}
	
	@Override
	public List<AirlineDetails> viewAllAirlines() throws NotFoundException{
		List<AirlineDetails> listOfAirlines = airlineRepo.findAll();
		if(listOfAirlines.isEmpty())
		{
			throw new NotFoundException(FlightBookingConstants.AIRLINE_NOT_FOUND);
		}
		return listOfAirlines;
	}

}
