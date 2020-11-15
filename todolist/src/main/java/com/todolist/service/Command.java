package com.todolist.service;

import org.springframework.stereotype.Service;

@Service
public interface Command {

	public Boolean execute(); 
}
