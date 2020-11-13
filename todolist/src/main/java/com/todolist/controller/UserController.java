package com.todolist.controller;

//import javax.validation.Valid;

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
@CrossOrigin
@RequestMapping(value="/api/user")
public class UserController {
	
	@Autowired
	UserService userService ;
	
	UserView userView;
	/*@GetMapping("/all")
	public List<User> Userlogin() {
	List<User> l1=user_service.getAll();
	return l1;
	}*/
	@RequestMapping(path="/signup", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody UserSignupView userSignupView) {
		 UserView userView = userService.addUser(userSignupView);
		 if(userView==null)
		 {
			 return ResponseEntity.ok().body("Invalid Email/User already exist");
		 }
         return ResponseEntity.ok().body("Successfully Registered");
		
	}
}
