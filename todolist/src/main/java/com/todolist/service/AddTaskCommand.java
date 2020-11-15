package com.todolist.service;

import com.todolist.model.Task;

public class AddTaskCommand implements Command {
	
	TaskService taskService;
	Task task;
	public AddTaskCommand(TaskService taskService,Task task) {
		this.taskService = taskService;
		this.task=task;
	}

	@Override
	public Boolean execute() {
		// TODO Auto-generated method stub
		 return taskService.AddTask(task);
	}

}
