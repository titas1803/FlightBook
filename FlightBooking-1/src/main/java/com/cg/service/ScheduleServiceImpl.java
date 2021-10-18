package com.cg.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.Dto.ScheduleDto;
import com.cg.exceptions.NotFoundException;
import com.cg.model.FlightDetails;
import com.cg.model.Schedule;
import com.cg.repository.FlightRepository;
import com.cg.repository.ScheduleRepository;
import com.cg.util.FlightBookingConstants;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	public ScheduleRepository scheduleRepo;
	
	@Autowired
	public FlightRepository flightRepo;

	@Override
	@Transactional
	public Schedule addSchedule(ScheduleDto scheduleDto) throws NotFoundException {
		Optional<FlightDetails> optFlight = flightRepo.findById(scheduleDto.getFlightId());
		if(!optFlight.isPresent())
			throw new NotFoundException(FlightBookingConstants.FLIGHT_ID_NOT_FOUND);	
		Schedule schedule=new Schedule();
		schedule.setArrivaltime(scheduleDto.getArrivaltime());
		schedule.setDeparturetime(scheduleDto.getDeparturetime());
		schedule.setDestination(scheduleDto.getDestination());
		schedule.setSource(scheduleDto.getSource());
		schedule.setFlightdetails(optFlight.get());
		schedule.setSeatsAvailable(schedule.getFlightdetails().getNoOfSeats());
		Schedule sc=scheduleRepo.saveAndFlush(schedule);
		return sc;
	}

	@Override
	@Transactional
	public String deleteSchedule(Integer scheduleId) throws NotFoundException {
		Optional<Schedule> optSchedule=scheduleRepo.findById(scheduleId);
		if(!optSchedule.isPresent())
			throw new NotFoundException(FlightBookingConstants.SCHEDULE_ID_NOT_FOUND);
		scheduleRepo.deleteById(scheduleId);
		return FlightBookingConstants.SUCCESSFULLY_DELETED+scheduleId;
	}

	@Override
	public Schedule viewbyScheduleId(Integer scheduleId) throws NotFoundException {
		Optional<Schedule> optSchedule=scheduleRepo.findById(scheduleId);
		if(!optSchedule.isPresent())
			throw new NotFoundException(FlightBookingConstants.SCHEDULE_ID_NOT_FOUND);
		return optSchedule.get();
	}


	@Override
	public List<Schedule> viewAllSchedule() throws NotFoundException {
		List<Schedule> lst=scheduleRepo.findAll();
		if(lst.isEmpty())
			throw new NotFoundException(FlightBookingConstants.SCHEDULE_NOT_FOUND);
		return lst;
	}

}
