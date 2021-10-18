package com.cg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Dto.FlightDetailsDto;
import com.cg.exceptions.LoginException;
import com.cg.exceptions.NotFoundException;
import com.cg.exceptions.ValidationException;
import com.cg.model.FlightDetails;
import com.cg.service.FlightDetailsService;
import com.cg.service.LoginService;
import com.cg.util.FlightBookingConstants;
import com.cg.util.SuccessMessage;

@RestController
public class FlightController {

	@Autowired
	public FlightDetailsService flightSer;

	@Autowired
	public LoginService loginSer;

	@PostMapping("addflight")
	public FlightDetails addFlight(@Valid @RequestBody FlightDetailsDto flightDto,
			@RequestHeader("token-id") String tokenid, BindingResult br) throws LoginException, ValidationException, NotFoundException {
		if (loginSer.verifyLogin(tokenid) && loginSer.verifyRole(tokenid)) {
			if (br.hasErrors())
				throw new ValidationException(br.getFieldErrors());
			FlightDetails flightId = flightSer.addFlight(flightDto);
			return flightId;
		}
		throw new LoginException();
	}

	@DeleteMapping("deletebyid/{flightId}")
	public SuccessMessage deleteFlight(@PathVariable("flightId") Integer flightId,
			@RequestHeader("token-id") String tokenid) throws LoginException, NotFoundException {
		if (loginSer.verifyLogin(tokenid) && loginSer.verifyRole(tokenid)) {
			String deletedflight = flightSer.deleteFlight(flightId);
			return new SuccessMessage(deletedflight);
		}
		throw new LoginException(FlightBookingConstants.INVALID_TOKEN);
	}

	@GetMapping("viewbyid/{flightid}")
	public FlightDetails viewbyid(@PathVariable("flightid") Integer flightId,
			@RequestHeader("token-id") String tokenid) throws LoginException, NotFoundException {
		if(loginSer.verifyLogin(tokenid) && loginSer.verifyRole(tokenid)) {
			return flightSer.viewById(flightId);
		}
		throw new LoginException();
		
	}
	
	@GetMapping("viewallflights")
	public List<FlightDetails> viewallflight(@RequestHeader("token-id") String tokenid) throws LoginException, NotFoundException{
		if(loginSer.verifyLogin(tokenid) && loginSer.verifyRole(tokenid)) {
			List<FlightDetails> lst=flightSer.viewAllFlights();
			return lst;
		}
		throw new LoginException();
	}
}
