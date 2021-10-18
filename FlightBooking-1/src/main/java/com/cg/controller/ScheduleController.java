package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Dto.ScheduleDto;
import com.cg.exceptions.LoginException;
import com.cg.exceptions.NotFoundException;
import com.cg.exceptions.ValidationException;
import com.cg.model.Schedule;
import com.cg.service.LoginService;
import com.cg.service.ScheduleService;
import com.cg.util.SuccessMessage;

@RestController
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private LoginService loginService;

	@PostMapping("/addschedule")
	public Schedule addSchedule(@RequestBody ScheduleDto scheduleDto, BindingResult br,
			@RequestHeader("token-id") String tokenid) throws ValidationException, LoginException, NotFoundException {
		if (loginService.verifyLogin(tokenid) && loginService.verifyRole(tokenid)) {
			if (br.hasErrors()) {
				throw new ValidationException(br.getFieldErrors());
			}
			Schedule sch = scheduleService.addSchedule(scheduleDto);
			return sch;
		}
		throw new LoginException();
	}

	@DeleteMapping("/deleteschedule/{id}")
	public SuccessMessage deleteSchedule(@PathVariable("id") Integer scheduleId,
			@RequestHeader("token-id") String tokenid) throws LoginException, NotFoundException {
		if (loginService.verifyLogin(tokenid) && loginService.verifyRole(tokenid)) {
			return new SuccessMessage(scheduleService.deleteSchedule(scheduleId));
		}
		throw new LoginException();
	}

	@GetMapping("/getschedulebyid/{id}")
	public Schedule viewScheduleById(@PathVariable("id") Integer scheduleId, @RequestHeader("token-id") String tokenid)
			throws LoginException, NotFoundException {
		if (loginService.verifyLogin(tokenid)) {
			return scheduleService.viewbyScheduleId(scheduleId);
		}
		throw new LoginException();
	}

	@GetMapping("/getallschedule")
	public List<Schedule> viewAlSchedule(@RequestHeader("token-id") String tokenid) throws LoginException, NotFoundException {
		if (loginService.verifyLogin(tokenid)) {
			return scheduleService.viewAllSchedule();
		}
		throw new LoginException();
	}
}
