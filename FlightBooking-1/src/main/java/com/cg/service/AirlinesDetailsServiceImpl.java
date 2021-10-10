package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.Dto.AirlinesDetailsDto;
import com.cg.exceptions.AirlineExceptions;
import com.cg.model.AirlineDetails;
import com.cg.repository.AirlineDetailsRepository;

@Service
public class AirlinesDetailsServiceImpl implements AirlinesDetailsService{
	
	@Autowired
	private AirlineDetailsRepository airlineRepo;

	@Override
	public AirlineDetails addAirline(AirlinesDetailsDto airlinedto) {

		AirlineDetails airline = new AirlineDetails();
		airline.setAirLineName(airlinedto.getAirlineName());
		airline.setAirlineLogo(airlinedto.getAirlineLogo());
		AirlineDetails al = airlineRepo.saveAndFlush(airline);
		return al;
	}
	
	@Override
	public String deleteAirline(Integer airlineId) throws AirlineExceptions {
		Optional<AirlineDetails> optAirline = airlineRepo.findById(airlineId);
		if(optAirline.isEmpty()) {
			throw new AirlineExceptions("Airlne Not Found");
		}
		airlineRepo.deleteById(airlineId);
		return "Successfully Deleted "+airlineId;	
	}
	
	@Override
	public List<AirlineDetails> viewAllAirlines() throws AirlineExceptions{
		List<AirlineDetails> listOfAirlines = airlineRepo.findAll();
		if(listOfAirlines.isEmpty())
		{
			throw new AirlineExceptions("No Airlines Found");
		}
		return listOfAirlines;
	}

}
