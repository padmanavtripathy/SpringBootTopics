package com.jwt.JWT.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.JWT.Model.repository.UserRepo;

@Service 
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UserRepo repo;
	
	public UserDetailsServiceImpl(UserRepo repo) {
		super();
		this.repo = repo;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("User is not present"));
	}
	

}
