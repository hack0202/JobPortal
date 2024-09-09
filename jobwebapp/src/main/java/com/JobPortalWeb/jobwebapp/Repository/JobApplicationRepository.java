package com.JobPortalWeb.jobwebapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.JobPortalWeb.jobwebapp.BeanDto.EmployerApplicationsJobsDtoBean;
import com.JobPortalWeb.jobwebapp.Entity.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {
	
	  JobApplication findByEmployeeIdAndJobId(int employeeId, int jobId);

	@Query("from JobApplication j where j.employee.id = :employeeId")
	List<JobApplication> findAllApplicationByEmployeeId(@Param("employeeId") int employeeId);

	
	@Query(
	        "SELECT NEW com.JobPortalWeb.jobwebapp.BeanDto.EmployerApplicationsJobsDtoBean(" +
	                "j.companyName, " +
	                "j.logoPath, " +  // Assumed to be equivalent to CompanyLogo
	                "j.title, " +
	                "j.jobCategory.title, " +
	                "j.jobType, " +
	                "e.firstName, " +  // Assumed to be equivalent to employeeName
	                "j.country, " +
	                "ja.id, " +
	                "ja.status) " +
	                
	        "FROM Job j " +
	        "JOIN JobApplication ja ON ja.job.id = j.id " +
	        "JOIN j.employer e " +  // Added this join to fetch employer details
	        "JOIN ja.employee emp " +
	        "WHERE j.employer.id = :employerId"
	)
	List<EmployerApplicationsJobsDtoBean> findAllApplicationForEmployer(@Param("employerId") int employerId);
	
	@Query(
		    "SELECT NEW com.JobPortalWeb.jobwebapp.BeanDto.EmployerApplicationsJobsDtoBean(" +
		            "j.companyName, j.logoPath, j.title, j.jobCategory.title, " +
		            "j.jobType, ja.employee.firstName, j.country, ja.id, ja.status) " +
		    "FROM Job j " +
		    "JOIN JobApplication ja ON ja.job.id = j.id"
		)
		List<EmployerApplicationsJobsDtoBean> findAllApplication();


}
	

