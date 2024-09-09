package com.JobPortalWeb.jobwebapp.BeanDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.JobPortalWeb.jobwebapp.Entity.Qualification;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class EmployeeDtoBean {
	 public Integer id;
		public String firstName;
		public String lastName;
		
		public String email;
		public String password;
		public String contactNum;
		public String country;
		public String state;
		public String city;
		public String pinCode;
		public String street;
		public Date registrationDate;
		
		public List<QualificationDtoBean> qualification = new ArrayList<>();
		
		private List<WorkExperienceDtoBean> workExperienceDtoBeans = new ArrayList<>();
		
		public List<EmployeeSkillDtoBean> employeeSkillDtoBeans= new ArrayList<>();
		
		public Set<EmployeeRoleDtoBean> employeeRoleDtoBeans = new  HashSet<EmployeeRoleDtoBean>();
		
		public List<EmployeeProjectsDtoBean> employeeProjectsDtoBeans = new ArrayList<>();
		public EmployeeProfileDtoBean employeeProfileDtoBean = new EmployeeProfileDtoBean();
		
		public List<EmployeeProjectsDtoBean> getEmployeeProjectsDtoBeans() {
			return employeeProjectsDtoBeans;
		}
		public void setEmployeeProjectsDtoBeans(List<EmployeeProjectsDtoBean> employeeProjectsDtoBeans) {
			this.employeeProjectsDtoBeans = employeeProjectsDtoBeans;
		}
		
		
		
	
		
		
		
		public List<WorkExperienceDtoBean> getWorkExperienceDtoBeans() {
			return workExperienceDtoBeans;
		}
		public void setWorkExperienceDtoBeans(List<WorkExperienceDtoBean> workExperienceDtoBeans) {
			this.workExperienceDtoBeans = workExperienceDtoBeans;
		}
		public List<EmployeeSkillDtoBean> getEmployeeSkillDtoBeans() {
			return employeeSkillDtoBeans;
		}
		public void setEmployeeSkillDtoBeans(List<EmployeeSkillDtoBean> employeeSkillDtoBeans) {
			this.employeeSkillDtoBeans = employeeSkillDtoBeans;
		}
		public Set<EmployeeRoleDtoBean> getEmployeeRoleDtoBeans() {
			return employeeRoleDtoBeans;
		}
		public void setEmployeeRoleDtoBeans(Set<EmployeeRoleDtoBean> employeeRoleDtoBeans) {
			this.employeeRoleDtoBeans = employeeRoleDtoBeans;
		}
		public EmployeeProfileDtoBean getEmployeeProfileDtoBean() {
			return employeeProfileDtoBean;
		}
		public void setEmployeeProfileDtoBean(EmployeeProfileDtoBean employeeProfileDtoBean) {
			this.employeeProfileDtoBean = employeeProfileDtoBean;
		}
		public List<QualificationDtoBean> getQualification() {
			return qualification;
		}
		public void setQualification(List<QualificationDtoBean> qualification) {
			this.qualification = qualification;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
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
		public String getContactNum() {
			return contactNum;
		}
		public void setContactNum(String contactNum) {
			this.contactNum = contactNum;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getPinCode() {
			return pinCode;
		}
		public void setPinCode(String pinCode) {
			this.pinCode = pinCode;
		}
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public Date getRegistrationDate() {
			return registrationDate;
		}
		public void setRegistrationDate(Date registrationDate) {
			this.registrationDate = registrationDate;
		}
	
	
		
		public EmployeeDtoBean(Integer id, String firstName, String lastName, String email, String password,
				String contactNum, String country, String state, String city, String pinCode, String street,
				Date registrationDate, List<QualificationDtoBean> qualification,
				List<WorkExperienceDtoBean> workExperienceDtoBeans, List<EmployeeSkillDtoBean> employeeSkillDtoBeans,
				Set<EmployeeRoleDtoBean> employeeRoleDtoBeans, List<EmployeeProjectsDtoBean> employeeProjectsDtoBeans,
				EmployeeProfileDtoBean employeeProfileDtoBean) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
			this.contactNum = contactNum;
			this.country = country;
			this.state = state;
			this.city = city;
			this.pinCode = pinCode;
			this.street = street;
			this.registrationDate = registrationDate;
			this.qualification = qualification;
			this.workExperienceDtoBeans = workExperienceDtoBeans;
			this.employeeSkillDtoBeans = employeeSkillDtoBeans;
			this.employeeRoleDtoBeans = employeeRoleDtoBeans;
			this.employeeProjectsDtoBeans = employeeProjectsDtoBeans;
			this.employeeProfileDtoBean = employeeProfileDtoBean;
		}
		public EmployeeDtoBean() {
			
		}
}
