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

import com.cg.Dto.BookingDetailsDto;
import com.cg.exceptions.LoginException;
import com.cg.exceptions.NotFoundException;
import com.cg.exceptions.ScheduleException;
import com.cg.exceptions.UserException;
import com.cg.exceptions.ValidationException;
import com.cg.model.BookingDetails;
import com.cg.service.BookingDetailsService;
import com.cg.service.LoginService;
import com.cg.util.SuccessMessage;

@RestController
public class BookingController {

	@Autowired
	private BookingDetailsService bookingService;

	@Autowired
	private LoginService loginService;

	@PostMapping("/addbooking")
	public BookingDetails addBooking(@Valid @RequestBody BookingDetailsDto bookingDto, BindingResult br,
			@RequestHeader("token-id") String tokenid)
			throws UserException, ScheduleException, ValidationException, LoginException, NotFoundException {
		if (loginService.verifyLogin(tokenid)) {
			if (br.hasErrors()) {
				throw new ValidationException(br.getFieldErrors());
			}
			BookingDetails bookings = bookingService.addBooking(bookingDto);
			return bookings;
		}
		throw new LoginException();
	}

	@DeleteMapping("/deletebooking/{id}")
	public SuccessMessage deletebooking(@PathVariable("id") Integer ticketid, @RequestHeader("token-id") String tokenid)
			throws LoginException, NotFoundException {
		if (loginService.verifyLogin(tokenid)) {
			return new SuccessMessage(bookingService.deleteBooking(ticketid));
		}
		throw new LoginException();
	}

	@GetMapping("/getbookingbyid/{id}")
	public BookingDetails viewBookingById(@PathVariable("id") Integer ticketId,
			@RequestHeader("token-id") String tokenid) throws LoginException, NotFoundException {
		if (loginService.verifyLogin(tokenid)) {
			return bookingService.viewById(ticketId);
		}
		throw new LoginException();
	}

	@GetMapping("/getallbookings")
	public List<BookingDetails> viewAllBookings(@RequestHeader("token-id") String tokenid)
			throws LoginException, NotFoundException {
		if (loginService.verifyLogin(tokenid) && loginService.verifyRole(tokenid)) {
			return bookingService.viewAllBookings();
		}
		throw new LoginException();
	}

}
