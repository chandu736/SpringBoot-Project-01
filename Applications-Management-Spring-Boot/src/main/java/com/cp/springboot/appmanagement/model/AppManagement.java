package com.cp.springboot.appmanagement.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="appointment")
public class AppManagement {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String userName;
	
	@Size(min=10, message="Enter atleast 10 characters...")
	private String description;
	
	private Date targetDate;
	
	public AppManagement() {
		super();
	}
	public AppManagement(String user, String desc, Date targetDate, boolean isDone) {
		super();
		this.userName=user;
		this.description=desc;
		this.targetDate=targetDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	
}
