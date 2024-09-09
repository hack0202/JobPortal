package com.JobPortalWeb.jobwebapp.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.JobPortalWeb.jobwebapp.BeanDto.EmployeeDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.EmployeeProfileDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.EmployeeProjectsDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.EmployeeRoleDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.EmployeeSkillDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.LoginDto;
import com.JobPortalWeb.jobwebapp.BeanDto.QualificationDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.WorkExperienceDtoBean;
import com.JobPortalWeb.jobwebapp.Entity.Employee;
import com.JobPortalWeb.jobwebapp.Entity.EmployeeProfileInfo;
import com.JobPortalWeb.jobwebapp.Entity.EmployeeProjects;
import com.JobPortalWeb.jobwebapp.Entity.EmployeeRole;
import com.JobPortalWeb.jobwebapp.Entity.EmployeeSkill;
import com.JobPortalWeb.jobwebapp.Entity.Qualification;
import com.JobPortalWeb.jobwebapp.Entity.WorkExperience;
import com.JobPortalWeb.jobwebapp.ExceptionHandler.EmployeeNotFoundException;
import com.JobPortalWeb.jobwebapp.Repository.EmployeeRepo;
import com.JobPortalWeb.jobwebapp.Response.LoginResponse;
import com.JobPortalWeb.jobwebapp.Service.EmployeeServ;

import io.jsonwebtoken.lang.Collections;
@Service
public class EmployeeServImpl implements EmployeeServ {
	@Autowired
	EmployeeRepo repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;
	

@Override
	public String saveEmp(EmployeeDtoBean employeeReq) 
	{
	

		Employee emp = new Employee();
		emp.setFirstName(employeeReq.getFirstName());
		emp.setLastName(employeeReq.getLastName());
		emp.setEmail(employeeReq.getEmail());
		emp.setPassword(passwordEncoder.encode(employeeReq.getPassword()));
		emp.setContactNum(employeeReq.getContactNum());
		emp.setCountry(employeeReq.getCountry());
		emp.setState(employeeReq.getState());
		emp.setCity(employeeReq.getCity());
		emp.setPinCode(employeeReq.getPinCode());
		emp.setRegistrationDate(new Date());
		emp.setStreet(employeeReq.getStreet());
		
		emp.setQualification(new ArrayList<>());
		emp.setWorkExperiences(new ArrayList<>());
		emp.setEmployeeskill(new ArrayList<>());
		emp.setEmployeerole(new HashSet<>());
		emp.setEmployeeProjects(new ArrayList<>());
		
		
	    EmployeeRole defaultRole = new EmployeeRole();
	    defaultRole.setName("USER");
	    defaultRole.setEmployee(emp);
	    
	    Set<EmployeeRole> roles = new HashSet<>();
	    roles.add(defaultRole);
	    emp.setEmployeerole(roles);
		
		
		// Create and add Qualifications
		    for(QualificationDtoBean qualDto: employeeReq.getQualification()) {
		        Qualification qual = new Qualification();
		        qual.setDegree(qualDto.getDegree());
		        qual.setEmployee(emp);
		        qual.setStartdate(qualDto.getStartdate());
		        qual.setEnddate(qualDto.getEnddate());
		        emp.getQualification().add(qual);
		    }
		  
	// Add Work Experiences
		  
		    for(WorkExperienceDtoBean workExpDto : employeeReq.getWorkExperienceDtoBeans()) {
		        WorkExperience workexp = new WorkExperience();
		        workexp.setCompany(workExpDto.getCompany());
		        workexp.setEmployee(emp);
		        workexp.setPosition(workExpDto.getPosition());
		        workexp.setStartdate(workExpDto.getStartdate());
		        workexp.setEnddate(workExpDto.getEnddate());
		        emp.getWorkExperiences().add(workexp);
		    }
		
//		add Skills
		
		    for(EmployeeSkillDtoBean skillDto : employeeReq.getEmployeeSkillDtoBeans()) {
		        EmployeeSkill skill = new EmployeeSkill();
		        skill.setName(skillDto.getName());
		        skill.setExperience(skillDto.getExperience());
		        skill.setEmployee(emp);
		        emp.getEmployeeskill().add(skill);
		    }
		
//		setting employee role  
		
//		    for(EmployeeRoleDtoBean roleDto : employeeReq.getEmployeeRoleDtoBeans()) {
//		        EmployeeRole role = new EmployeeRole();
//		        role.setName(roleDto.getName());
//		        role.setEmployee(emp);
//		        emp.getEmployeerole().add(role);
//		    }
		
//		setting Employee Projects
		
		    for(EmployeeProjectsDtoBean projdto: employeeReq.getEmployeeProjectsDtoBeans()) {
		        EmployeeProjects proj = new EmployeeProjects();
		        proj.setEmployee(emp);
		        proj.setProjectName(projdto.getProjectName());
		        proj.setDescription(projdto.getDescription());
		        emp.getEmployeeProjects().add(proj);
		    }
		
		
//		setting profileInfo
		
		    EmployeeProfileInfo profile = new EmployeeProfileInfo();
		    profile.setBio(employeeReq.getEmployeeProfileDtoBean().getBio());
		    profile.setEmployee(emp);
		    profile.setGithub(employeeReq.getEmployeeProfileDtoBean().getGithub());
		    profile.setLinkedin(employeeReq.getEmployeeProfileDtoBean().getLinkedin());
		    profile.setPhotopath(employeeReq.getEmployeeProfileDtoBean().getPhotopath());
		    profile.setResumepath(employeeReq.getEmployeeProfileDtoBean().getResumepath());
		    profile.setWebsite(employeeReq.getEmployeeProfileDtoBean().getWebsite());
		    emp.setEmployeeProfileInfo(profile);
		    
		    // Save employee entity
		    repo.save(emp);
		    return "Data saved";
	}


	

@Override
public Page<EmployeeDtoBean> getAllEmployee(Pageable pageable) {
    Page<Employee> employeePage = repo.findAll(pageable);
    List<EmployeeDtoBean> employeeDtoBeans = employeePage.stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    return new PageImpl<>(employeeDtoBeans, pageable, employeePage.getTotalElements());
}
	
	
	@Override
	public EmployeeDtoBean getEmployeeById(Integer id) {
		
		 Employee employee = repo.findById(id)
	                .orElseThrow(() -> new EmployeeNotFoundException(id));
	        return mapToDto(employee);
	}

	
	@Override
	public Optional<EmployeeDtoBean> updateEmployee(Integer id, EmployeeDtoBean employeeDtoBean) {
	    if (employeeDtoBean == null) {
	        throw new IllegalArgumentException("Employee DTO cannot be null");
	    }
	    if (employeeDtoBean.getId() == null) {
	        throw new IllegalArgumentException("Employee ID in DTO cannot be null");
	    }
	    if (!id.equals(employeeDtoBean.getId())) {
	        throw new IllegalArgumentException("ID mismatch: expected " + id + " but found " + employeeDtoBean.getId());
	    }

	    return repo.findById(id).map(existingEmployee -> {
	        // Update basic fields from DTO
	        existingEmployee.setFirstName(employeeDtoBean.getFirstName());
	        existingEmployee.setLastName(employeeDtoBean.getLastName());
	        existingEmployee.setEmail(employeeDtoBean.getEmail());
	        existingEmployee.setContactNum(employeeDtoBean.getContactNum());
	        existingEmployee.setCountry(employeeDtoBean.getCountry());
	        existingEmployee.setState(employeeDtoBean.getState());
	        existingEmployee.setCity(employeeDtoBean.getCity());
	        existingEmployee.setPinCode(employeeDtoBean.getPinCode());
	        existingEmployee.setStreet(employeeDtoBean.getStreet());

	        if (employeeDtoBean.getPassword() != null && !employeeDtoBean.getPassword().isEmpty()) {
	            existingEmployee.setPassword(passwordEncoder.encode(employeeDtoBean.getPassword()));
	        }

// Update qualifications
	        updateQualifications(existingEmployee, employeeDtoBean.getQualification());
//	    Update updateWorkExperiences 
	        updateWorkExperiences(existingEmployee,employeeDtoBean.getWorkExperienceDtoBeans());
//		    Update updateEmployeeSkills 
	        updateEmployeeSkills(existingEmployee,employeeDtoBean.getEmployeeSkillDtoBeans());
//	        Update updateEmployeeProjects 
	        updateEmployeeProjects(existingEmployee,employeeDtoBean.getEmployeeProjectsDtoBeans());
//	        Update updateEmployeeProfileInfo 
	        updateEmployeeProfileInfo(existingEmployee,employeeDtoBean.getEmployeeProfileDtoBean());
	        updateEmployeeRole(existingEmployee,employeeDtoBean.getEmployeeRoleDtoBeans());
	      
	        // Save and return updated employee
	        Employee updatedEmployee = repo.save(existingEmployee);
	        return Optional.of(mapToDto(updatedEmployee));
	    }).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	private void updateQualifications(Employee existingEmployee, List<QualificationDtoBean> qualifications) {
	    if (qualifications == null) {
	        return;
	    }

	    // Create a map for quick lookup of existing qualifications
	    Map<Integer, Qualification> existingQualificationsMap = existingEmployee.getQualification().stream()
	            .collect(Collectors.toMap(Qualification::getId, q -> q));

	    // Process provided qualifications
	    for (QualificationDtoBean dto : qualifications) {
	        Qualification qualification = existingQualificationsMap.get(dto.getId());

	        if (qualification != null) {
	            // Update existing qualification
	            qualification.setDegree(dto.getDegree());
	            qualification.setStartdate(dto.getStartdate());
	            qualification.setEnddate(dto.getEnddate());
	        }
	    }

	    // Remove qualifications not present in the provided data
	    existingEmployee.getQualification().removeIf(q -> !qualifications.stream()
	            .anyMatch(dto -> dto.getId() != null && dto.getId().equals(q.getId())));
	}
	
	private void updateWorkExperiences(Employee existingEmployee, List<WorkExperienceDtoBean> workExperiences) {
	    if (workExperiences == null) {
	        return;
	    }

	    // Create a map for quick lookup of existing work experiences
	    Map<Integer, WorkExperience> existingWorkExperiencesMap = existingEmployee.getWorkExperiences().stream()
	            .collect(Collectors.toMap(WorkExperience::getId, w -> w));

	    // Process provided work experiences
	    for (WorkExperienceDtoBean dto : workExperiences) {
	        WorkExperience workExperience = existingWorkExperiencesMap.get(dto.getId());

	        if (workExperience != null) {
	            // Update existing work experience
	            workExperience.setCompany(dto.getCompany());
	            workExperience.setPosition(dto.getPosition());
	            workExperience.setStartdate(dto.getStartdate());
	            workExperience.setEnddate(dto.getEnddate());
	        } else {
	            // Add new work experience if not present in the existing map
	            WorkExperience newWorkExperience = new WorkExperience();
	            newWorkExperience.setCompany(dto.getCompany());
	            newWorkExperience.setPosition(dto.getPosition());
	            newWorkExperience.setStartdate(dto.getStartdate());
	            newWorkExperience.setEnddate(dto.getEnddate());
	            newWorkExperience.setEmployee(existingEmployee);
//	            existingEmployee.getWorkExperiences().add(newWorkExperience);
	        }
	    }

	    // Remove work experiences not present in the provided data
	    existingEmployee.getWorkExperiences().removeIf(w -> !workExperiences.stream()
	            .anyMatch(dto -> dto.getId() != null && dto.getId().equals(w.getId())));
	}
	
	private void updateEmployeeSkills(Employee existingEmployee, List<EmployeeSkillDtoBean> employeeSkills) {
	    if (employeeSkills == null) {
	        return;
	    }

	    // Create a map for quick lookup of existing employee skills
	    Map<Integer, EmployeeSkill> existingEmployeeSkillsMap = existingEmployee.getEmployeeskill().stream()
	            .collect(Collectors.toMap(EmployeeSkill::getId, e -> e));

	    // Process provided employee skills
	    for (EmployeeSkillDtoBean dto : employeeSkills) {
	        EmployeeSkill employeeSkill = existingEmployeeSkillsMap.get(dto.getId());

	        if (employeeSkill != null) {
	            // Update existing employee skill
	            employeeSkill.setName(dto.getName());
	            employeeSkill.setExperience(dto.getExperience());
	        } else {
	            // Add new employee skill if not present in the existing map
	            EmployeeSkill newEmployeeSkill = new EmployeeSkill();
	            newEmployeeSkill.setName(dto.getName());
	            newEmployeeSkill.setExperience(dto.getExperience());
	            newEmployeeSkill.setEmployee(existingEmployee);
	            existingEmployee.getEmployeeskill().add(newEmployeeSkill);
	        }
	    }

	    // Remove employee skills not present in the provided data
	    existingEmployee.getEmployeeskill().removeIf(e -> !employeeSkills.stream()
	            .anyMatch(dto -> dto.getId() != null && dto.getId().equals(e.getId())));
	}
	
	
	
	private void updateEmployeeProjects(Employee existingEmployee, List<EmployeeProjectsDtoBean> employeeProjects) {
	    if (employeeProjects == null || existingEmployee == null) {
	        return;
	    }

	    // Create a map for quick lookup of existing employee projects by their ID
	    Map<Integer, EmployeeProjects> existingProjectsMap = existingEmployee.getEmployeeProjects().stream()
	            .collect(Collectors.toMap(EmployeeProjects::getId, project -> project));

	    // Process provided employee projects
	    for (EmployeeProjectsDtoBean projectDto : employeeProjects) {
	        EmployeeProjects existingProject = existingProjectsMap.get(projectDto.getId());

	        if (existingProject != null) {
	            // Update existing employee project
	            existingProject.setProjectName(projectDto.getProjectName());
	            existingProject.setDescription(projectDto.getDescription());
	        } else {
	            // Add new employee project if not present in the existing map
	            EmployeeProjects newProject = new EmployeeProjects();
	            newProject.setProjectName(projectDto.getProjectName());
	            newProject.setDescription(projectDto.getDescription());
	            newProject.setEmployee(existingEmployee);
	            existingEmployee.getEmployeeProjects().add(newProject);
	        }
	    }

	    // Remove employee projects not present in the provided data
	    existingEmployee.getEmployeeProjects().removeIf(project -> !employeeProjects.stream()
	            .anyMatch(projectDto -> projectDto.getId() != null && projectDto.getId().equals(project.getId())));
	}
	
	private void updateEmployeeProfileInfo(Employee existingEmployee, EmployeeProfileDtoBean employeeProfileDto) {
	    if (employeeProfileDto == null || existingEmployee == null) {
	        return;
	    }

	    // Check if employeeProfileInfo exists for the employee
	    EmployeeProfileInfo existingProfileInfo = existingEmployee.getEmployeeProfileInfo();
	    if (existingProfileInfo != null) {
	        // Update existing profile info
	        existingProfileInfo.setPhotopath(employeeProfileDto.getPhotopath());
	        existingProfileInfo.setGithub(employeeProfileDto.getGithub());
	        existingProfileInfo.setLinkedin(employeeProfileDto.getLinkedin());
	        existingProfileInfo.setBio(employeeProfileDto.getBio());
	        existingProfileInfo.setWebsite(employeeProfileDto.getWebsite());
	        existingProfileInfo.setResumepath(employeeProfileDto.getResumepath());
	    } else {
	        // Create new profile info if it does not exist
	        EmployeeProfileInfo newProfileInfo = new EmployeeProfileInfo();
	        newProfileInfo.setPhotopath(employeeProfileDto.getPhotopath());
	        newProfileInfo.setGithub(employeeProfileDto.getGithub());
	        newProfileInfo.setLinkedin(employeeProfileDto.getLinkedin());
	        newProfileInfo.setBio(employeeProfileDto.getBio());
	        newProfileInfo.setWebsite(employeeProfileDto.getWebsite());
	        newProfileInfo.setResumepath(employeeProfileDto.getResumepath());
	        newProfileInfo.setEmployee(existingEmployee);
	        existingEmployee.setEmployeeProfileInfo(newProfileInfo);
	    }
	}


	private void updateEmployeeRole(Employee existingEmployee, Set<EmployeeRoleDtoBean> employeeRoleDto) {
	    if (employeeRoleDto == null) {
	        return;
	    }

	    // Create a map for quick lookup of existing employee roles by their ID
	    Map<Integer, EmployeeRole> existingRolesMap = existingEmployee.getEmployeerole().stream()
	            .collect(Collectors.toMap(EmployeeRole::getId, role -> role));

	    // Process provided employee roles
	    for (EmployeeRoleDtoBean dto : employeeRoleDto) {
	        EmployeeRole existingRole = existingRolesMap.get(dto.getId());

	        if (existingRole != null) {
	            // Update existing employee role
	            existingRole.setName(dto.getName());
	        } else {
	            // Add new employee role if not present in the existing map
	            EmployeeRole newRole = new EmployeeRole();
	            newRole.setName(dto.getName());
	            newRole.setEmployee(existingEmployee);
	            existingEmployee.getEmployeerole().add(newRole);
	        }
	    }

	    // Remove employee roles not present in the provided data
	    existingEmployee.getEmployeerole().removeIf(role -> !employeeRoleDto.stream()
	            .anyMatch(dto -> dto.getId() != null && dto.getId().equals(role.getId())));
	}




	
	
	
//	helper method 
	 private EmployeeDtoBean mapToDto(Employee employee) 
	 {
	        return modelMapper.map(employee, EmployeeDtoBean.class);
	    }




	 
	 
	 
	 




	




}
