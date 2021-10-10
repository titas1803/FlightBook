package com.cg.service;

import java.util.List;

import com.cg.Dto.AirlinesDetailsDto;
import com.cg.exceptions.AirlineExceptions;
import com.cg.model.AirlineDetails;

public interface AirlinesDetailsService {

	public AirlineDetails addAirline(AirlinesDetailsDto airlinedto);

	public String deleteAirline(Integer airlineId) throws AirlineExceptions;

	List<AirlineDetails> viewAllAirlines() throws AirlineExceptions;

}
