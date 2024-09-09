package com.JobPortalWeb.jobwebapp.BeanDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data


public class JobResponse {
	
	private Integer jobId;
	private String title;
	private String companyName;
	private String jobDescription;
	private String skills;
	private String jobType;
	private String salaryRange;
	private String experience;
	private String street;
	private String city;
	private String pincode;
	private String country;
	private String logoPath;
	private JobCategoryDtoBean jobCategory;
	private Integer employerId;
	
	public JobResponse(Integer jobId, String title, String companyName, String jobDescription, String skills,
            String jobType, String salaryRange, String experience, String street, 
            String city, String pinCode, String country, String logoPath, 
            JobCategoryDtoBean jobCategory, Integer employerId) {
this.jobId = jobId;
this.title = title;
this.companyName = companyName;
this.jobDescription = jobDescription;
this.skills = skills;
this.jobType = jobType;
this.salaryRange = salaryRange;
this.experience = experience;
this.street = street;
this.city = city;
this.pincode = pinCode;
this.country = country;
this.logoPath = logoPath;
this.jobCategory = jobCategory;
this.employerId = employerId;
}


	
	public JobResponse() {
		
	}

}
