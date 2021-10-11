package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.Dto.ScheduleDto;
import com.cg.exceptions.ScheduleException;
import com.cg.model.Schedule;
import com.cg.repository.ScheduleRepository;
import com.cg.util.FlightBookingConstants;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	public ScheduleRepository scheduleRepo;

	@Override
	public Schedule addSchedule(ScheduleDto scheduleDto) {
		Schedule schedule=new Schedule();
		schedule.setArrivaltime(scheduleDto.getArrivaltime());
		schedule.setDeparturetime(scheduleDto.getDeparturetime());
		schedule.setDestination(scheduleDto.getDestination());
		schedule.setSource(scheduleDto.getSource());
		schedule.setSeatsAvailable(scheduleDto.getSeatsAvailable());
		Schedule sc=scheduleRepo.saveAndFlush(schedule);
		return sc;
	}

	@Override
	public String deleteSchedule(Integer scheduleId) throws ScheduleException {
		Optional<Schedule> optSchedule=scheduleRepo.findById(scheduleId);
		if(!optSchedule.isPresent())
			throw new ScheduleException(FlightBookingConstants.SCHEDULE_ID_NOT_FOUND);
		scheduleRepo.deleteById(scheduleId);
		return FlightBookingConstants.SUCCESSFULLY_DELETED+scheduleId;
	}

	@Override
	public Schedule viewbyScheduleId(Integer scheduleId) throws ScheduleException {
		Optional<Schedule> optSchedule=scheduleRepo.findById(scheduleId);
		if(!optSchedule.isPresent())
			throw new ScheduleException(FlightBookingConstants.SCHEDULE_ID_NOT_FOUND);
		return optSchedule.get();
	}


	@Override
	public List<Schedule> viewAllSchedule() throws ScheduleException {
		List<Schedule> lst=scheduleRepo.findAll();
		if(lst.isEmpty())
			throw new ScheduleException(FlightBookingConstants.SCHEDULE_NOT_FOUND);
		return lst;
	}

}
