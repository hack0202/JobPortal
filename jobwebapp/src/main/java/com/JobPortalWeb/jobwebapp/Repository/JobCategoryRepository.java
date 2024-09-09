package com.JobPortalWeb.jobwebapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JobPortalWeb.jobwebapp.Entity.JobCategory;

public interface JobCategoryRepository extends JpaRepository<JobCategory, Integer> 
{
	 Optional<JobCategory> findByTitle(String title);

}
