package com.cg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



import lombok.Data;

/*@DynamicInsert
@DynamicUpdate*/

@Data
@Entity

@Table(name = "AirLine_Details")
public class AirlineDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq2" )
	@SequenceGenerator(name="seq2", sequenceName = "airline_sequence", allocationSize = 1, initialValue = 1)
	private Integer airLineId;
	private String airLineName;
	private String airlineLogo;
	
	
	public String getAirLineName() {
		return airLineName;
	}
	public void setAirLineName(String airLineName) {
		this.airLineName = airLineName;
	}
	public String getAirlineLogo() {
		return airlineLogo;
	}
	public void setAirlineLogo(String airlineLogo) {
		this.airlineLogo = airlineLogo;
	}
	public Integer getAirLineId() {
		return airLineId;
	}
	public AirlineDetails(Integer airLineId, String airLineName, String airlineLogo) {
		super();
		this.airLineId = airLineId;
		this.airLineName = airLineName;
		this.airlineLogo = airlineLogo;
	}
	public AirlineDetails() {
		super();
	}
	
	
}
