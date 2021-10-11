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

import com.cg.Dto.BookingDetailsDto;
import com.cg.exceptions.BookingExceptions;
import com.cg.exceptions.ScheduleException;
import com.cg.exceptions.UserException;
import com.cg.model.BookingDetails;
import com.cg.service.BookingDetailsService;
import com.cg.util.FlightBookingConstants;
import com.cg.util.SuccessMessage;

@RestController
public class BookingController {
	
	@Autowired
	private BookingDetailsService bookingService;
	
	@PostMapping("/addbooking")
    public BookingDetails addBooking(@RequestBody BookingDetailsDto bookingDto, BindingResult br ) throws UserException, ScheduleException, BookingExceptions
    {
		if(br.hasErrors())
		{
			throw new BookingExceptions(br.getFieldErrors());
		}
        BookingDetails bookings = bookingService.addBooking(bookingDto);
        return bookings;
    }
	
	@DeleteMapping("/deletebooking/{id}")
	public SuccessMessage deletebooking(@PathVariable("id") Integer ticketid) throws BookingExceptions
	{
		return new SuccessMessage(bookingService.deleteBooking(ticketid));
	}
	
	@GetMapping("/getbookingbyid/{id}")
	public BookingDetails viewBookingById(@PathVariable("id") Integer ticketId) throws BookingExceptions
	{
		return bookingService.viewById(ticketId);
	}
	
	@GetMapping("/getallbookings")
	public List<BookingDetails> viewAllBookings() throws BookingExceptions
	{
		return bookingService.viewAllBookings();
	}
	
}
