package com.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.todolist.model.Task;

@Component
public class CommandMain {
	
	@Autowired
	CommandSwitch commandswitch;
	@Autowired
	TaskService taskservice;
	
	public Boolean AddTask(String description, int userId) {
		
		Task task=new Task(description,userId);
		Command addTask=new AddTaskCommand(taskservice,task);
		commandswitch.setCommand(addTask);
		return commandswitch.commandService();
	}
	
	public Boolean RemoveTask(int taskId) {
		Command removeTask=new RemoveTaskCommand(taskservice,taskId);
		commandswitch.setCommand(removeTask);
		return commandswitch.commandService();
	}
	
	public Boolean UpdateTask(String description, int taskId, Boolean isCheck) {
		Task task=new Task(description,taskId,isCheck);
		Command updateTask=new UpdateTaskCommand(taskservice,task);
		commandswitch.setCommand(updateTask);
		return commandswitch.commandService();
	}
	
	
	

}
