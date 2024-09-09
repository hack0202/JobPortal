package com.JobPortalWeb.jobwebapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortalWeb.jobwebapp.BeanDto.EmployerApplicationsJobsDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.EmployerDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.JobDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.JobResponse;
import com.JobPortalWeb.jobwebapp.BeanDto.LoginDto;
import com.JobPortalWeb.jobwebapp.Entity.Employer;
import com.JobPortalWeb.jobwebapp.Response.LoginResponse;
import com.JobPortalWeb.jobwebapp.Service.EmployerService;
import com.JobPortalWeb.jobwebapp.Service.JobApplicationService;
import com.JobPortalWeb.jobwebapp.Service.JobService;

import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("/employer")
@CrossOrigin("http://localhost:5173/")
public class EmployerController 
{  
	@Autowired
	private EmployerService employerservice; 
	@Autowired
	private JobService jobService;
	
	@Autowired
	private JobApplicationService jobApplicationService;
	
	
	
	@PostMapping("/saveemployer")
	public String saveEmployer(@RequestBody EmployerDtoBean employerdto) 
	{
		return employerservice.saveEmployer(employerdto);
	}
	
	@GetMapping("/getall")
	public List<EmployerDtoBean> getallEmployer(){
		return employerservice.findallEmployer();
	}
	
//	employer Job related
	
	@PostMapping(value = "/{employerId}/jobs", consumes = "application/json")
	public ResponseEntity<String> createJob(@RequestBody JobDtoBean jobDto,
	                                        @PathVariable("employerId") int employerId) {
	    try {
	        String result = jobService.createJob(jobDto, employerId);
	        return new ResponseEntity<>(result, HttpStatus.CREATED);
	    } catch (Exception e) {
	        // Handle specific exceptions as needed or log them
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
    @PostMapping("/{employerId}/myApplications/{applicationId}")
    public ResponseEntity<String> updateJobApplicationsStatus(
            @PathVariable("applicationId") int applicationId,
            @RequestParam("status") String status) {

        return new ResponseEntity<>(jobApplicationService.updateApplicationStatusByEmployer(applicationId, status), HttpStatus.CREATED);

    }
    @GetMapping(value = "/{employerId}/jobs")
    public ResponseEntity<List<JobResponse>> getAllJobsByEmployer(@PathVariable("employerId") int employerId) {
        return ResponseEntity.ok(jobService.findByAllJobsByEmployerId(employerId));
    }
//    
    @GetMapping(value = "/{employerId}/myApplications")
    public ResponseEntity<List<EmployerApplicationsJobsDtoBean>> getAllApplicationByEmployerId(
            @PathVariable("employerId") int employerId
    ) {
        return ResponseEntity.ok(jobApplicationService.getAllApplicationsByEmployer(employerId));
    }



    @DeleteMapping({"/{employerId}/jobs/{jobId}"})
    public ResponseEntity<String> deleteJob(@PathVariable("employerId") int employerId,
                                            @PathVariable("jobId") int jobId) 
    {
        
        return ResponseEntity.ok(jobService.deleteJob(employerId, jobId));
        
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@PostMapping("/login")
//	public ResponseEntity<?> loginEmployer(@RequestBody LoginDto logindto){
//		LoginResponse loginresponse=employerservice.loginEmployer(logindto);
//		return ResponseEntity.ok(loginresponse);
//	}
	

}
