package com.JobPortalWeb.jobwebapp.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="job_application")
@Getter
@Setter
@AllArgsConstructor
public class JobApplication 
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String status;
	private LocalDateTime appliedDate;
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@ManyToOne
    @JoinColumn(name = "job_id" )
	private  Job job;
	
	public JobApplication() {
		
	}
}
