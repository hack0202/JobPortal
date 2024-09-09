package com.JobPortalWeb.jobwebapp.BeanDto;

import java.util.Date;

public class WorkExperienceDtoBean 
{ 
 private Integer id;
	private String company;
	private String position;
	private Date startdate;
	private Date enddate;
	


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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

	public WorkExperienceDtoBean(Integer id, String company, String position, Date startdate, Date enddate) {
		super();
		this.id = id;
		this.company = company;
		this.position = position;
		this.startdate = startdate;
		this.enddate = enddate;
	}
	public WorkExperienceDtoBean() {
		
	}

}
