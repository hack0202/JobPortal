package com.JobPortalWeb.jobwebapp.Service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.JobPortalWeb.jobwebapp.BeanDto.EmployerDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.LoginDto;
import com.JobPortalWeb.jobwebapp.Entity.Employer;
import com.JobPortalWeb.jobwebapp.Response.LoginResponse;

public interface EmployerService  {
	public String saveEmployer(@RequestBody EmployerDtoBean employerRequest);
	
//	public LoginResponse loginEmployer(LoginDto logindto);

	public List<EmployerDtoBean> findallEmployer();
}
