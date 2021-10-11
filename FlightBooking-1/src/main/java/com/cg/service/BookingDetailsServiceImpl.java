package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.Dto.BookingDetailsDto;
import com.cg.exceptions.AirlineExceptions;
import com.cg.exceptions.BookingExceptions;
import com.cg.exceptions.ScheduleException;
import com.cg.exceptions.UserException;
import com.cg.model.BookingDetails;
import com.cg.model.Schedule;
import com.cg.model.User;
import com.cg.repository.BookingRepository;
import com.cg.repository.ScheduleRepository;
import com.cg.repository.UserRepository;
import com.cg.util.FlightBookingConstants;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {
	
	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ScheduleRepository scheduleRepo;
	
	@Override
	public BookingDetails addBooking(BookingDetailsDto bookingdto ) throws UserException, ScheduleException {
		BookingDetails bookingdetails = new BookingDetails();
		
		Optional<User> optUser = userRepo.findById(bookingdto.getUserId());
		if(!optUser.isPresent()) {
			throw new UserException(FlightBookingConstants.USER_ID_NOT_FOUND);
		}
		
		Optional<Schedule> optSchedule = scheduleRepo.findById(bookingdto.getScheduleId());
		if(!optSchedule.isPresent()) {
			throw new ScheduleException(FlightBookingConstants.SCHEDULE_ID_NOT_FOUND);
		}
		
		bookingdetails.setUser(optUser.get());
		bookingdetails.setSchedule(optSchedule.get());
		
		
		return bookingRepo.save(bookingdetails);
		
	}
	
	@Override
	public String deleteBooking(Integer ticketId) throws BookingExceptions {
		Optional<BookingDetails> optBooking = bookingRepo.findById(ticketId);
		if(!optBooking.isPresent()) {
			throw new BookingExceptions(FlightBookingConstants.BOOKING_ID_NOT_FOUND);
		}
		bookingRepo.deleteById(ticketId);
		return FlightBookingConstants.SUCCESSFULLY_DELETED+ticketId;	
		}
	
	@Override
	public List<BookingDetails> viewAllBookings() throws BookingExceptions{
		List<BookingDetails> listOfBookings = bookingRepo.findAll();
		if(listOfBookings.isEmpty())
		{
			throw new BookingExceptions(FlightBookingConstants.AIRLINE_NOT_FOUND);
		}
		return listOfBookings;
	}
	
	@Override
	public BookingDetails viewById(Integer ticketId) throws BookingExceptions {
	Optional<BookingDetails> optBookings = bookingRepo.findById(ticketId);
	if(!optBookings.isPresent()){
		throw new BookingExceptions(FlightBookingConstants.BOOKING_NOT_FOUND);
	}
	return optBookings.get();
	}
}
