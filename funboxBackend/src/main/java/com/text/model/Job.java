package com.text.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
private String jobTitle;
private String jobDescription;
private String skillsRequired;
private String jobLocation;
private String salary;
private String experienceRequired;
private String companyName;
private Date postedOn;
private boolean active;
public boolean isActive() {
	return active;
}
public void setActive(boolean active) {
	this.active = active;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getJobTitle() {
	return jobTitle;
}
public void setJobTitle(String jobTitle) {
	this.jobTitle = jobTitle;
}
public String getJobDescription() {
	return jobDescription;
}
public void setJobDescription(String jobDescription) {
	this.jobDescription = jobDescription;
}
public String getSkillsRequired() {
	return skillsRequired;
}
public void setSkillsRequired(String skillsRequired) {
	this.skillsRequired = skillsRequired;
}
public String getJobLocation() {
	return jobLocation;
}
public void setJobLocation(String jobLocation) {
	this.jobLocation = jobLocation;
}
public String getSalary() {
	return salary;
}
public void setSalary(String salary) {
	this.salary = salary;
}
public String getExperienceRequired() {
	return experienceRequired;
}
public void setExperienceRequired(String experienceRequired) {
	this.experienceRequired = experienceRequired;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public Date getPostedOn() {
	return postedOn;
}
public void setPostedOn(Date postedOn) {
	this.postedOn = postedOn;
}

}
