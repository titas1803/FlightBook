package com.cg.model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Schedule_Details")
public class Schedule {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq4" )
	@SequenceGenerator(name="seq4", sequenceName = "schedule_sequence", allocationSize = 1, initialValue = 100)
	private Integer scheduleId;
	private LocalTime departuretime;
	private LocalTime arrivaltime;
	private String source;
	private String destination;
	private Integer seatsAvailable;
	
	@ManyToMany
	private List<FlightDetails> flightdetails;
	
	@OneToMany(mappedBy = "schedules")
	private List<BookingDetails> bookingdetails;

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

	public List<FlightDetails> getFlightdetails() {
		return flightdetails;
	}

	public void setFlightdetails(List<FlightDetails> flightdetails) {
		this.flightdetails = flightdetails;
	}

}
