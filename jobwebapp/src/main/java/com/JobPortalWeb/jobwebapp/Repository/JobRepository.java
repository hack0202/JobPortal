package com.JobPortalWeb.jobwebapp.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.JobPortalWeb.jobwebapp.BeanDto.JobResponse;
import com.JobPortalWeb.jobwebapp.Entity.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {
	
	List<Job> findByEmployerId(int employerId);
	
	@Query("SELECT NEW com.JobPortalWeb.jobwebapp.BeanDto.JobResponse(j.id, j.title, j.companyName, j.jobDescription, j.skills, " +
	        "j.jobType, j.salaryRange, j.experience, j.street, j.city, j.pinCode, j.country, " +
	        "j.logoPath, " +
	        "new com.JobPortalWeb.jobwebapp.BeanDto.JobCategoryDtoBean(j.jobCategory.title, j.jobCategory.description), " +
	        "j.employer.id) " +
	        "FROM Job j")
	List<JobResponse> findByAllJobs();
	

    @Query(
        "SELECT NEW com.JobPortalWeb.jobwebapp.BeanDto.JobResponse(" +
                "j.id, j.title, j.companyName, j.jobDescription, j.skills, " +
                "j.jobType, j.salaryRange, j.experience, j.street, j.city, " +
                "j.pinCode, j.country, j.logoPath, " +
                "NEW com.JobPortalWeb.jobwebapp.BeanDto.JobCategoryDtoBean(j.jobCategory.title, j.jobCategory.description), " +
                "j.employer.id) " +
        "FROM Job j " +
        "WHERE (:jobCategory IS NULL OR j.jobCategory.title LIKE CONCAT('%', :jobCategory, '%')) " +
        "AND (:jobType IS NULL OR j.jobType LIKE CONCAT('%', :jobType, '%')) " +
        "AND (:country IS NULL OR j.country LIKE CONCAT('%', :country, '%')) " +
        "AND (:skills IS NULL OR j.skills LIKE CONCAT('%', :skills, '%')) " +
        "AND (:title IS NULL OR j.title LIKE CONCAT('%', :title, '%')) " +
        "AND (:salaryRange IS NULL OR j.salaryRange LIKE CONCAT('%', :salaryRange, '%')) " +
        "AND (:experience IS NULL OR j.experience LIKE CONCAT('%', :experience, '%')) " +
        "AND (:companyName IS NULL OR j.companyName LIKE CONCAT('%', :companyName, '%')) " +
        "AND (:city IS NULL OR j.city LIKE CONCAT('%', :city, '%'))"
    )
    Page<JobResponse> searchJobs(@Param("jobCategory") String jobCategory,
                                 @Param("jobType") String jobType,
                                 @Param("country") String country,
                                 @Param("skills") String skills,
                                 @Param("title") String title,
                                 @Param("salaryRange") String salaryRange,
                                 @Param("experience") String experience,
                                 @Param("companyName") String companyName,
                                 @Param("city") String city,
                                 Pageable pageable);
}






	

