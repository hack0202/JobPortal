package com.JobPortalWeb.jobwebapp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JobPortalWeb.jobwebapp.Entity.Bookmark;
import com.JobPortalWeb.jobwebapp.Entity.Employee;
import com.JobPortalWeb.jobwebapp.Entity.Job;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
	  Optional<Bookmark> findByEmployeeAndJob(Employee employee, Job job);
	   List<Bookmark> findByEmployee(Employee employee);

}
