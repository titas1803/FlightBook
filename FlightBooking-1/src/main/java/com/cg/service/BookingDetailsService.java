package com.cg.service;

import java.util.List;

import com.cg.Dto.BookingDetailsDto;
import com.cg.exceptions.NotFoundException;
import com.cg.model.BookingDetails;

public interface BookingDetailsService  {

	BookingDetails addBooking(BookingDetailsDto bookingdto) throws NotFoundException;

	String deleteBooking(Integer ticketId) throws NotFoundException;

	List<BookingDetails> viewAllBookings() throws NotFoundException;

	BookingDetails viewById(Integer ticketId) throws NotFoundException;

}
