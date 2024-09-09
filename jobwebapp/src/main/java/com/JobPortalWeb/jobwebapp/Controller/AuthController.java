package com.JobPortalWeb.jobwebapp.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortalWeb.jobwebapp.Entity.Employee;
import com.JobPortalWeb.jobwebapp.Entity.Employer;
import com.JobPortalWeb.jobwebapp.Security.JwtAuthRequest;
import com.JobPortalWeb.jobwebapp.Security.JwtAuthResponse;
import com.JobPortalWeb.jobwebapp.Security.JwtTokenHelper;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/auth")
public class AuthController 
{
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

	 
	
	
	 @PostMapping("/login")
	    public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest request) {

	        this.doAuthenticate(request.getEmail(), request.getPassword());


	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	        
	        Integer id = null;
	        String firstName = null;
	        String lastName = null;
	        List<String> roles = null;

	        if (userDetails instanceof Employee) {
	            Employee employee = (Employee) userDetails;
	            id = employee.getId();
	            firstName = employee.getFirstName();
	            lastName = employee.getLastName();
	            roles = employee.getEmployeerole().stream()
	                    .map(role -> role.getName())
	                    .collect(Collectors.toList());
	            
	        } else if (userDetails instanceof Employer) {
	            Employer employer = (Employer) userDetails;
	            id = employer.getId();
	            firstName = employer.getFirstName();
	            lastName = employer.getLastName();
	            roles = employer.getRoles().stream()
	                    .map(role -> role.getName())
	                    .collect(Collectors.toList());
	        }
	        String token = jwtTokenHelper.generateToken(userDetails);

	        JwtAuthResponse response = new JwtAuthResponse(
	                token, 
	                userDetails.getUsername(),  // Username (which is the email)
	                userDetails.getUsername(),  // Email
	                firstName, 
	                lastName, 
	                id,
	                roles 
	            );
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
//	        
	        

	 private void doAuthenticate(String email, String password) {

	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
	        try 
	        {
	        	
	        	authenticationManager.authenticate(authentication);


	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException(" Invalid Username or Password  !!");
	        }

	    }
	  @ExceptionHandler(BadCredentialsException.class)
	    public String exceptionHandler() {
	        return "Credentials Invalid !!";
	    }


}
