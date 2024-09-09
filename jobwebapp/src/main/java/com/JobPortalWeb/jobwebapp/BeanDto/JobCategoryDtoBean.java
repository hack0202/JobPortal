package com.JobPortalWeb.jobwebapp.BeanDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JobCategoryDtoBean {

	private String title;
	private String description;
	
	public JobCategoryDtoBean() {
		
	}
}
