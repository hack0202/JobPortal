package com.JobPortalWeb.jobwebapp.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmployeeNotFoundAdvice {
	
@ResponseBody	
@ExceptionHandler(EmployeeNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)

public Map<String, String> exceptionHandler(EmployeeNotFoundException exception)
{
	Map<String, String> errorMap=new HashMap<>();
	errorMap.put("errorMessage", exception.getMessage());
	return errorMap;
	
}


}
