package com.JobPortalWeb.jobwebapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortalWeb.jobwebapp.BeanDto.EmployerApplicationsJobsDtoBean;
import com.JobPortalWeb.jobwebapp.Service.JobApplicationService;

@RestController
@RequestMapping("/jobapplication")
@CrossOrigin("http://localhost:5173/")
public class JobApplicationController 
{
	@Autowired
	private JobApplicationService jobApplicationService;
	
	   @GetMapping
	    public ResponseEntity<List<EmployerApplicationsJobsDtoBean>> getAllJobApplications() {

	        return ResponseEntity.ok(jobApplicationService.getAllApplications());
	    }

}
