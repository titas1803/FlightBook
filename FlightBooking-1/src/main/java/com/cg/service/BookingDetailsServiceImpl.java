package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.Dto.BookingDetailsDto;
import com.cg.exceptions.NotFoundException;
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
	@Transactional
	public BookingDetails addBooking(BookingDetailsDto bookingdto ) throws NotFoundException {
		BookingDetails bookingdetails = new BookingDetails();
		
		Optional<User> optUser = userRepo.findById(bookingdto.getUserId());
		if(!optUser.isPresent()) {
			throw new NotFoundException(FlightBookingConstants.USER_ID_NOT_FOUND);
		}
		
		Optional<Schedule> optSchedule = scheduleRepo.findById(bookingdto.getScheduleId());
		if(!optSchedule.isPresent()) {
			throw new NotFoundException(FlightBookingConstants.SCHEDULE_ID_NOT_FOUND);
		}
		Schedule schedule = optSchedule.get(); 
		
		bookingdetails.setNoOfPersons(bookingdto.getNoOfPersons());
		bookingdetails.setUser(optUser.get());
		schedule.setSeatsAvailable(schedule.getSeatsAvailable()-bookingdto.getNoOfPersons());
		bookingdetails.setSchedule(optSchedule.get());
		
		scheduleRepo.save(schedule);
		return bookingRepo.save(bookingdetails);
		
	}
	
	@Override
	@Transactional
	public String deleteBooking(Integer ticketId) throws NotFoundException {
		Optional<BookingDetails> optBooking = bookingRepo.findById(ticketId);
		if(!optBooking.isPresent()) {
			throw new NotFoundException(FlightBookingConstants.BOOKING_ID_NOT_FOUND);
		}
		BookingDetails booking = optBooking.get();
		Schedule schedule = booking.getSchedule();
		schedule.setSeatsAvailable(schedule.getSeatsAvailable()+booking.getNoOfPersons());
		bookingRepo.deleteById(ticketId);
		
		scheduleRepo.saveAndFlush(schedule);
		return FlightBookingConstants.SUCCESSFULLY_DELETED+ticketId;	
	}
	
	@Override
	public List<BookingDetails> viewAllBookings() throws NotFoundException{
		List<BookingDetails> listOfBookings = bookingRepo.findAll();
		if(listOfBookings.isEmpty())
		{
			throw new NotFoundException(FlightBookingConstants.AIRLINE_NOT_FOUND);
		}
		return listOfBookings;
	}
	
	@Override
	public BookingDetails viewById(Integer ticketId) throws NotFoundException {
	Optional<BookingDetails> optBookings = bookingRepo.findById(ticketId);
	if(!optBookings.isPresent()){
		throw new NotFoundException(FlightBookingConstants.BOOKING_NOT_FOUND);
	}
	return optBookings.get();
	}
}
