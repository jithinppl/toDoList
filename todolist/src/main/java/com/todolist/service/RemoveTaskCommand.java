package com.todolist.service;

public class RemoveTaskCommand implements Command {

	TaskService taskService;
	private int taskId;
	public RemoveTaskCommand(TaskService taskService,int taskId) {
		this.taskService = taskService;
		this.taskId=taskId;
	}

	@Override
	public Boolean execute() {
		// TODO Auto-generated method stub
		 return taskService.RemoveTask(taskId);
	}

}