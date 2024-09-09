package com.JobPortalWeb.jobwebapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JobPortalWeb.jobwebapp.Entity.EmployeeProfileInfo;

public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfileInfo, Integer> {

}
