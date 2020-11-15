package com.todolist.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.todolist.repository.UserRepository;
import com.todolist.view.UserSigninView;



@Service
public class AuthenticationService {
	@Autowired
    private UserRepository userRepository;
	


	public User GetUserbyname(String userName)
	{
		com.todolist.model.User userdetails=userRepository.GetuserbyName(userName);
		User springuser=null;
		if (userdetails !=null)
		{
			ArrayList<GrantedAuthority> newAuthorities = new ArrayList<>();
			springuser=new User(userdetails.getUserName(),userdetails.getPassword(),newAuthorities);
		}
		else
		{
			throw new UsernameNotFoundException(String.format("No user found with User Name '%s'.", userName));
		}
	
		
		return springuser;
		
	}



}