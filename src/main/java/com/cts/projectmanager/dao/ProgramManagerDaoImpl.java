package com.cts.projectmanager.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cts.projectmanager.model.ParentTask;
import com.cts.projectmanager.model.Project;
import com.cts.projectmanager.model.Task;
import com.cts.projectmanager.model.User;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Repository
public class ProgramManagerDaoImpl implements ProgramManagerDao {
	@Autowired
	EntityManager entityManager;
	
	@Override
	public User addOrUpdateUser(User user) throws Exception {
		try{
			Session session = entityManager.unwrap(Session.class);
			session.saveOrUpdate(user);
			log.info("Added user with user id "+user.getUserId());
			}catch(Exception e) {
				throw e;
			}
			return user;
	}

	@Override
	public String deleteUser(int userId) throws Exception{
		try{
			List<Project> projects = new ArrayList<>();
			Session session = entityManager.unwrap(Session.class);
			User user = session.get(User.class, userId);			
			Query query2 = session.createQuery("from Project project where project.user.userId=:user_id");
			query2.setParameter("user_id", userId);
			projects = query2.getResultList();
			for(Project p:projects) {
				p.setUser(null);
				session.saveOrUpdate(p);
			}	
			session.delete(user);
			}catch(Exception e) {
				throw e;
			}
			return "deleted";
	}

	@Override
	public List<User> getUsers() throws Exception{
		List<User> usrList = new ArrayList<>();
		try{
			Session session = entityManager.unwrap(Session.class);
			Query query = session.createQuery("from User",User.class);
			usrList=  query.getResultList();	
		}catch(Exception e) {
			throw e;
		}
				
		return usrList;
	}

	
	@Override
	public String addProject(Project project,int userId)  throws Exception{
		log.info("Adding project inside DAO IMPL");
		try{
			Session session = entityManager.unwrap(Session.class);
			
			User user = session.get(User.class, userId);
			//log.info("Before addProject "+user.getUserId());
			project.setUser(user);
			session.saveOrUpdate(project);
			//log.info("Added addProject "+user.getUserId());
		}catch(Exception e) {
			throw e;
		}
		
		
		return "project added or updated";
	}
	
	@Override
	public String updateProject(Project project) throws Exception {
		
		log.info("Inside Update project DAO IMPL");
		try{
			Session session = entityManager.unwrap(Session.class);
			User user = session.get(User.class, project.getUser().getUserId());
			project.setUser(user);
			session.saveOrUpdate(project);	
		}catch(Exception e) {
			throw e;
		}
		
		return "project added or updated";
	}
	
	@Override
	public List<Project> getProjects() throws Exception{
		List<Project> projectList=  new ArrayList<>();
		try {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("from Project",Project.class);
		projectList=  query.getResultList();
		/*if(null != projectList && projectList.size()>=1) {
			projectList.stream().forEach(project->{
				int projectId = project.getProjectId();
				Query query2 = session.createQuery("from Task task where task.project.projectId=:project_id");
				query2.setParameter("project_id", projectId);
				Query query3 = session.createQuery("from Task task where task.status='completed' and task.project.projectId=:project_id");
				query3.setParameter("project_id", projectId);
				project.setTaskCount(query2.getResultList().size());
				project.setCompletedTasks(query3.getResultList().size());	
				log.info("TaskCount"+project.getTaskCount());
			});
		}*/
		for(Project p: projectList) {
			int projectId = p.getProjectId();
			Query query2 = session.createQuery("from Task task where task.project.projectId=:project_id");
			query2.setParameter("project_id", projectId);
			Query query3 = session.createQuery("from Task task where task.status='completed' and task.project.projectId=:project_id");
			query3.setParameter("project_id", projectId);
			p.setTaskCount(query2.getResultList().size());
			p.setCompletedTasks(query3.getResultList().size());
			log.info("TaskCount"+p.getTaskCount());
		}
		}catch(Exception e) {
			throw e;
		}
		return projectList;
	}

	
	@Override
	public String deleteProject(int projectId) throws Exception{
		try{
			Session session = entityManager.unwrap(Session.class);
			Project project = session.get(Project.class, projectId);
			 
			Query query2 = session.createQuery("from Task task where task.project.projectId=:project_id");
			query2.setParameter("project_id", projectId);
			List<Task> tasks = query2.getResultList();
			//session.delete(project);
			for(Task task:tasks) {
				session.delete(task);
			}
			
			Query query3 = session.createQuery("from ParentTask parenttask where parenttask.project.projectId=:project_id");
			query3.setParameter("project_id", projectId);
			List<ParentTask> parenttasks = query3.getResultList();

			for(ParentTask parenttask:parenttasks) {
				session.delete(parenttask);
			}
			//Thread.sleep(5000);
			session.delete(project);
			}catch(Exception e) {
				throw e;
			}
			return "project deleted";
	}

/*	@Override
	public String addTask(Task task, int userId, int projectId,int parentId) {
		Session session = entityManager.unwrap(Session.class);
		
		User user = session.get(User.class, userId);
		Project project = session.get(Project.class, projectId);
		ParentTask parentTask = session.get(ParentTask.class, parentId);
		task.setUser(user);
		task.setProject(project);
		task.setParenttask(parentTask);
		session.saveOrUpdate(task);
		return "task added";
	}*/
	
	@Override
	public String addTask(Task task) throws Exception{
		String status="task added";
		try {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(task);
		}catch(Exception e) {
			status= "task adding failed";
			throw e;
		}
		return status;
	}
	
	@Override
	public String updateTask(Task task) throws Exception{
		String status="task updated";
		try {
			Session session = entityManager.unwrap(Session.class);
			session.saveOrUpdate(task);
		}catch(Exception e) {
			status= "task updation failed";
			throw e;
		}
		return status;
	}
	
	@Override
	public String addParentTask(ParentTask parentTask) throws Exception{
		try{
			Session session = entityManager.unwrap(Session.class);
			session.saveOrUpdate(parentTask);
			}catch(Exception e) {
				throw e;
			}
			return "parent task updated";
	}

	@Override
	public List<Task> getTasks() throws Exception {
		List<Task> taskList= new ArrayList<>();
		try {
			Session session = entityManager.unwrap(Session.class);			
			Query query = session.createQuery("from Task",Task.class);
			taskList=  query.getResultList();
		}catch(Exception e) {
			throw e;
		}
				
		return taskList;
	}
	
	@Override
	public List<Task> getTasks(int projectId) throws Exception {
		List<Task> taskList= new ArrayList<>();
		try {
			Session session = entityManager.unwrap(Session.class);
			Query query = session.createQuery("from Task",Task.class);
			taskList=  query.getResultList();
			taskList = taskList.stream().filter(task->task.getProject()!=null && projectId == task.getProject().getProjectId()).collect(Collectors.toList());
		}catch(Exception e) {
			throw e;
		}
		
		return taskList;
	}
	
	
	
	@Override
	public List<ParentTask> getParentTask(int projectId) throws Exception {
		
		List<ParentTask> parentTaskList = new ArrayList<ParentTask>();
		try{
			Session session = entityManager.unwrap(Session.class);
			
			Query query = session.createQuery("from ParentTask",ParentTask.class);
			parentTaskList=  query.getResultList();
			parentTaskList = parentTaskList.stream().filter(parentTask->
			(parentTask.getProject()!= null &&parentTask.getProject().getProjectId()==projectId)).collect(Collectors.toList());
			
		}catch(Exception e) {
			throw e;
			//e.printStackTrace();
		}
			
		return parentTaskList;
	
	}
}
