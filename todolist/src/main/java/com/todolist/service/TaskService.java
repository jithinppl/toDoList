package com.todolist.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.model.Task;
import com.todolist.repository.TaskRepository;
import com.todolist.view.TaskView;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	public Boolean AddTask(Task task) {
		
		Date today = new Date();
		task.setDate(today);
		task.setCheck(false);
		taskRepository.save(task);
		return true;
	}
	
	public Boolean RemoveTask(int taskId) {
		
		Task newTask=taskRepository.getTaskById(taskId);
		taskRepository.delete(newTask);
		return true;
		
	}
	
	public Boolean UpdateTask(Task task) {
		
		Date today = new Date();
		Task newTask=taskRepository.getTaskById(task.getTaskId());
		newTask.setDate(today);
		newTask.setDescription(task.getDescription());
		newTask.setCheck(task.getCheck());
		taskRepository.save(newTask);
		return true;
		}
	


	public List<Task> DisplayTask(int userId) {
	
		return taskRepository.findTaskbyUserId(userId);
	
	
	}

  }
