package com.JobPortalWeb.jobwebapp.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.JobPortalWeb.jobwebapp.BeanDto.JobDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.JobResponse;

public interface JobService {
	
	String createJob(JobDtoBean jobDtoBean, int employerId) throws Exception;
	
	 List<JobResponse> findByAllJobsByEmployerId(int id);
	 
	 Page<JobResponse> getAllJobs(Pageable pageable);
	    String deleteJob(int employerId, int jobId);
	    JobResponse getJobByJobId(int jobId);
		Page<JobResponse> searchJob(String jobCategory, String jobType, String country, String skills, String title,
                String salaryRange, String experience, String companyName, String city, Pageable pageable);

}
