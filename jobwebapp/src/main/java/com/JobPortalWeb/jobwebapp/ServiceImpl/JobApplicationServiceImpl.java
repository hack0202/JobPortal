package com.JobPortalWeb.jobwebapp.ServiceImpl;
import java.util.Base64;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.hibernate.graph.spi.AppliedGraph;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.JobPortalWeb.jobwebapp.BeanDto.EmployerApplicationsJobsDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.JobApplicationDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.JobResponse;
import com.JobPortalWeb.jobwebapp.Entity.Employee;
import com.JobPortalWeb.jobwebapp.Entity.Job;
import com.JobPortalWeb.jobwebapp.Entity.JobApplication;
import com.JobPortalWeb.jobwebapp.ExceptionHandler.JobPortalException;
import com.JobPortalWeb.jobwebapp.Repository.EmployeeRepo;
import com.JobPortalWeb.jobwebapp.Repository.JobApplicationRepository;
import com.JobPortalWeb.jobwebapp.Repository.JobRepository;
import com.JobPortalWeb.jobwebapp.Service.JobApplicationService;
import com.JobPortalWeb.jobwebapp.Utility.AppConstant.JOB_APPLICATION_STATUS;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Path;
@Service
public class JobApplicationServiceImpl implements JobApplicationService 
{
	
	@Autowired
	private JobApplicationRepository jobApplicationRepository;
	
	@Autowired
	private EmployeeRepo employeeRepository;
	
	@Autowired
    private JobRepository jobRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	

	@Override
	public String applyJob(int employeeId, int jobId) {
	    Employee employee = checkEmployeeExistence(employeeId);
	    if (employee == null) {
	        return "Employee not found";
	    }
	    Job job = checkJobExistence(jobId);
	    if (job == null) {
	        return "Job not found";
	    }

	    // Check if the employee has already applied for this job
	    JobApplication existingApplication = jobApplicationRepository
	        .findByEmployeeIdAndJobId(employeeId, jobId);
	    if (existingApplication != null) {
	        return "You have already applied for this job.";
	    }

	    // Create a new application if not already applied
	    JobApplication jobApplication = new JobApplication();
	    jobApplication.setEmployee(employee);
	    jobApplication.setJob(job);
	    jobApplication.setAppliedDate(LocalDateTime.now());
	    jobApplication.setStatus(JOB_APPLICATION_STATUS.APPLIED.toString());

	    jobApplicationRepository.save(jobApplication);
	    return "Job Applied Successfully";
	}

		
		
//		Employee employee = checkEmployeeExistence(employeeId);
//	    if (employee == null) {
//	        return "Employee not found";
//	    }
//	    Job job = checkJobExistence(jobId);
//	    if (job == null) {
//	        return "Job not found";
//	    }
//
//	    System.out.println("Employee ID: " + employeeId + ", Employee Name: " + employee.getFirstName());
//	    
//		
////		Job job= checkJobExistence(jobId);
//		JobApplication jobApplication= new JobApplication();
//		  jobApplication.setEmployee(employee);
//		  jobApplication.setJob(job);
//		  jobApplication.setAppliedDate(LocalDateTime.now());
//		  jobApplication.setStatus(JOB_APPLICATION_STATUS.APPLIED.toString());
//		  jobApplicationRepository.save(jobApplication);
//		  return "Job Applied Successfully";
		
		
	
	

	@Override
	public List<JobApplicationDtoBean> getAllApplicationsByEmployee(int employeeId) {
	    Employee employee = checkEmployeeExistence(employeeId); // Ensure this method throws an appropriate exception if not found
	    List<JobApplication> jobApplications = jobApplicationRepository.findAllApplicationByEmployeeId(employeeId);

	    // Stream processing with null checks and proper DTO mapping
	    List<JobApplicationDtoBean> allJobApplications = jobApplications.stream()
	            .filter(jobApp -> jobApp != null) // Optional: filter out null values, if applicable
	            .map(jobApp -> {
	                JobApplicationDtoBean jobApplicationDto = new JobApplicationDtoBean();
	                jobApplicationDto.setApplicationId(jobApp.getId()); // Use the correct application ID
	                jobApplicationDto.setStatus(jobApp.getStatus());
	                jobApplicationDto.setAppliedDate(jobApp.getAppliedDate());
	                jobApplicationDto.setJobResponse(mapToJobDto(jobApp.getJob()));
	                return jobApplicationDto;
	            })
	            .collect(Collectors.toList());

	    return allJobApplications;
	}
	
	
	
	@Override
	public String cancelApplication(int employeeId, int jobId, int applicationId) 
	{
		
	        JobApplication jobApplication = checkJobApplicationExistence(applicationId);
	        jobApplication.setStatus(JOB_APPLICATION_STATUS.CANCELED.toString());
	        jobApplicationRepository.save(jobApplication);
	        return "Job Application Cancel Successfully";
	
	}
	
	@Override
	public List<EmployerApplicationsJobsDtoBean> getAllApplications() {
	
		 return jobApplicationRepository.findAllApplication();
	}
	

	
	
	
	
	
	
	
	private JobResponse mapToJobDto(Job job) {
	    if (job == null) {
	        return null; // Handle null job case appropriately
	    }
	    return modelMapper.map(job, JobResponse.class); 
	}
	

	public Employee checkEmployeeExistence(int employeeId) {
	    // Example method implementation
	    return employeeRepository.findById(employeeId).orElse(null);
	}
	  
	  private Job checkJobExistence(int jobId) {

	        Job job = jobRepository.findById(jobId).orElseThrow(
	                () -> new EntityNotFoundException("job with id " + jobId + " not found")	
	        );
	        return job;
	    }
	  
	  private JobApplication checkJobApplicationExistence(int applicationId) {

		    JobApplication jobApplication = jobApplicationRepository.findById(applicationId).orElseThrow(
		            () -> new com.JobPortalWeb.jobwebapp.ExceptionHandler.EntityNotFoundException("JobApplication", "id", applicationId)
		    );
		    return jobApplication;
		}


	@Override
	public String updateApplicationStatusByEmployer(int applicationId, String status) {

        JobApplication jobApplication = checkJobApplicationExistence(applicationId);
        if (jobApplication.getStatus().equals(JOB_APPLICATION_STATUS.CANCELED.toString())) {
            throw new JobPortalException(HttpStatus.BAD_REQUEST,
                    "This Application is Already Canceled , You can't Change it");
        }

        jobApplication.setStatus(status);
        jobApplicationRepository.save(jobApplication);

        return "Application Status Changed Successfully";
	}

	@Override
	public List<EmployerApplicationsJobsDtoBean> getAllApplicationsByEmployer(int employerId) {
	    return jobApplicationRepository.findAllApplicationForEmployer(employerId).stream()
	        .map((job) -> {
	            String base64Logo = job.getCompanyLogo();

	            // Check if base64Logo is not null or empty
	            if (base64Logo != null && !base64Logo.isEmpty()) {
	                try {
	                    // Check if the Base64 string includes the data URL scheme part
	                    String[] parts = base64Logo.split(",");
	                    
	                    if (parts.length > 1) {
	                        // Return the Base64 data without additional processing
	                        job.setCompanyLogo(base64Logo); // Set the actual Base64 string
	                    } else {
	                        // Handle the case where Base64 string is malformed
	                        job.setCompanyLogo("Invalid Base64 data");
	                    }
	                } catch (IllegalArgumentException e) {
	                    // Handle Base64 decoding errors
	                    e.printStackTrace();
	                    job.setCompanyLogo("Base64 decoding error");
	                }
	            } else {
	                // Handle the case where no logo is available
	                job.setCompanyLogo("No logo available");
	            }

	            return job;
	        })
	        .collect(Collectors.toList());
	}







	

}
