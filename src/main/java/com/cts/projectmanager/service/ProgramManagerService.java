package com.cts.projectmanager.service;

import java.util.List;

import com.cts.projectmanager.model.ParentTask;
import com.cts.projectmanager.model.Project;
import com.cts.projectmanager.model.Task;
import com.cts.projectmanager.model.User;


public interface ProgramManagerService {
	
	public User addUser(User user) throws Exception;
	public User updateUser(User user) throws Exception;
	public String deleteUser(int userId) throws Exception;;
	public List<User> getUsers() throws Exception;
	
	public String addProject(Project project,int userId) throws Exception;
	public String updateProject(Project project) throws Exception;
	public List<Project> getProjects() throws Exception;
	public String deleteProject(int projectID) throws Exception;
	
	
	//public String addTask(Task task, int userId, int projectId,int parentId);
	public String addTask(Task task) throws Exception;
	public String updateTask(Task task) throws Exception;
	public List<Task> getTasks() throws Exception;
	public List<Task> getTasks(int projectId) throws Exception;
	
	public String addParentTask(ParentTask parentTask) throws Exception;
	public List<ParentTask> getParentTask(int projectId) throws Exception;
	
	
}
