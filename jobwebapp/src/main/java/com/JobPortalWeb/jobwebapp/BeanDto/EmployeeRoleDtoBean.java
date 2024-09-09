package com.JobPortalWeb.jobwebapp.BeanDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRoleDtoBean {
	
	private Integer id;
	private String name;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public EmployeeRoleDtoBean(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public EmployeeRoleDtoBean() {
		
	}
	

}
