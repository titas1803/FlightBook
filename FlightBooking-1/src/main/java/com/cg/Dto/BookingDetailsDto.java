package com.cg.Dto;

import javax.validation.constraints.NotNull;

import com.cg.util.FlightBookingConstants;

public class BookingDetailsDto {
	
	private Integer ticketId;
	@NotNull(message = FlightBookingConstants.USER_ID_REQUIRED)
	private Integer userId;
	@NotNull(message = FlightBookingConstants.SCHEDULE_ID_REQUIRED)
	private Integer scheduleId;
	
	private Integer noOfPersons;
	
	public Integer getTicketId() {
		return ticketId;
	}
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Integer getNoOfPersons() {
		return noOfPersons;
	}
	public void setNoOfPersons(Integer noOfPersons) {
		this.noOfPersons = noOfPersons;
	}
	
	
}
