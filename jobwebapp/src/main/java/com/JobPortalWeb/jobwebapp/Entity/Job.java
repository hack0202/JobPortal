package com.JobPortalWeb.jobwebapp.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Table(name="job")
@Data

@AllArgsConstructor

public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String companyName;
	private String jobDescription;
	private String skills;
	private String jobType;
	private String salaryRange;
	private String experience;
	private String country;
	private String city;
	private String street;
	private String pinCode;
	
	@Lob
	@Column(columnDefinition = "LONGTEXT")
	private String logoPath;
//	ALTER TABLE job MODIFY logo_path LONGTEXT;
	
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinColumn(name="jobcategory_id")
	private JobCategory jobCategory;
	
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinColumn(name = "employer_id")
	private Employer employer;
	
	
	public Job() {
		
	}
}
