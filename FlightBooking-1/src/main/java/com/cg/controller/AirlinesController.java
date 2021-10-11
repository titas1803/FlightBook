package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Dto.AirlinesDetailsDto;
import com.cg.exceptions.AirlineExceptions;
import com.cg.exceptions.ValidationException;
import com.cg.model.AirlineDetails;
import com.cg.service.AirlinesDetailsService;
import com.cg.util.SuccessMessage;

@RestController
public class AirlinesController {
	
	@Autowired
	private AirlinesDetailsService airlineService;
	
	@PostMapping("/addairline")
	public AirlineDetails addAirline(@RequestBody AirlinesDetailsDto airlineDto, BindingResult br) throws ValidationException
	{
		if(br.hasErrors())
		{
			throw new ValidationException(br.getFieldErrors());
		}
		AirlineDetails airlines = airlineService.addAirline(airlineDto);
		return airlines;
	}
	
	@DeleteMapping("/deleteairline/{id}")
	public SuccessMessage deleteAirline(@PathVariable("id") Integer airlineId) throws AirlineExceptions
	{
		return new SuccessMessage(airlineService.deleteAirline(airlineId));
	}
	
	@GetMapping("/getallairlines")
	public List<AirlineDetails> viewAllAirlines() throws AirlineExceptions
	{
		return airlineService.viewAllAirlines();
	}
}
