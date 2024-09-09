package com.JobPortalWeb.jobwebapp.BeanDto;

public class LoginDto 
{
	public String email;
	public String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public LoginDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public LoginDto() {
		
	}
	
	@Override
	public String toString() {
		return "LoginDto [email=" + email + ", password=" + password + "]";
	}

}
