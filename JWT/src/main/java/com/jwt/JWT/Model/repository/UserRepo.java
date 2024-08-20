package com.jwt.JWT.Model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jwt.JWT.Model.User;


public interface UserRepo extends JpaRepository<User, Integer>{
	
	Optional<User> findByUserName(String userName);
}
