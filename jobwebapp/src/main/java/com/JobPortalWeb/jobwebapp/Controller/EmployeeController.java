package com.JobPortalWeb.jobwebapp.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortalWeb.jobwebapp.BeanDto.EmployeeDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.JobApplicationDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.LoginDto;
import com.JobPortalWeb.jobwebapp.Entity.Employee;
import com.JobPortalWeb.jobwebapp.ExceptionHandler.EmployeeNotFoundException;
import com.JobPortalWeb.jobwebapp.Response.LoginResponse;
import com.JobPortalWeb.jobwebapp.Service.EmployeeServ;
import com.JobPortalWeb.jobwebapp.Service.JobApplicationService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:5173/")
public class EmployeeController {


	@Autowired
	EmployeeServ service;
	
	@Autowired
	 private JobApplicationService jobApplicationService;
	
	
//	@Autowired
//	AuthenticationManager authenticationManager;
	
	@GetMapping("/user")
	public String showMessage() {
		return "hello world";
	}
	
	
	@PostMapping("/save")
	public String saveEmployee(@RequestBody EmployeeDtoBean employeeReq) {
		return service.saveEmp(employeeReq);
	}
//	@PostMapping("/login")
//	public ResponseEntity<?> loginEmployee(@RequestBody LoginDto logindto)
//	{
//		
//		LoginResponse loginresponse = service.loginEmployee(logindto);
//		return ResponseEntity.ok(loginresponse);
//	}

    @GetMapping("/all")
    public ResponseEntity<Page<EmployeeDtoBean>> getAllEmployee(Pageable pageable) {
        Page<EmployeeDtoBean> employees = service.getAllEmployee(pageable);
        return ResponseEntity.ok(employees);
    }
	
	@GetMapping("/{id}")
	public EmployeeDtoBean getEmployeeById(@PathVariable Integer id) {
	    return service.getEmployeeById(id);
	}
	@PostMapping("/updateemployee/{id}")
	public Optional<EmployeeDtoBean> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDtoBean newEmployeeDetails) {
	    Optional<EmployeeDtoBean> resp = service.updateEmployee(id, newEmployeeDetails);

	    if (resp.isEmpty()) {
	        throw new EmployeeNotFoundException(id);
	    }

	    return resp;
	}
//	employeeJobRelated 
//	apply for job  
	   @PostMapping("/{employeeId}/jobs/{jobId}/apply")
	    public ResponseEntity<String> applyForJob(@PathVariable("employeeId") int employeeId,
	                                              @PathVariable("jobId") int jobId) {

	        return new ResponseEntity<>(jobApplicationService.applyJob(employeeId, jobId), HttpStatus.CREATED);

	    }
	   
//get all application of a employee by id
	    @GetMapping("/{employeeId}/jobs/yourApplications")
	    public ResponseEntity<List<JobApplicationDtoBean>> getAllJobApplicationByEmployee(
	            @PathVariable("employeeId") int employeeId) {
	        return ResponseEntity.ok(jobApplicationService.getAllApplicationsByEmployee(employeeId));
	    }
	    
//	    cancel a job application
	    @PostMapping("/{employeeId}/jobs/{jobId}/yourApplications/{applicationId}/cancel")
	    public ResponseEntity<String> cancelJobApplication(@PathVariable("employeeId") int employeeId,
	                                                       @PathVariable("jobId") int jobId,
	                                                       @PathVariable("applicationId") int applicationId

	    ) {

	        return ResponseEntity.ok(jobApplicationService.cancelApplication(employeeId, jobId , applicationId));

	    }

}
