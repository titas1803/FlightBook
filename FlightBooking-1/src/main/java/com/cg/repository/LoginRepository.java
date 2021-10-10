package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

}
