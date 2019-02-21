package com.HRMS.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "hrms_employee_projects")
public class Employee_Projects implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8388999932325411703L;
	@Id
	@GeneratedValue(generator="gen")
    @GenericGenerator(name="gen",strategy="foreign",parameters=@Parameter(name="property",value="projects"))
	private int project_Id;
	private int empId;
	
	private String projectHandled;
	private Date dateStarted;
	private Date dateEnded;
	private String Status;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	 @PrimaryKeyJoinColumn//(name="leaveId" )
	private Projects projects;
	
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	public String getProjectHandled() {
		return projectHandled;
	}
	public void setProjectHandled(String projectHandled) {
		this.projectHandled = projectHandled;
	}
	public Date getDateStarted() {
		return dateStarted;
	}
	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}
	public Date getDateEnded() {
		return dateEnded;
	}
	public void setDateEnded(Date dateEnded) {
		this.dateEnded = dateEnded;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	public Projects getProjects() {
		return projects;
	}
	public void setProjects(Projects projects) {
		this.projects = projects;
	}
	
	public int getProject_Id() {
		return project_Id;
	}
	public void setProject_Id(int project_Id) {
		this.project_Id = project_Id;
	}
	@Override
	public String toString() {
		return "Employee_Projects [project_Id=" + project_Id + ", empId=" + empId + ", projectHandled=" + projectHandled
				+ ", dateStarted=" + dateStarted + ", dateEnded=" + dateEnded + ", Status=" + Status + ", projects="
				+ projects + "]";
	}
	
	
	
	
	

	

}
