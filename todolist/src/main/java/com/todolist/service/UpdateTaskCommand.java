package com.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.todolist.model.Task;


public class UpdateTaskCommand implements Command {

	
	TaskService taskService;
	Task task;
	public UpdateTaskCommand(TaskService taskService, Task task) {
		this.taskService = taskService;
		this.task=task;
	}

	@Override
	public Boolean execute() {
		// TODO Auto-generated method stub
		 return taskService.UpdateTask(task);
	}

}
