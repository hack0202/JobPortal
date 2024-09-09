package com.JobPortalWeb.jobwebapp.ServiceImpl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JobPortalWeb.jobwebapp.Entity.JobCategory;
import com.JobPortalWeb.jobwebapp.Repository.JobCategoryRepository;
import com.JobPortalWeb.jobwebapp.Service.JobCategoryService;
@Service
public class JobCategoryServiceImpl implements JobCategoryService {
	@Autowired
	private JobCategoryRepository jobCategoryRepository;

	@Override
	public JobCategory createJobCategory(JobCategory jobCategory) {
	
		return jobCategoryRepository.save(jobCategory) ;
	}

	@Override
	public List<JobCategory> getAllJobCategories() {
		
		return jobCategoryRepository.findAll();
	}

	@Override
	public JobCategory updateJobCategory(int id, JobCategory jobCategory) {
		JobCategory dbJobCategory = isJobCategoryExist(id);

        dbJobCategory.setTitle(jobCategory.getTitle());
        dbJobCategory.setDescription(jobCategory.getDescription());

        return jobCategoryRepository.save(dbJobCategory);
	}

	@Override
	public String deleteJobCategoryById(int id) {
		JobCategory jobCategory = isJobCategoryExist(id);
		
	
		 jobCategoryRepository.delete(jobCategory);
		 return "Job Category Deleted Successfully";
	}
	
	
//	helper
	  private JobCategory isJobCategoryExist(int id) {
	        JobCategory dbJobCategory = jobCategoryRepository.findById(id).orElseThrow(
	                () -> new  NoSuchElementException("Job Category with id " + id + " not found")
	        );

	        return dbJobCategory;
	    }


}
