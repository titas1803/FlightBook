package com.cg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("from User u where u.phoneNumber = :phoneNumber")
	public Optional<User> findByContact(@Param("phoneNumber") String contactNo);
	
	@Query("from User u where u.email = :email")
	public Optional<User> findByEmail(@Param("email") String email);
	
}
