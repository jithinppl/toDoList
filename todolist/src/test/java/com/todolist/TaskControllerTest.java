package com.todolist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.model.Task;
import com.todolist.repository.TaskRepository;
import com.todolist.view.TaskRemoveView;
import com.todolist.view.TaskView;
import com.todolist.view.UserSigninView;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodolistApplication.class)
@EnableWebMvc
@AutoConfigureMockMvc
class TaskControllerTest {

	@Autowired
	private MockMvc mvc;
	

	  @MockBean
	  private TaskRepository mockedTaskRepository;
	

	private String Autherizationheader;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void AddTaskTest() throws Exception {
		
		UserSigninView userinfo = new UserSigninView();
		userinfo.setUserName("test");
		userinfo.setPassword("pwd123");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(userinfo);

		MvcResult result=mvc.perform(post("/authentication").contentType(MediaType.APPLICATION_JSON).content(jsonString))
				.andExpect(status().isOk()).andReturn(); 
		Autherizationheader="Bearer " +result.getResponse().getContentAsString();
		
		Task task=new Task();
		
		Mockito.when(mockedTaskRepository.save(Mockito.any(Task.class))).thenReturn(task);
		
		
		TaskView taskView = new TaskView();
		taskView.setDescription("Task 111");
		

		ObjectMapper mapper1 = new ObjectMapper();
		String jsonString1 = mapper1.writeValueAsString(taskView);

		MvcResult finalresult=mvc.perform(post("/addtask").contentType(MediaType.APPLICATION_JSON).content(jsonString1).header("Authorization",Autherizationheader))
				.andExpect(status().isOk()).andReturn(); 
		
		String response= finalresult.getResponse().getContentAsString();
		assertEquals("Successfully Added",response);

	}
	
	@Test
	public void RemoveTaskTest() throws Exception {
		
		UserSigninView userinfo = new UserSigninView();
		userinfo.setUserName("test");
		userinfo.setPassword("pwd123");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(userinfo);

		MvcResult result=mvc.perform(post("/authentication").contentType(MediaType.APPLICATION_JSON).content(jsonString))
				.andExpect(status().isOk()).andReturn(); 
		Autherizationheader="Bearer " +result.getResponse().getContentAsString();
		
		Task task=new Task();
		
		TaskRemoveView taskRemoveView = new TaskRemoveView();
		taskRemoveView.setTaskId(1);
		 //Mockito.when(mockedTaskRepository.delete(task));
		 //Mockito.verify(mockedTaskRepository, Mockito.times(1)).delete(task);

		ObjectMapper mapper1 = new ObjectMapper();
		String jsonString1 = mapper1.writeValueAsString(taskRemoveView);

		MvcResult finalresult=mvc.perform(post("/removetask").contentType(MediaType.APPLICATION_JSON).content(jsonString1).header("Authorization",Autherizationheader))
				.andExpect(status().isOk()).andReturn(); 
		
		String response= finalresult.getResponse().getContentAsString();
		assertEquals("Successfully Deleted",response);

	}
}
