package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Dto.AirlinesDetailsDto;
import com.cg.exceptions.LoginException;
import com.cg.exceptions.NotFoundException;
import com.cg.exceptions.ValidationException;
import com.cg.model.AirlineDetails;
import com.cg.service.AirlinesDetailsService;
import com.cg.service.LoginService;
import com.cg.util.SuccessMessage;

@RestController
public class AirlinesController {
	
	@Autowired
	private AirlinesDetailsService airlineService;
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/addairline")
	public AirlineDetails addAirline(@RequestBody AirlinesDetailsDto airlineDto, BindingResult br,
			@RequestHeader("token-id") String tokenid) throws ValidationException, LoginException
	{
		if(loginService.verifyLogin(tokenid) && loginService.verifyRole(tokenid)) {
		if(br.hasErrors())
		{
			throw new ValidationException(br.getFieldErrors());
		}
		AirlineDetails airlines = airlineService.addAirline(airlineDto);
		return airlines;
		}
		throw new LoginException();
	}
	
	@DeleteMapping("/deleteairline/{id}")
	public SuccessMessage deleteAirline(@PathVariable("id") Integer airlineId, @RequestHeader("token-id") String tokenid) throws LoginException, NotFoundException
	{
		if(loginService.verifyLogin(tokenid) && loginService.verifyRole(tokenid)) {
		return new SuccessMessage(airlineService.deleteAirline(airlineId));
		}
		throw new LoginException();
	}
	
	@GetMapping("/getallairlines")
	public List<AirlineDetails> viewAllAirlines(@RequestHeader("token-id") String tokenid) throws LoginException, NotFoundException
	{
		if(loginService.verifyLogin(tokenid)) {
		return airlineService.viewAllAirlines();
		}
		throw new LoginException();
	}
}
