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

import com.cg.Dto.FlightDetailsDto;
import com.cg.exceptions.FlightException;
import com.cg.exceptions.LoginException;
import com.cg.exceptions.ValidationException;
import com.cg.model.FlightDetails;
import com.cg.service.FlightDetailsService;
import com.cg.service.LoginService;
import com.cg.util.SuccessMessage;

public class FlightController {

	@Autowired
	public FlightDetailsService flightSer;

	@Autowired
	public LoginService loginSer;

	@PostMapping("addflight")
	public FlightDetails addFlight(@Valid @RequestBody FlightDetailsDto flightDto,
			@RequestHeader("token-ID") String tokenid, BindingResult br) throws FlightException, LoginException, ValidationException {
		if (loginSer.verifyLogin(tokenid)) {
			if (br.hasErrors())
				throw new ValidationException(br.getFieldErrors());
			FlightDetails flightId = flightSer.addFlight(flightDto);

			return flightId;
		}
		throw new LoginException();
	}

	@DeleteMapping("deletebyid/{flightId}")
	public SuccessMessage deleteFlight(@Valid @PathVariable("flightId") Integer flightid,
			@RequestHeader("token-id") String tokenid, BindingResult br) throws LoginException, FlightException, ValidationException {
		if (loginSer.verifyLogin(tokenid)) {
			if (br.hasErrors())
				throw new ValidationException(br.getFieldErrors());
			String deletedflight = flightSer.deleteFlight(flightid);
			return new SuccessMessage(deletedflight);
		}
		throw new LoginException();
	}

	@GetMapping("viewbyid/{flightid}")
	public FlightDetails viewbyid(@Valid @PathVariable("flightid") Integer flightId,
			@RequestHeader("token-id") String tokenId, BindingResult br) throws FlightException, LoginException, ValidationException {
		if(loginSer.verifyLogin(tokenId)) {
			if(br.hasErrors())
				throw new ValidationException(br.getFieldErrors());
			return flightSer.viewById(flightId);
		}
		throw new LoginException();

	}
	
	@GetMapping("viewallflights")
	public List<FlightDetails> viewallflight(@RequestHeader("token-id") String tokenid) throws LoginException, FlightException{
		if(loginSer.verifyLogin(tokenid)) {
			List<FlightDetails> lst=flightSer.viewAllFlights();
			return lst;
		}
		throw new LoginException();
	}
}
