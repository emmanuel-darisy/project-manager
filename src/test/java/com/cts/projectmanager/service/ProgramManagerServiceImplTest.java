package com.cts.projectmanager.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.projectmanager.dao.ProgramManagerDao;
import com.cts.projectmanager.model.ParentTask;
import com.cts.projectmanager.model.Project;
import com.cts.projectmanager.model.Task;
import com.cts.projectmanager.model.User;

@RunWith(SpringRunner.class)
public class ProgramManagerServiceImplTest {

	
	@InjectMocks
	private ProgramManagerServiceImpl programManagerServiceImpl;
	
	@Mock
	private ProgramManagerDao programManagerDao;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void testGetUser() throws Exception {
		User user = new User();
		when(programManagerDao.addOrUpdateUser(Mockito.any())).thenReturn(user);
		assertNotNull(programManagerServiceImpl.addUser(user));
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		User user = new User();
		when(programManagerDao.addOrUpdateUser(Mockito.any())).thenReturn(user);
		assertNotNull(programManagerServiceImpl.updateUser(user));
	}
	
	@Test
	public void testdeleteUser() throws Exception {
		String status = "";
		when(programManagerDao.deleteUser(Mockito.anyInt())).thenReturn(status);
		assertNotNull(programManagerServiceImpl.deleteUser(123));
	}
	
	@Test
	public void testgetUsers() throws Exception {
		List<User> usersList = new ArrayList<>();
		when(programManagerDao.getUsers()).thenReturn(usersList);
		assertNotNull(programManagerServiceImpl.getUsers());
	}
	
	
	@Test
	public void testaddProject() throws Exception {
		Project project = new Project();
		String status = "";
		when(programManagerDao.addProject(Mockito.any(),Mockito.anyInt())).thenReturn(status);
		assertNotNull(programManagerServiceImpl.addProject(project,123));
	}
	
	@Test
	public void testupdateProject() throws Exception {
		Project project = new Project();
		String status = "";
		when(programManagerDao.updateProject(Mockito.any())).thenReturn(status);
		assertNotNull(programManagerServiceImpl.updateProject(project));
	}
	
	@Test
	public void testgetProjects() throws Exception {
		List<Project> project = new ArrayList<>();
		when(programManagerDao.getProjects()).thenReturn(project);
		assertNotNull(programManagerServiceImpl.getProjects());
	}

	@Test
	public void testdeleteProject() throws Exception {
		Project project = new Project();
		String status = "";
		when(programManagerDao.deleteProject(Mockito.anyInt())).thenReturn(status);
		assertNotNull(programManagerServiceImpl.deleteProject(123));
	}

	
	@Test
	public void testaddTask() throws Exception {
		Task task = new Task();
		String status = "";
		when(programManagerDao.addTask(Mockito.any())).thenReturn(status);
		assertNotNull(programManagerServiceImpl.addTask(task));
	}
	
	@Test
	public void testupdateTask() throws Exception {
		Task task = new Task();
		String status = "";
		when(programManagerDao.updateTask(Mockito.any())).thenReturn(status);
		assertNotNull(programManagerServiceImpl.updateTask(task));
	}
	
	@Test
	public void testaddParentTask() throws Exception {
		ParentTask task = new ParentTask();
		String status = "";
		when(programManagerDao.addParentTask(Mockito.any())).thenReturn(status);
		assertNotNull(programManagerServiceImpl.addParentTask(task));
	}
	
	@Test
	public void testgetTasks() throws Exception {
		List<Task> taskList = new ArrayList<>();
		when(programManagerDao.getTasks()).thenReturn(taskList);
		assertNotNull(programManagerServiceImpl.getTasks());
	}
	
	
	
	@Test
	public void testgetTasksById() throws Exception {
		List<Task> taskList = new ArrayList<>();
		when(programManagerDao.getTasks(Mockito.anyInt())).thenReturn(taskList);
		assertNotNull(programManagerServiceImpl.getTasks(123));
	}
	
	@Test
	public void testgetParentTask() throws Exception {
		List<ParentTask> taskList = new ArrayList<>();
		when(programManagerDao.getParentTask(Mockito.anyInt())).thenReturn(taskList);
		assertNotNull(programManagerServiceImpl.getParentTask(123));
	}
	
	

}
