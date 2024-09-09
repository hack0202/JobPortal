package com.JobPortalWeb.jobwebapp.Entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Qualification 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
	private String degree;
	private Date startdate;
	private Date enddate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="employee_id")
	@JsonBackReference
	private Employee employee;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
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
	
//args constructor

//	no args 
	public Qualification() 
	{
		
		
	}

public Qualification(Integer id, String degree, Date startdate, Date enddate, Employee employee) {
	super();
	this.id = id;
	this.degree = degree;
	this.startdate = startdate;
	this.enddate = enddate;
	this.employee = employee;
}

}
