package com.todolist.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taskid")
	private int taskId;
	
	@Column(name = "userid")
	private int userId;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "ischeck")
	private Boolean check;

	public Task() {}
	public Task(String description, int userId) {
		// TODO Auto-generated constructor stub
		setDescription(description);
		setUserId(userId);
	}
	public Task(String description,int taskId, Boolean check) {
		// TODO Auto-generated constructor stub
		setDescription(description);
		setTaskId(taskId);
		setCheck(check);
	}

	public int getTaskId() {
		return taskId;
	}

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
