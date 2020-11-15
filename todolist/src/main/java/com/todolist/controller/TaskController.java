package com.todolist.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.model.Task;
import com.todolist.model.User;
import com.todolist.service.CommandMain;
import com.todolist.service.CommonService;
import com.todolist.service.TaskService;
import com.todolist.view.TaskDisplayView;
import com.todolist.view.TaskRemoveView;
import com.todolist.view.TaskUpdateView;
import com.todolist.view.TaskView;
import com.todolist.view.UserSignupView;

@RestController
public class TaskController {
	
	@Autowired
	private CommonService commonservice;
	
	@Autowired
	private CommandMain commandMain;
	
	@Autowired
	private TaskService taskService;
	

	@RequestMapping(path="/addtask", method = RequestMethod.POST)
    public ResponseEntity<?> addTask(@RequestBody TaskView taskView) {
		try {
			User Loggedinuser=commonservice.Getloggedinuserdetails();
			Boolean valid=commandMain.AddTask(taskView.getDescription(),Loggedinuser.getUserId());
			if (valid)
			{
				 return ResponseEntity.ok().body("Successfully Added");
			}
			else {
				
				return ResponseEntity.status(500).contentType(MediaType.APPLICATION_JSON).body("Error");
			}
		}
	 catch (Exception ex) {

		return ResponseEntity.status(500).contentType(MediaType.APPLICATION_JSON)
				.body("Error has been occured");

	}
 }
	
	@RequestMapping(path="/updatetask", method = RequestMethod.POST)
    public ResponseEntity<?> updateTask(@RequestBody TaskUpdateView taskUpdateView) {
		
			User Loggedinuser=commonservice.Getloggedinuserdetails();
			Boolean valid=commandMain.UpdateTask(taskUpdateView.getDescription(),taskUpdateView.getTaskId(),taskUpdateView.getIsCheck());
			if (valid)
			{
				 return ResponseEntity.ok().body("Successfully Updated");
			}
		
		
	
		return ResponseEntity.ok().body("Not Successfully Updated");
}
	
	@RequestMapping(path="/removetask", method = RequestMethod.POST)
    public ResponseEntity<?> removeTask(@RequestBody TaskRemoveView taskRemoveView) {
		
			//User Loggedinuser=commonservice.Getloggedinuserdetails();
			Boolean valid=commandMain.RemoveTask(taskRemoveView.getTaskId());
			if (valid)
			{
				 return ResponseEntity.ok().body("Successfully Deleted");
			}
		
		
	
		return ResponseEntity.ok().body("Not Successfully Deleted");
}
	
	@RequestMapping(path="/displaytask", method = RequestMethod.GET)
    public ResponseEntity<?> displayTask() {
		
			//User Loggedinuser=commonservice.Getloggedinuserdetails();
		List<Task> newTask=taskService.DisplayTask(1);
		Iterator<Task> task_iterator=newTask.iterator();
		List<TaskDisplayView> userTaskView = new ArrayList<TaskDisplayView>();
		while(task_iterator.hasNext())
		{
			Task t=task_iterator.next();
			TaskDisplayView taskDisplay=new TaskDisplayView();
			taskDisplay.setDate(t.getDate());
			taskDisplay.setDescription(t.getDescription());
			userTaskView.add(taskDisplay);
		}	
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userTaskView);
}
}