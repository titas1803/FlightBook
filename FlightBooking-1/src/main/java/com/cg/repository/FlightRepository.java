package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.FlightDetails;

@Repository
public interface FlightRepository extends JpaRepository<FlightDetails, Integer> {

	
}
