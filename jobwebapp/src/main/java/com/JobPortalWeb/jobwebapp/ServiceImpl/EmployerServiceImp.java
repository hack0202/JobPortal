package com.JobPortalWeb.jobwebapp.ServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JobPortalWeb.jobwebapp.BeanDto.EmployeeDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.EmployerDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.EmployerRoleDtoBean;
import com.JobPortalWeb.jobwebapp.BeanDto.LoginDto;
import com.JobPortalWeb.jobwebapp.Entity.Employee;
import com.JobPortalWeb.jobwebapp.Entity.Employer;
import com.JobPortalWeb.jobwebapp.Entity.EmployerRole;
import com.JobPortalWeb.jobwebapp.Repository.EmployerRepo;
import com.JobPortalWeb.jobwebapp.Response.LoginResponse;
import com.JobPortalWeb.jobwebapp.Service.EmployerService;


@Service
public class EmployerServiceImp  implements EmployerService{

	
	@Autowired
	private EmployerRepo employerrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String saveEmployer(EmployerDtoBean employerRequest) {
		Employer employer = new Employer();
		employer.setFirstName(employerRequest.getFirstName());
		employer.setLastName(employerRequest.getLastName());
		employer.setEmail(employerRequest.getEmail());
		employer.setPassword(passwordEncoder.encode(employerRequest.getPassword()));
		employer.setContactNum(employerRequest.getContactNum());
		employer.setCountry(employerRequest.getCountry());
		employer.setState(employerRequest.getState());
		employer.setCity(employerRequest.getCity());
		employer.setPinCode(employerRequest.getPinCode());
		employer.setStreet(employerRequest.getStreet());
		
		 Set<EmployerRole> roles = new HashSet<>();
		    EmployerRole adminRole = new EmployerRole();
		    adminRole.setName("Admin");  // Set the default role name as Admin
		    adminRole.setEmployer(employer);  // Set the association
		    roles.add(adminRole);
		    
		    employer.setRoles(roles);
		
//		Set<EmployerRole> roles = new HashSet<EmployerRole>();
//        if (employerRequest.getRoles() != null) {
//            for (EmployerRoleDtoBean roleDto : employerRequest.getRoles()) {
//                EmployerRole role = new EmployerRole();
//                role.setName(roleDto.getName());
//                role.setEmployer(employer); // Set the association
//                roles.add(role);
//            } 
//           
//        }
//        employer.setRoles(roles);  
		
		employerrepo.save(employer);
		
		
		return "Employer saved successfully";
	}

	@Override
	public List<EmployerDtoBean> findallEmployer() {
		List<Employer> employeer = employerrepo.findAll();
		
		return employeer.stream()
                .map((employer ->mapToDto(employer)))
                .collect(Collectors.toList());
    }
	

	
//	helper method 
	 private EmployerDtoBean mapToDto(Employer employer) 
	 {
	        return modelMapper.map(employer, EmployerDtoBean.class);
	    }


//	@Override
//	public LoginResponse loginEmployer(LoginDto logindto) {
//		String msg="";
//		Employer employer= employerrepo.findByEmail(logindto.getEmail());
//		if(employer !=null) {
//			String password= logindto.getPassword();
//			String EncodedPassword= employer.getPassword();
//			boolean isPasswordCorrect=passwordEncoder.matches(password, EncodedPassword);
//			if(isPasswordCorrect) {
//				Optional<Employer> empl = employerrepo.findOneByEmailAndPassword(logindto.getEmail(),EncodedPassword);
//				if(empl.isPresent()) {
//					return new LoginResponse("Employer login successfull", true);
//				}else {
//					return new LoginResponse("login failed",false);
//				}
//			}else {
//				return new LoginResponse("Invalid password ",false);
//			}
//			
//		}
//		else {
//			return new LoginResponse("Email does not Exists",false);
//		}
//	}

	
}
