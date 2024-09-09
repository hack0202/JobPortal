package com.JobPortalWeb.jobwebapp.BeanDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EmployerApplicationsJobsDtoBean {

    private String companyName;
    private String CompanyLogo;
    private String jobTitle;
    private String jobCategory;
    private String type;
    private String employerName;
    private String location;
    private int applicationId;
    private String status;
    
    
    
    public EmployerApplicationsJobsDtoBean() {
    	
    }

}
