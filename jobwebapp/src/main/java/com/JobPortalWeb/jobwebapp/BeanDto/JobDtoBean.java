package com.JobPortalWeb.jobwebapp.BeanDto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class JobDtoBean {
	
	private Integer id;
	private String title;
	private String companyName;
	private String jobDescription;
	private String skills;
	private String jobType;
	private String salaryRange;
	private String experience;
	private String country;
	private String city;
	private String street;
	private String pinCode;
	private String logoPath;
	private  Integer employerId;
	
	public JobDtoBean() {
		
	}
}
