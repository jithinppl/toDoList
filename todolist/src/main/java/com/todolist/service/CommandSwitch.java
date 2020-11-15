package com.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CommandSwitch {
	
	private Command commandTask;
	public void setCommand(Command command)
	{
		this.commandTask=command;
	}
	public Boolean commandService() {
		return commandTask.execute();
	}

}