package com.JobPortalWeb.jobwebapp.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class EmployeeProfileInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Lob
	@Column(columnDefinition = "LONGTEXT")
	private String photopath;
	private String github;
	private String linkedin;
	private String bio;
	private String website;
	@Lob
	@Column(columnDefinition = "LONGTEXT")
	private String resumepath;
	
	@OneToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhotopath() {
		return photopath;
	}
	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}
	public String getGithub() {
		return github;
	}
	public void setGithub(String github) {
		this.github = github;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getResumepath() {
		return resumepath;
	}
	public void setResumepath(String resumepath) {
		this.resumepath = resumepath;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public EmployeeProfileInfo(int id, String photopath, String github, String linkedin, String bio, String website,
			String resumepath, Employee employee) {
		super();
		this.id = id;
		this.photopath = photopath;
		this.github = github;
		this.linkedin = linkedin;
		this.bio = bio;
		this.website = website;
		this.resumepath = resumepath;
		this.employee = employee;
	}
	public EmployeeProfileInfo() {
		
	}
	

}
