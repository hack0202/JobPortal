package com.JobPortalWeb.jobwebapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JobPortalWeb.jobwebapp.Entity.EmployeeProjects;

public interface EmployeeProjectsRepo extends JpaRepository<EmployeeProjects, Integer> {

}
