package com.todolist;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.view.UserSigninView;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodolistApplication.class)
@EnableWebMvc
@AutoConfigureMockMvc
class AuthenticationControllerTest {
	@Autowired
	private MockMvc mvc;

	

	private String Autherizationheader;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void Logincontroltest() throws Exception {
		UserSigninView userinfo = new UserSigninView();
		userinfo.setUserName("test");
		userinfo.setPassword("pwd123");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(userinfo);

		MvcResult result=mvc.perform(post("/authentication").contentType(MediaType.APPLICATION_JSON).content(jsonString))
				.andExpect(status().isOk()).andReturn(); 
		

	}
	
	@Test
	public void LogincontroltestFail() throws Exception {
		UserSigninView userinfo = new UserSigninView();
		userinfo.setUserName("test");
		userinfo.setPassword("pwd");

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(userinfo);

		MvcResult result=mvc.perform(post("/authentication").contentType(MediaType.APPLICATION_JSON).content(jsonString))
				.andExpect(status().is(501)).andReturn(); 
		

	}



}