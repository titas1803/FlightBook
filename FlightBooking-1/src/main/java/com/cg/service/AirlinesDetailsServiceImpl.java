package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.Dto.AirlinesDetailsDto;
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
		airlineRepo.saveAndFlush(airline);
		return null;
	}
	
	

}
