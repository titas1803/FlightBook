package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Dto.BookingDetailsDto;
import com.cg.exceptions.BookingExceptions;
import com.cg.exceptions.ScheduleException;
import com.cg.exceptions.UserException;
import com.cg.model.BookingDetails;
import com.cg.service.BookingDetailsService;

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
	
}
