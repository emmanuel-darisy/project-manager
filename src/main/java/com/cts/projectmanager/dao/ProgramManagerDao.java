package com.cts.projectmanager.dao;

import java.util.List;

import com.cts.projectmanager.model.ParentTask;
import com.cts.projectmanager.model.Project;
import com.cts.projectmanager.model.Task;
import com.cts.projectmanager.model.User;


public interface ProgramManagerDao {
	
	public User addOrUpdateUser(User user) throws Exception;
	public String deleteUser(int userId)  throws Exception;;
	public List<User> getUsers() throws Exception;
	
	public String addProject(Project project,int userId) throws Exception;
	public String updateProject(Project project) throws Exception;
	public String deleteProject(Project project) throws Exception;
	public List<Project> getProjects() throws Exception;
	
	public String addTask(Task task);
	public String updateTask(Task task);
	
	
	public List<Task> getTasks();
	public List<Task> getTasks(int projectId);
	
	public String addParentTask(ParentTask parentTask);
	public List<ParentTask> getParentTask(int projectId);

	
}
