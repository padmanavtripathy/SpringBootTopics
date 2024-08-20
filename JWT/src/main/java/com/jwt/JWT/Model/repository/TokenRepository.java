package com.jwt.JWT.Model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jwt.JWT.Model.Token;



public interface TokenRepository extends JpaRepository<Token, Integer>{

	 @Query("""
			 select t from Token t inner join User u on t.user.id = u.id
			 where t.user.id = :userId
			 """)
			    List<Token> findAllAccessTokensByUser(Integer userId);
}
