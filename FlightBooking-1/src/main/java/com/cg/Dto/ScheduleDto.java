package com.cg.Dto;

import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.cg.util.FlightBookingConstants;

public class ScheduleDto {
	
	private Integer scheduleId;
	private LocalTime departuretime;
	private LocalTime arrivaltime;
	@NotBlank(message = FlightBookingConstants.SOURCE_REQUIRED)
	private String source;
	@NotBlank(message = FlightBookingConstants.DESTINATION_REQUIRED)
	private String destination;
	private Integer seatsAvailable;
	
	private List<Integer> flightId;

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public LocalTime getDeparturetime() {
		return departuretime;
	}

	public void setDeparturetime(LocalTime departuretime) {
		this.departuretime = departuretime;
	}

	public LocalTime getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(LocalTime arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(Integer seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public List<Integer> getFlightId() {
		return flightId;
	}

	public void setFlightId(List<Integer> flightId) {
		this.flightId = flightId;
	}
	
	
}
