package com.cts.projectmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="parenttask")
public class ParentTask {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="parent_id")
	private int parentId;

	@Column(name="parenttask")
	private String parentTaskName;

	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;
	

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentTaskName() {
		return parentTaskName;
	}

	public void setParentTask(String parentTaskName) {
		this.parentTaskName = parentTaskName;
	}
	
	@Override
	public String toString() {
		return "ParentTask [parentId=" + parentId + ", parentTaskName=" + parentTaskName + "]";
	}
	
}

