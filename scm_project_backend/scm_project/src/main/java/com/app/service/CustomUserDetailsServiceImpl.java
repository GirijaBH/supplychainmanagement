package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.pojos.User;
import com.app.repository.IUserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	IUserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 User user = userRepository.findByUserName(username)
			        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

			    return UserDetailsImpl.build(user);
		
	}
	
	
}
