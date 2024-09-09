package com.JobPortalWeb.jobwebapp.Response;

public class LoginResponse  {
	public String message;
	public Boolean status;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
//	args constructor
	public LoginResponse(String message, Boolean status) {
		super();
		this.message = message;
		this.status = status;
	}
//	non args constructor
	public LoginResponse() {
		
	}
	@Override
	public String toString() {
		return "LoginResponse [message=" + message + ", status=" + status + "]";
	}
	
}
