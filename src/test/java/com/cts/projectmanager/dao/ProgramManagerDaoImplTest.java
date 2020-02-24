package com.cts.projectmanager.dao;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.projectmanager.model.ParentTask;
import com.cts.projectmanager.model.Project;
import com.cts.projectmanager.model.Task;
import com.cts.projectmanager.model.User;

@RunWith(SpringRunner.class)
public class ProgramManagerDaoImplTest {

	
	@InjectMocks
	private ProgramManagerDaoImpl programManagerDao;
	
	@Mock
	EntityManager entityManager;
	
	@Mock
	private Session session;
	
	@Mock
	private org.hibernate.query.Query<Object> query;
	
	@Mock
	private org.hibernate.query.Query<Object> query2;
	
	@Mock
	private org.hibernate.query.Query<Object> query3;
	
	@Mock
	private User user;
	
	@Mock
	private Project project;
	

	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetUser() throws Exception {
		User user = new User();
		user.setUserId(12);
		user.setEmployeeId(12);
		user.setFirstName("test");
		user.setLastName("test");
		when(entityManager.unwrap(Mockito.any())).thenReturn(session);
		assertNotNull(programManagerDao.addOrUpdateUser(user));
	}

	
	@Test(expected=Exception.class)
	public void testGetUserException() throws Exception {
		User user = new User();
		when(entityManager.unwrap(Mockito.any())).thenThrow(new RuntimeException());
		programManagerDao.addOrUpdateUser(user);
	}
	
	
	@Test
	public void testdeleteUser() throws Exception {
		int userId=123;
		User user = new User();
		user.setUserId(12);
		user.setEmployeeId(12);
		user.setFirstName("test");
		user.setLastName("test");
		when(entityManager.unwrap(Mockito.any())).thenReturn(session);
		when(session.createQuery(Mockito.anyString())).thenReturn(query);
		assertNotNull(programManagerDao.deleteUser(userId));
	}
	
	@Test(expected=Exception.class)
	public void testdeleteUserException() throws Exception {
		int userId=123;
		when(entityManager.unwrap(Mockito.any())).thenThrow(new RuntimeException());
		programManagerDao.deleteUser(userId);
	}
	
	
	@Test
	public void testgetUsers() throws Exception {
		User user = new User();
		user.setUserId(12);
		user.setEmployeeId(12);
		user.setFirstName("test");
		user.setLastName("test");
		when(entityManager.unwrap(Mockito.any())).thenReturn(session);
		when(session.createQuery(Mockito.anyString(),Mockito.any())).thenReturn(query);
		assertNotNull(programManagerDao.getUsers());
	}
	
	@Test(expected=Exception.class)
	public void testgetUsersException() throws Exception {
		when(entityManager.unwrap(Mockito.any())).thenReturn(session);
		when(session.createQuery(Mockito.anyString(),Mockito.any())).thenThrow(new RuntimeException());
		assertNotNull(programManagerDao.getUsers());
	}
	
	
	@Test
	public void testaddProject() throws Exception {
		user.setUserId(123);
		user.setEmployeeId(1234);
		when(entityManager.unwrap(Mockito.any())).thenReturn(session);
		//when(session.get(Mockito.anyString(),Mockito.anyInt())).thenReturn(user);
		when(session.get(ArgumentMatchers.anyString(),ArgumentMatchers.anyInt())).thenReturn(user);
		programManagerDao.addProject(project,123);
	}
	
	
	@Test
	public void testupdateProject() throws Exception {
		User user = new User();
		user.setUserId(123);
		user.setEmployeeId(1234);
		Project project = new Project();
		project.setPriority(1);
		project.setProjectId(123);
		project.setUser(user);
		when(entityManager.unwrap(Mockito.any())).thenReturn(session);
		when(session.get(ArgumentMatchers.anyString(),ArgumentMatchers.anyInt())).thenReturn(user);
		programManagerDao.updateProject(project);
	}
	
	@Test
	public void testgetProjects() throws Exception {
		List<Project> projectList=  new ArrayList<>();
		Project project = new Project();
		project.setCompletedTasks(10);
		project.setTaskCount(10);
		project.setProjectId(123);
		projectList.add(project);
		when(entityManager.unwrap(Mockito.any())).thenReturn(session);
		when(session.createQuery(Mockito.anyString(),Mockito.any())).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<>(projectList));
		when(session.createQuery(Mockito.anyString())).thenReturn(query2);
		when(session.createQuery(Mockito.anyString())).thenReturn(query3);
		assertNotNull(programManagerDao.getProjects());
	}

	@Test(expected=Exception.class)
	public void testgetProjectsException() throws Exception {
		programManagerDao.getProjects();
	}
	
	
	@Test
	public void testdeleteProject() throws Exception {
		Project project = new Project();
		project.setCompletedTasks(10);
		project.setTaskCount(10);
		project.setProjectId(123);
		project.setProjectName("Dummy");
		long millis = System.currentTimeMillis();
		project.setStartDate(new java.sql.Date(millis));
		project.setEndDate(new java.sql.Date(millis));
		project.toString();
		ParentTask parentTask = new ParentTask();
		parentTask.setParentTask("Dummy");
		parentTask.toString();
		when(entityManager.unwrap(Mockito.any())).thenReturn(session);
		when(session.createQuery(Mockito.anyString())).thenReturn(query);
		assertNotNull(programManagerDao.deleteProject(123));
	}
	
	@Test(expected=Exception.class)
	public void testdeleteProjectException() throws Exception {
		assertNotNull(programManagerDao.deleteProject(123));
	}
	
	
	@Test
	public void testaddTask() throws Exception {
		Task task = new Task();
		task.toString();
		when(entityManager.unwrap(Mockito.any())).thenReturn(session);
		assertNotNull(programManagerDao.addTask(task));
	}
	
	@Test
	public void testupdateTask() throws Exception {
		Task task = new Task();
		when(entityManager.unwrap(Mockito.any())).thenReturn(session);
		assertNotNull(programManagerDao.updateTask(task));
	}
	
	@Test
	public void testaddParentTask() throws Exception {
		ParentTask parentTask = new ParentTask();
		when(entityManager.unwrap(Mockito.any())).thenReturn(session);
		assertNotNull(programManagerDao.addParentTask(parentTask));
	}
	
	@Test(expected=Exception.class)
	public void testaddParentTaskException() throws Exception {
		ParentTask parentTask = new ParentTask();
		programManagerDao.addParentTask(parentTask);
	}
	
	
	@Test
	public void testgetTasks() throws Exception {	
		when(entityManager.unwrap(Mockito.any())).thenReturn(session);
		when(session.createQuery(Mockito.anyString(),Mockito.any())).thenReturn(query);
		assertNotNull(programManagerDao.getTasks());
	}
	
	@Test
	public void testgetTasksById() throws Exception {	
		List<Task> taskList = new ArrayList<>();
		Task task = new Task();
		long millis = System.currentTimeMillis();		
		task.setStartDate(new java.sql.Date(millis));
		task.setEndDate(new java.sql.Date(millis));
		task.setStatus("status");
		task.setTaskName("taskName");
		task.setParenttask(new ParentTask());
		Project project = new Project();
		project.setProjectId(123);
		task.setProject(project);
		taskList.add(task);		
		when(entityManager.unwrap(Mockito.any())).thenReturn(session);
		when(session.createQuery(Mockito.anyString(),Mockito.any())).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<>(taskList));
		assertNotNull(programManagerDao.getTasks(123));
	}
	
	@Test
	public void testgetParentTask() throws Exception {	
		List<ParentTask> taskList = new ArrayList<>();
		ParentTask task = new ParentTask();
		Project project = new Project();
		project.setProjectId(123);
		task.setProject(project);
		taskList.add(task);
		when(entityManager.unwrap(Mockito.any())).thenReturn(session);
		when(session.createQuery(Mockito.anyString(),Mockito.any())).thenReturn(query);
		when(query.getResultList()).thenReturn(new ArrayList<>(taskList));
		assertNotNull(programManagerDao.getParentTask(123));
	}
	
	@Test(expected=Exception.class)
	public void testgetParentTaskException() throws Exception {
		programManagerDao.getParentTask(123);
	}
	
}
