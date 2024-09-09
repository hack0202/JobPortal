package com.JobPortalWeb.jobwebapp.Service;

import java.util.List;

import com.JobPortalWeb.jobwebapp.BeanDto.EmployerApplicationsJobsDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.JobApplicationDtoBean;

public interface JobApplicationService {

	String  applyJob(int employeeId, int jobId);
	 List<JobApplicationDtoBean> getAllApplicationsByEmployee(int employeeId);
	 
	 String cancelApplication(int employeeId, int jobId ,int applicationId);
	 
	 List<EmployerApplicationsJobsDtoBean> getAllApplicationsByEmployer(int employerId);
	 String updateApplicationStatusByEmployer(int applicationId,String status);
	 List<EmployerApplicationsJobsDtoBean> getAllApplications();
}
