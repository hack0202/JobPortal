package com.JobPortalWeb.jobwebapp.Entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class WorkExperience 
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String company;
	private String position;
	private Date startdate;
	private Date enddate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="employee_id")
	@JsonIgnore
	private Employee employee;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public WorkExperience(int id, String company, String position, Date startdate, Date enddate, Employee employee) {
		super();
		this.id = id;
		this.company = company;
		this.position = position;
		this.startdate = startdate;
		this.enddate = enddate;
		this.employee = employee;
	}
	public WorkExperience() {
		
	}
}
