package com.dh.test.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dh.test.models.CustomUserDetails;
import com.dh.test.models.User;
import com.dh.test.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		
		optionalUser
		.orElseThrow(() -> new UsernameNotFoundException("Username not found."));
		
		return optionalUser
		.map(CustomUserDetails::new).get();		
	}	
}