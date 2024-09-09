package com.JobPortalWeb.jobwebapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JobPortalWeb.jobwebapp.Entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
	 Employee findByEmail(String email);
}
