package com.JobPortalWeb.jobwebapp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter

public class Bookmark 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "employee_id")
	    private Employee employee;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "job_id")
	    private Job job;

	    private boolean isBookmarked;
	    

	    public Bookmark(Employee employee, Job job, boolean isBookmarked) {
	        this.employee = employee;
	        this.job = job;
	        this.isBookmarked = isBookmarked;
	    }
	   
	    public Bookmark() {
	    	
	    }




}
