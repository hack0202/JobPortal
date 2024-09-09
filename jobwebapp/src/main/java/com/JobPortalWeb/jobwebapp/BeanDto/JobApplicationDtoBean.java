package com.JobPortalWeb.jobwebapp.BeanDto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
public class JobApplicationDtoBean {

	private JobResponse jobResponse;
	private int applicationId; // Fixed the typo
	private String status;
	private LocalDateTime appliedDate;
	
	
	public JobApplicationDtoBean() {
		
	}
}
