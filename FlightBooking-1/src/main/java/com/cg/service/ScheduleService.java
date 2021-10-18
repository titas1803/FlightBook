package com.cg.service;

import java.util.List;

import com.cg.Dto.ScheduleDto;
import com.cg.exceptions.FlightException;
import com.cg.exceptions.NotFoundException;
import com.cg.exceptions.ScheduleException;
import com.cg.model.Schedule;

public interface ScheduleService {
	
	public Schedule addSchedule(ScheduleDto scheduleDto) throws NotFoundException;
	public String deleteSchedule(Integer scheduleId) throws NotFoundException;
	public Schedule viewbyScheduleId(Integer scheduleId) throws NotFoundException;
	public List<Schedule> viewAllSchedule() throws NotFoundException;
}
