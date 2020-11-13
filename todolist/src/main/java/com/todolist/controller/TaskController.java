package com.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.todolist.model.User;
import com.todolist.service.CommonService;
import com.todolist.view.TaskView;
import com.todolist.view.UserSignupView;

public class TaskController {
	
	@Autowired
	private CommonService commonservice;

	@RequestMapping(path="/addtask", method = RequestMethod.POST)
    public ResponseEntity<?> addTask(@RequestBody TaskView taskView) {
		
			User Loggedinuser=commonservice.Getloggedinuserdetails();
		
		
		
	
		return null;
}
}