package com.JobPortalWeb.jobwebapp.BeanDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeSkillDtoBean {
	
	private Integer id;
	private String name;
	private String experience;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	

	public EmployeeSkillDtoBean() {
		
	}

}
