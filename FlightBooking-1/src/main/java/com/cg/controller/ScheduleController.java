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

import com.cg.Dto.ScheduleDto;
import com.cg.exceptions.BookingExceptions;
import com.cg.exceptions.ScheduleException;
import com.cg.model.BookingDetails;
import com.cg.model.Schedule;
import com.cg.service.ScheduleService;
import com.cg.util.SuccessMessage;

@RestController
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;
	
	@PostMapping("/addschedule")
	public Schedule addSchedule(@RequestBody ScheduleDto scheduleDto, BindingResult br) throws ScheduleException
	{
		if(br.hasErrors())
		{
			throw new ScheduleException();
		}
		Schedule sch = scheduleService.addSchedule(scheduleDto);
		return sch;
	}
	
	@DeleteMapping("/deleteschedule/{id}")
	public SuccessMessage deleteSchedule(@PathVariable("id") Integer scheduleId) throws ScheduleException
	{
		return new SuccessMessage(scheduleService.deleteSchedule(scheduleId));
	}
	
	@GetMapping("/getschedulebyid/{id}")
	public Schedule viewScheduleById(@PathVariable("id") Integer scheduleId) throws ScheduleException
	{
		return scheduleService.viewbyScheduleId(scheduleId);
	}
	
	@GetMapping("/getallschedule")
	public List<Schedule> viewAlSchedule() throws ScheduleException
	{
		return scheduleService.viewAllSchedule();
	}
}
