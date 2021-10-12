package com.cg.service;

import java.util.List;

import com.cg.Dto.ScheduleDto;
import com.cg.exceptions.FlightException;
import com.cg.exceptions.ScheduleException;
import com.cg.model.Schedule;

public interface ScheduleService {
	
	public Schedule addSchedule(ScheduleDto scheduleDto) throws FlightException;
	public String deleteSchedule(Integer scheduleId) throws ScheduleException;
	public Schedule viewbyScheduleId(Integer scheduleId) throws ScheduleException;
	public List<Schedule> viewAllSchedule() throws ScheduleException;
}
