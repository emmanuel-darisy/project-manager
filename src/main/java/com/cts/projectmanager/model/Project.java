package com.cts.projectmanager.model;

import static org.hamcrest.CoreMatchers.nullValue;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import com.sun.istack.Nullable;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="project_id")	
	private int projectId;
	
	@Column(name="project")
	private String projectName;
	
	@Column(name="startdate")
	private Date startDate;
	
	@Column(name="enddate")
	private Date endDate;
	
	@Column(name="priority")
	private int priority;
	
	@Transient
	private int taskCount;
	
	@Transient
	private int completedTasks;
	
	@ManyToOne
	@JoinTable(name="Manager", joinColumns={@JoinColumn(name="projectId", referencedColumnName="project_id")},
	inverseJoinColumns= {@JoinColumn(name="userId", referencedColumnName="user_Id")})	
	private User user;
	
	
	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public int getProjectId() {
		return projectId;
	}



	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}



	public String getProjectName() {
		return projectName;
	}



	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public int getPriority() {
		return priority;
	}



	public void setPriority(int priority) {
		this.priority = priority;
	}



	public int getTaskCount() {
		return taskCount;
	}



	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}



	public int getCompletedTasks() {
		return completedTasks;
	}



	public void setCompletedTasks(int completedTasks) {
		this.completedTasks = completedTasks;
	}



	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", priority=" + priority + ", taskCount=" + taskCount + ", completedTasks="
				+ completedTasks + ", user=" + user + "]";
	}








	
}
