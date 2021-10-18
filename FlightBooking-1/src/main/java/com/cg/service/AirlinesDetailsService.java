package com.cg.service;

import java.util.List;

import com.cg.Dto.AirlinesDetailsDto;
import com.cg.exceptions.NotFoundException;
import com.cg.model.AirlineDetails;

public interface AirlinesDetailsService {

	public AirlineDetails addAirline(AirlinesDetailsDto airlinedto);

	public String deleteAirline(Integer airlineId) throws NotFoundException;

	List<AirlineDetails> viewAllAirlines() throws NotFoundException;

}
