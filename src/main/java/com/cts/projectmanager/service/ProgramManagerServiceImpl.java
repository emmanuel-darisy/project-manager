package com.cts.projectmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.projectmanager.dao.ProgramManagerDao;
import com.cts.projectmanager.model.ParentTask;
import com.cts.projectmanager.model.Project;
import com.cts.projectmanager.model.Task;
import com.cts.projectmanager.model.User;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class ProgramManagerServiceImpl implements ProgramManagerService{

	
	//private static final Logger log = LogManager.getLogger(ProgramManagerController.class);
	
	
	@Autowired
	ProgramManagerDao programManagerDao;
	
	@Override
	@Transactional
	public User addUser(User user) throws Exception {
		log.info("Invoking addUser with userID{}",user.getUserId());
		return programManagerDao.addOrUpdateUser(user);
	}

	@Override
	@Transactional
	public User updateUser(User user) throws Exception {
		log.info("Invoking updateUser with userID{}",user.getUserId());
		log.info("Update usre with userId"+user.getUserId());
		return programManagerDao.addOrUpdateUser(user);
	}

	@Override
	@Transactional
	public String deleteUser(int userId) throws Exception{
		log.info("Invoking deleteUser with userID{}",userId);
		return programManagerDao.deleteUser(userId);
	}

	@Override
	@Transactional
	public List<User> getUsers() throws Exception {
		log.info("Invoking getUsers ");
		return programManagerDao.getUsers();
	}
	
	@Override
	@Transactional
	public String addProject(Project project,int userId) throws Exception {
		log.info("Invoking addProject ");
		return programManagerDao.addProject(project,userId);
		
	}
	
	@Override
	@Transactional
	public String updateProject(Project project) throws Exception {
		log.info("Invoking addProject ");
		return programManagerDao.updateProject(project);
		
	}
	
	@Override
	@Transactional
	public List<Project> getProjects() throws Exception {
		log.info("Invoking getUsers ");
		return programManagerDao.getProjects();
	}
	
	@Override
	@Transactional
	public String deleteProject(int projectId) throws Exception {
		log.info("Invoking delete Project ");
		return programManagerDao.deleteProject(projectId);
		
	}
	
	/*@Override
	@Transactional
	public String addTask(Task task, int userId, int projectId, int parentId) {
		logger.debug("Invoking addTask ");
		return programManagerDao.addTask(task,userId,projectId,parentId);
	}*/
	@Override
	@Transactional
	public String addTask(Task task) throws Exception {
		log.info("Invoking addTask ");
		return programManagerDao.addTask(task);
	}
	
	@Override
	@Transactional
	public String updateTask(Task task) throws Exception {
		log.info("Invoking updateTask ");
		return programManagerDao.updateTask(task);
	}
	
	
	@Override
	@Transactional
	public String addParentTask(ParentTask parentTask) throws Exception {
		log.info("Invoking addParent Task ");
		return programManagerDao.addParentTask(parentTask);
	}
	
	@Override
	@Transactional
	public List<Task> getTasks() throws Exception {
		log.info("Invoking getTasks ");
		return programManagerDao.getTasks();
	}
	
	@Override
	@Transactional
	public List<Task> getTasks(int projectId) throws Exception {
		log.info("Invoking getTasks by projectId ");
		return programManagerDao.getTasks(projectId);
	}
	
	@Override
	@Transactional
	public List<ParentTask> getParentTask(int projectId) throws Exception {
		log.info("Invoking get ParentTask ");
		return programManagerDao.getParentTask(projectId);
	}
	

}
