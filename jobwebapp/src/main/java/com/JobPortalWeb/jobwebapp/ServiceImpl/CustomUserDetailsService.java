package com.JobPortalWeb.jobwebapp.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.JobPortalWeb.jobwebapp.Entity.Employee;
import com.JobPortalWeb.jobwebapp.Entity.Employer;
import com.JobPortalWeb.jobwebapp.Repository.EmployeeRepo;
import com.JobPortalWeb.jobwebapp.Repository.EmployerRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private EmployeeRepo repo;
	
	@Autowired
	private EmployerRepo employerRepo;
	
//	   private final EmployeeRepo employeeRepo;

	    public CustomUserDetailsService(EmployeeRepo employeeRepo ,EmployerRepo employerRepo) {
	        this.repo = employeeRepo;
	        this.employerRepo=employerRepo;
	    }

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee employee= repo.findByEmail(email);
		if(employee!=null) {

			return employee;
		}
		   Employer employer = employerRepo.findByEmail(email);
	        if (employer != null) {
	            return employer;
	        }
	        throw new UsernameNotFoundException("User not found with email: " + email);
		  
	}

}
