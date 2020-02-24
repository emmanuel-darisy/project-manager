package com.cts.projectmanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.projectmanager.model.ParentTask;
import com.cts.projectmanager.model.Project;
import com.cts.projectmanager.model.Task;
import com.cts.projectmanager.model.User;
import com.cts.projectmanager.service.ProgramManagerService;

import lombok.extern.slf4j.Slf4j;


/**
 * @author Emmanuel Darisy
 *
 */

@Slf4j
@RestController
@CrossOrigin
public class ProgramManagerController {

	@Autowired
	ProgramManagerService programManagerService;


	@RequestMapping(method=RequestMethod.POST, value="/user", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> addUserDetails(@RequestBody (required = false)User user) {		
		log.info("Inside add user return json");
		User response = null;
		try {
			response = programManagerService.addUser(user);
			return new ResponseEntity<User>(response, HttpStatus.OK);
		}catch(Exception e) {
			response = new User();
			response.setErrorCode("ADD-USER_FAILED");
			response.setErrorMessage("ADD-USER_FAILED ");
			return new ResponseEntity<User>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method=RequestMethod.PUT, value="/user", produces= {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User>  updateUserDetails(@RequestBody User user) {
		log.info("inside update user return json {}",user);
		User response = null;
		try {
			response =  programManagerService.updateUser(user);
			return new ResponseEntity<User>(response, HttpStatus.OK);
		} catch (Exception e) {
			response = new User();
			response.setErrorCode("UPDATE-USER_FAILED");
			response.setErrorMessage("UPDATE-USER_FAILED ");
			return new ResponseEntity<User>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/user/{userId}", produces= {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> deleteUserDetails( @PathVariable (name="userId") int userId) {
		log.info("Deleting user with UserId {}",userId);
		String status = null;
		try {
			status = programManagerService.deleteUser(userId);
			return new ResponseEntity<String>(status,HttpStatus.OK);
		} catch (Exception e) {
			status = "FAILED";
			return new ResponseEntity<String>(status,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method=RequestMethod.GET, value="/user", produces= {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<User>> getUsers() {
		log.info("Inside getUser");
		List<User> usersList = new ArrayList<>();
		try {
			usersList= programManagerService.getUsers();
			return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<User>>(usersList, HttpStatus.INTERNAL_SERVER_ERROR);
		}finally{
			log.info("Inside getUsers with size{}",usersList.size());
		}
	}

	@RequestMapping(method=RequestMethod.POST, value="/project/{userId}", produces= {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addProject(@RequestBody Project project, @PathVariable (name="userId") int userId) {
		log.info("Inside UpdateProject with userId {} ",project.getUser().getUserId());
		String status = null;
		try {
			status = programManagerService.addProject(project,userId);
			return new ResponseEntity<String>(status,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("FAILED",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/project", produces= {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> updateProject(@RequestBody Project project) {
		log.info("Inside UpdateProject with project Id & userId"+project.getUser().getUserId());
		try {
			return new ResponseEntity<String>(programManagerService.updateProject(project),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("FAILED",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/project", produces= {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Project>> getProjects() {
		log.info("Inside getProjects");
		List<Project> projects = new ArrayList<>();
		try {
			return new ResponseEntity<List<Project>>(programManagerService.getProjects(),HttpStatus.OK);
		} catch (Exception e) {
			 	return new ResponseEntity<List<Project>>(projects,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/project/{projectId}", produces= {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> deleteProject(@PathVariable(name="projectId") int projectId) {
		log.info("Inside deleteProject with project Id{}",projectId);
		try {
			return new ResponseEntity<>(programManagerService.deleteProject(projectId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/task", produces= {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addTask(@RequestBody Task task) {
		log.info("Inside addTask task info {}",task);
		try {
			return new ResponseEntity<>(programManagerService.addTask(task), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>("FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/task", produces= {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> updateTask(@RequestBody Task task) {
		log.info("Inside updateTask task info {}",task);
		try {
			return new ResponseEntity<>(programManagerService.updateTask(task), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>("FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/task", produces= {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Task>> getTasks () {
		log.info("Inside getTasks");
		List<Task> tasks = new ArrayList<>();
		try {
			return new ResponseEntity<>(programManagerService.getTasks(), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(tasks, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/task/{projectId}", produces= {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Task>> getTasksById (@PathVariable(name="projectId") int projectId) {
		List<Task> tasksList = new ArrayList<>();
		log.info("Inside getTasksById project {}",projectId);
		try {
			tasksList = programManagerService.getTasks(projectId);
			return new ResponseEntity<>(tasksList, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(tasksList, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/parentTask", produces= {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addParentTask(@RequestBody ParentTask parentTask) {
		log.info("Inside addParentTask parentTask info {}",parentTask);
		try {
		return new ResponseEntity<>(programManagerService.addParentTask(parentTask), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>("FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/parentTask/{projectId}", produces= {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ParentTask>> getParentTask(@PathVariable(name="projectId") int projectId) {
		List <ParentTask> parentTasksList= new ArrayList<>();
		log.info("Inside getParentTask by projectId {}",projectId);
		try {
			parentTasksList = programManagerService.getParentTask(projectId);
			return new ResponseEntity<>(parentTasksList,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(parentTasksList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		finally {
			log.info("ParentTaskList size"+parentTasksList.size());
		}
	}
	
}
