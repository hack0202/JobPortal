package com.JobPortalWeb.jobwebapp.BeanDto;

import java.util.Date;

public class QualificationDtoBean 
{

	private Integer id;
   
	private String degree;
    private Date startdate;
    private Date enddate;
    
//    getters and setters


	public String getDegree() {
		return degree;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

    public QualificationDtoBean() {
    	
    }
	public QualificationDtoBean(Integer id, String degree, Date startdate, Date enddate) {
		super();
		this.id = id;
		this.degree = degree;
		this.startdate = startdate;
		this.enddate = enddate;
	}
    
}
