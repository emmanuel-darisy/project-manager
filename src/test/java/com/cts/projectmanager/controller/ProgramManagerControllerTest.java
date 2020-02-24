package com.cts.projectmanager.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import com.cts.projectmanager.model.ParentTask;
import com.cts.projectmanager.model.Project;
import com.cts.projectmanager.model.Task;
import com.cts.projectmanager.model.User;
import com.cts.projectmanager.service.ProgramManagerServiceImpl;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ProgramManagerController.class)
public class ProgramManagerControllerTest {
	
	@InjectMocks
	private ProgramManagerController programManagerController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProgramManagerServiceImpl programManagerService; 
	
	@Autowired
	protected WebApplicationContext webApplicationContext;
	
	@Mock
	private MockHttpServletRequest request;

	protected MediaType contentType;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
				MediaType.APPLICATION_JSON.getSubtype(),Charset.forName("utf8"));
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetUser() throws Exception {
		this.mockMvc.perform(get("/user").contentType(contentType).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk());
		when(programManagerService.getUsers()).thenThrow(new RuntimeException());
		this.mockMvc.perform(get("/user").contentType(contentType).accept(MediaType.APPLICATION_JSON)).
		andExpect(status().isInternalServerError());
		
	}
	
	@Test
	public void testAddUser() throws Exception {
		User req = new User();
		this.mockMvc.perform(post("/user").contentType(contentType).content(convertReqObjToJson(req)).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk());
		
		when(programManagerService.addUser(null)).thenThrow(new RuntimeException());
		this.mockMvc.perform(post("/user").contentType(contentType).accept(MediaType.APPLICATION_JSON)).
		andExpect(status().isInternalServerError());
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		User req = new User();
		req.setEmployeeId(123);
		req.setFirstName("firstname");
		req.setLastName("lastName");
		req.setUserId(123);
		when(programManagerService.updateUser(req)).thenReturn(req);
		this.mockMvc.perform(put("/user").contentType(contentType).content(convertReqObjToJson(req)).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk());
		when(programManagerService.updateUser(Mockito.any())).thenThrow(new RuntimeException());
		this.mockMvc.perform(put("/user").contentType(contentType).content(convertReqObjToJson(req)).accept(MediaType.APPLICATION_JSON)).
		andExpect(status().isInternalServerError());
	}
	
	/*@Test
	public void testDeleteUser() throws Exception {
		int userId=123;
		this.mockMvc.perform(delete("/user").contentType(contentType).content(convertReqObjToJson(userId)).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk());
	}*/
	
	
	
	@Test
	public void testdeleteUser() throws Exception {
		
		when(programManagerService.deleteUser(123)).thenReturn("Success");
		this.mockMvc.perform(delete("/user/123").contentType(contentType).content(convertReqObjToJson(new Integer(123))).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk());
		when(programManagerService.deleteUser(Mockito.anyInt())).thenThrow(new RuntimeException());
		
		this.mockMvc.perform(delete("/user/123").contentType(contentType).content(convertReqObjToJson(new Integer(123))).accept(MediaType.APPLICATION_JSON)).
		andExpect(status().isInternalServerError());
	}

	
	@Test
	public void testdeleteProject() throws Exception {
		Project project = new Project();
		project.setProjectId(123);
		when(programManagerService.deleteProject(123)).thenReturn("Success");
		this.mockMvc.perform(delete("/project/123").contentType(contentType).content(convertReqObjToJson(project)).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk());
		when(programManagerService.deleteProject(Mockito.anyInt())).thenThrow(new RuntimeException());
		
		this.mockMvc.perform(delete("/project/123").contentType(contentType).content(convertReqObjToJson(project)).accept(MediaType.APPLICATION_JSON)).
		andExpect(status().isInternalServerError());
	}



	@Test
	public void testaddProject() throws Exception {
		Project req = new Project();
		User user = new User();
		user.setUserId(1);
		req.setUser(user);
		String status = "";
		when(programManagerService.addProject(Mockito.any(),Mockito.anyInt())).thenReturn(status);
		this.mockMvc.perform(post("/project/1").contentType(contentType).content(convertReqObjToJson(req)).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk());
		
		when(programManagerService.addProject(Mockito.any(),Mockito.anyInt())).thenThrow(new RuntimeException());
		this.mockMvc.perform(post("/project/1").contentType(contentType).content(convertReqObjToJson(req)).accept(MediaType.APPLICATION_JSON)).
		andExpect(status().isInternalServerError());
	}
	
	@Test
	public void testUpdateProject() throws Exception {
		Project req = new Project();
		User user = new User();
		user.setUserId(1);
		req.setUser(user);
		String status = "";
		when(programManagerService.updateProject(Mockito.any())).thenReturn(status);
		this.mockMvc.perform(put("/project").contentType(contentType).content(convertReqObjToJson(req)).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk());
		
		when(programManagerService.updateProject(Mockito.any())).thenThrow(new RuntimeException());
		this.mockMvc.perform(put("/project").contentType(contentType).content(convertReqObjToJson(req)).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isInternalServerError());
	}
	
	@Test
	public void testgetProjects() throws Exception {
		List<Project> req = new ArrayList<>();
		when(programManagerService.getProjects()).thenReturn(req);
		this.mockMvc.perform(get("/project").contentType(contentType).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk());
		
		when(programManagerService.getProjects()).thenThrow(new RuntimeException());
		this.mockMvc.perform(get("/project").contentType(contentType).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isInternalServerError());
	}
	
	@Test
	public void testaddTask() throws Exception {
		Task req = new Task();
		User user = new User();
		user.setUserId(1);
		Project project = new Project();
		project.setProjectId(1);
		req.setUser(user);
		req.setProject(project);
		String status ="";
		when(programManagerService.addTask(Mockito.any())).thenReturn(status);
		this.mockMvc.perform(post("/task").contentType(contentType).content(convertReqObjToJson(req)).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk());
	}
	
	@Test
	public void testupdateTask() throws Exception {
		Task req = new Task();
		User user = new User();
		user.setUserId(1);
		Project project = new Project();
		project.setProjectId(1);
		req.setUser(user);
		req.setProject(project);
		String status ="";
		when(programManagerService.updateTask(Mockito.any())).thenReturn(status);
		this.mockMvc.perform(put("/task").contentType(contentType).content(convertReqObjToJson(req)).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk());
	}
	
	@Test
	public void testgetTasks() throws Exception {
		/*Task req = new Task();
		User user = new User();
		user.setUserId(1);
		Project project = new Project();
		project.setProjectId(1);
		req.setUser(user);
		req.setProject(project);
		String status ="";*/
		List<Task> taskList = new ArrayList<>();
		when(programManagerService.getTasks()).thenReturn(taskList);
		this.mockMvc.perform(get("/task").contentType(contentType).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk());
	}
	
	@Test
	public void testgetTasksById() throws Exception {
		/*Task req = new Task();
		User user = new User();
		user.setUserId(1);
		Project project = new Project();
		project.setProjectId(1);
		req.setUser(user);
		req.setProject(project);
		String status ="";*/
		List<Task> taskList = new ArrayList<>();
		when(programManagerService.getTasks()).thenReturn(taskList);
		this.mockMvc.perform(get("/task/1").contentType(contentType).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk());
	}
	
	@Test
	public void testaddParentTask() throws Exception {
		
		String status ="";
		ParentTask req = new ParentTask();
		Project project = new Project();
		project.setProjectId(1);
		req.setProject(project);
		when(programManagerService.addParentTask(Mockito.any())).thenReturn(status);
		this.mockMvc.perform(post("/parentTask").contentType(contentType).content(convertReqObjToJson(req)).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk());
	}
	
	@Test
	public void testgetParentTask() throws Exception {
		List<ParentTask> parentTasksList = new ArrayList<>();
		
		ParentTask req = new ParentTask();
		Project project = new Project();
		project.setProjectId(1);
		req.setProject(project);
		when(programManagerService.getParentTask(Mockito.anyInt())).thenReturn(parentTasksList);
		this.mockMvc.perform(get("/parentTask/1").contentType(contentType).content(convertReqObjToJson(req)).accept(MediaType.APPLICATION_JSON)).
			andExpect(status().isOk());
	}
	
	
	private byte[] convertReqObjToJson(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}
	
}
