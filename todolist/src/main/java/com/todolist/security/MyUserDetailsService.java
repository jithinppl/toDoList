package com.todolist.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todolist.service.AuthenticationService;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private AuthenticationService AuthService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return AuthService.GetUserbyname(username);
	}

}
