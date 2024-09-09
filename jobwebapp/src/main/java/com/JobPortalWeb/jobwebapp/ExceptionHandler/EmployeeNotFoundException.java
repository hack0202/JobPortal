package com.JobPortalWeb.jobwebapp.ExceptionHandler;

public class EmployeeNotFoundException  extends RuntimeException{

	public EmployeeNotFoundException(Integer id) {
		super("could not find the employee with id : " +id);
	}
}
