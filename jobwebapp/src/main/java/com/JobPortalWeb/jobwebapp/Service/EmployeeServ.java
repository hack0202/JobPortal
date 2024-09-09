package com.JobPortalWeb.jobwebapp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import com.JobPortalWeb.jobwebapp.BeanDto.EmployeeDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.LoginDto;
import com.JobPortalWeb.jobwebapp.Entity.Employee;
import com.JobPortalWeb.jobwebapp.Response.LoginResponse;

public interface EmployeeServ {
	
	public String saveEmp(@RequestBody EmployeeDtoBean employeeReq);
	
	Page<EmployeeDtoBean> getAllEmployee(Pageable pageable);
	 public EmployeeDtoBean getEmployeeById(Integer id);
	 public Optional<EmployeeDtoBean> updateEmployee(Integer id, EmployeeDtoBean employeeDtoBean);
	 


}
