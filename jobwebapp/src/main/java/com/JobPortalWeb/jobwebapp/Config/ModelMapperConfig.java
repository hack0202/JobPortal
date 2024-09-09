package com.JobPortalWeb.jobwebapp.Config;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.JobPortalWeb.jobwebapp.BeanDto.EmployeeDtoBean;
import com.JobPortalWeb.jobwebapp.Entity.Employee;

@Configuration
public class ModelMapperConfig {
	@Bean
    public ModelMapper CreateBean() {
        return new ModelMapper();
    }
	
	@Bean
	public ModelMapper modelMapper() {
	    ModelMapper modelMapper = new ModelMapper();

	    modelMapper.typeMap(Employee.class, EmployeeDtoBean.class).addMappings(mapper -> {
	        // Mapping for EmployeeProfileInfo
	     
	        mapper.map(src -> src.getEmployeeProfileInfo(),EmployeeDtoBean::setEmployeeProfileDtoBean);

	        // Mapping for other lists (WorkExperience, Skills, Roles)
	        mapper.map(src -> src.getEmployeeProjects(), EmployeeDtoBean::setEmployeeProjectsDtoBeans);
	        mapper.map(src -> src.getWorkExperiences(), EmployeeDtoBean::setWorkExperienceDtoBeans);
	        mapper.map(src -> src.getEmployeeskill(), EmployeeDtoBean::setEmployeeSkillDtoBeans);
	        mapper.map(src -> src.getEmployeerole(), EmployeeDtoBean::setEmployeeRoleDtoBeans);
	        // Add similar mappings for other collections if needed
	    });
	    return modelMapper;
}
}