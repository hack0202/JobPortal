package com.JobPortalWeb.jobwebapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JobPortalWeb.jobwebapp.Entity.Employer;

public interface EmployerRepo extends JpaRepository<Employer, Integer>{
	  Employer findByEmail(String email);
//	
//	Optional<Employer> findOneByEmailAndPassword(String email, String password);
//	Employer findByEmail(String email);
}
