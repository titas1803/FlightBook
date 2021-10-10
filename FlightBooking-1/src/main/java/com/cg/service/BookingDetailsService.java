package com.cg.service;

import java.util.List;

import com.cg.Dto.BookingDetailsDto;
import com.cg.exceptions.AirlineExceptions;
import com.cg.exceptions.BookingExceptions;
import com.cg.exceptions.ScheduleException;
import com.cg.exceptions.UserException;
import com.cg.model.BookingDetails;

public interface BookingDetailsService  {

	BookingDetails addBooking(BookingDetailsDto bookingdto) throws UserException, ScheduleException;

	String deleteBooking(Integer ticketId) throws BookingExceptions;

	List<BookingDetails> viewAllBookings() throws AirlineExceptions;

	BookingDetails viewById(Integer ticketId) throws BookingExceptions;

}
