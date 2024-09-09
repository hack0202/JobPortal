package com.JobPortalWeb.jobwebapp.ServiceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.JobPortalWeb.jobwebapp.BeanDto.JobCategoryDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.JobDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.JobResponse;
import com.JobPortalWeb.jobwebapp.Entity.Employer;
import com.JobPortalWeb.jobwebapp.Entity.Job;
import com.JobPortalWeb.jobwebapp.Entity.JobCategory;
import com.JobPortalWeb.jobwebapp.Repository.EmployerRepo;
import com.JobPortalWeb.jobwebapp.Repository.JobCategoryRepository;
import com.JobPortalWeb.jobwebapp.Repository.JobRepository;
import com.JobPortalWeb.jobwebapp.Service.JobService;

import io.jsonwebtoken.io.IOException;
import jakarta.persistence.EntityNotFoundException;
@Service
public class JobServiceImpl implements JobService{
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private EmployerRepo employerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JobCategoryRepository jobCategoryRepository;
	

	@Override
	public String createJob(JobDtoBean jobDtoBean, int employerId) throws IOException {
//		here i m checking if employee with id exists or not
		 Employer employerr = checkEmployerExist(employerId);
//			here i m checking if Job category  with title exists or not
		 JobCategory jobCategory =checkJobCategoryExist(jobDtoBean.getTitle());
		 String logopath= jobDtoBean.getLogoPath();
//		 setting data to job 
		 Job job = mapToJob(jobDtoBean);
		 job.setSkills(jobDtoBean.getSkills());
		 
		 job.setLogoPath(logopath);
		 job.setEmployer(employerr);
		 job.setJobCategory(jobCategory);

// Save the Job entity to the database
		 jobRepository.save(job);

		 return "Job Created Successfully";
		 	
	}
	@Override
	public List<JobResponse> findByAllJobsByEmployerId(int id) {
	    // checkEmployerExist(id); // Uncomment this line if you have this method to check if the employer exists.

	    return jobRepository.findByEmployerId(id).stream()
	        .map(job -> {
	            // Create a new JobResponse object
	            JobResponse jobResponse = new JobResponse();
	            
	            // Set the fields
	            jobResponse.setJobId(job.getId());
	            jobResponse.setTitle(job.getTitle());
	            jobResponse.setCompanyName(job.getCompanyName());
	            jobResponse.setJobDescription(job.getJobDescription());
	            jobResponse.setSkills(job.getSkills());
	            jobResponse.setJobType(job.getJobType());
	            jobResponse.setSalaryRange(job.getSalaryRange());
	            jobResponse.setExperience(job.getExperience());
	            jobResponse.setStreet(job.getStreet());
	            jobResponse.setCity(job.getCity());
	            jobResponse.setPincode(job.getPinCode());
	            jobResponse.setCountry(job.getCountry());

	            // Directly set the Base64 string as the logo path
	            jobResponse.setLogoPath(job.getLogoPath());
	            
	            JobCategoryDtoBean jobCategoryDto = new JobCategoryDtoBean();
	            // Assuming `getJobCategory` returns a JobCategory entity
	            jobCategoryDto.setTitle(job.getJobCategory().getTitle());
	            jobCategoryDto.setDescription(job.getJobCategory().getDescription());
	            jobResponse.setJobCategory(jobCategoryDto);

	            // Set the employer ID
	            jobResponse.setEmployerId(job.getEmployer().getId());

	            return jobResponse;
	        })
	        .collect(Collectors.toList());
	}
	 @Override
	    public Page<JobResponse> getAllJobs(Pageable pageable) {
	        Page<Job> jobPage = jobRepository.findAll(pageable);
	        return jobPage.map(this::mapToJobdto);
	    }

	@Override
	public String deleteJob(int employerId, int jobId) {
		checkEmployerExist(employerId);
        Job job = checkJobExist(jobId);
        job.setEmployer(null);
        job.setJobCategory(null);
        jobRepository.delete(job);
        return "Job Deleted Successfully";
	}
	
//	ALTER TABLE job_application DROP FOREIGN KEY FKdepcvxeq3gyb4438ws0qjycc7;
//	ALTER TABLE job_application 
//	ADD CONSTRAINT FKdepcvxeq3gyb4438ws0qjycc7 
//	FOREIGN KEY (job_id) REFERENCES job(id) 
//	ON DELETE CASCADE;

	@Override
	public JobResponse getJobByJobId(int jobId) {
	    Job job = jobRepository.findById(jobId)
	            .orElseThrow(() -> new NoSuchElementException("Job not found with id: " + jobId));

	    // Log the retrieved job
	    System.out.println("Retrieved Job: " + job);

	    return mapToJobdto(job);
	}
	@Override
	public Page<JobResponse> searchJob(String jobCategory, String jobType, String country, String skills, String title,
	                                    String salaryRange, String experience, String companyName, String city, Pageable pageable) {
	    return jobRepository.searchJobs(jobCategory, jobType, country, skills, title, salaryRange, experience, companyName, city, pageable);
	}





	
	
	private Job mapToJob(JobDtoBean jobDto) {
	    Job job = modelMapper.map(jobDto, Job.class);
	    
	    // Explicitly set fields if needed
	    if (jobDto.getTitle() != null) {
	        job.setTitle(jobDto.getTitle());
	    }
	    
	    return job;
	}
	
	private JobResponse mapToJobDto(Job job) {
	    JobResponse jobResponse = modelMapper.map(job, JobResponse.class);
	    if (job.getLogoPath() != null) {
	        jobResponse.setLogoPath(Path.of(job.getLogoPath()).getFileName().toString());
	    }
	    if (job.getJobCategory() != null) {
	        JobCategoryDtoBean jobCategoryDto = new JobCategoryDtoBean();
	        jobCategoryDto.setTitle(job.getJobCategory().getTitle());
	        jobCategoryDto.setDescription(job.getJobCategory().getDescription());
	        jobResponse.setJobCategory(jobCategoryDto);
	    }
	    if (job.getEmployer() != null) {
	        jobResponse.setEmployerId(job.getEmployer().getId());
	    }

	    return jobResponse;
	}



	
	  private Employer checkEmployerExist(int employerId) {

	        Employer employer = employerRepository.findById(employerId).orElseThrow(
	                () -> new NoSuchElementException(String.format("Employer with id: %d not found", employerId))
	        );
	        return employer;
	    }
	  
	  
	  
	  private JobCategory checkJobCategoryExist(String title) {
	        JobCategory jobCategory = jobCategoryRepository.findByTitle(title).orElseThrow(
	                () -> new EntityNotFoundException(String.format("JobCategory with title: '%s' not found", title))
	        );

	        return jobCategory;
	    }

	   private Job checkJobExist(int jobId) 
	    {

	        return jobRepository.findById(jobId).orElseThrow(
	                () -> new NoSuchElementException("Job not found with id: " + jobId)
	        );

	    }
	
	   private JobResponse mapToJobdto(Job job) {
		    return new JobResponse(
		        job.getId(),
		        job.getTitle(),
		        job.getCompanyName(),
		        job.getJobDescription(),
		        job.getSkills(),
		        job.getJobType(),
		        job.getSalaryRange(),
		        job.getExperience(),
		        job.getStreet(),
		        job.getCity(),
		        job.getPinCode(),
		        job.getCountry(),
		        job.getLogoPath(),
		        new JobCategoryDtoBean(job.getJobCategory().getTitle(), job.getJobCategory().getDescription()),
		        job.getEmployer().getId()
		    );
		}
	
	






//	@Override
//	public List<JobResponse> findByAllJobsByEmployerId(int employerId) {
//
//	    // Assuming the check for employer existence is done elsewhere or is commented out
//	    // checkEmployerExist(employerId);
//
//	    return jobRepository.findByAllJobsByEmployerId(employerId).stream()
//	            .map(job -> {
//	                // Create a new JobResponse object
//	                JobResponse jobResponse = new JobResponse();
//	                
//	                // Set the fields
//	                jobResponse.setJobId(job.getJobId());
//	                jobResponse.setTitle(job.getTitle());
//	                jobResponse.setCompanyName(job.getCompanyName());
//	                jobResponse.setJobDescription(job.getJobDescription());
//	                jobResponse.setSkills(job.getSkills());
//	                jobResponse.setJobType(job.getJobType());
//	                jobResponse.setSalaryRange(job.getSalaryRange());
//	                jobResponse.setExperience(job.getExperience());
//	                jobResponse.setStreet(job.getStreet());
//	                jobResponse.setCity(job.getCity());
//	                jobResponse.setPincode(job.getPincode());
//	                jobResponse.setCountry(job.getCountry());
//
//	                // Process the company logo to only get the filename
//	                String fullPath = job.getLogoPath();
//	                Path path = Paths.get(fullPath);
//	                jobResponse.setLogoPath(path.getFileName().toString());
//
//	                // Set the job category (assuming you have a JobCategoryDtoBean)
//	                jobResponse.setJobCategory(job.getJobCategory());
//
//	                // Set the employer ID
//	                jobResponse.setEmployerId(job.getEmployerId());
//
//	                return jobResponse;
//	            })
//	            .collect(Collectors.toList());
//	}
//
//
}
