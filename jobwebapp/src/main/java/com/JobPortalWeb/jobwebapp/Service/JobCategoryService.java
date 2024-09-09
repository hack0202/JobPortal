package com.JobPortalWeb.jobwebapp.Service;

import java.util.List;

import com.JobPortalWeb.jobwebapp.Entity.JobCategory;

public interface JobCategoryService 
{
	
	JobCategory createJobCategory(JobCategory jobCategory);
	 List<JobCategory>getAllJobCategories();
	 JobCategory updateJobCategory(int id , JobCategory jobCategory);
	 
	 String deleteJobCategoryById(int id);
	
}
