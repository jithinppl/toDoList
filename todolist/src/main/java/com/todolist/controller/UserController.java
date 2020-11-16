package com.todolist.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.service.UserService;
import com.todolist.view.UserSignupView;
import com.todolist.view.UserView;


@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/api/user")
public class UserController {
	
	@Autowired
	UserService userService ;
	

	@RequestMapping(path="/signup", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody UserSignupView userSignupView) {
		 UserView userView = userService.addUser(userSignupView);
		 if(userView==null)
		 {
			 return ResponseEntity.ok().body("User already exist");
		 }
         return ResponseEntity.ok().body("Successfully Registered");
		
	}
}
