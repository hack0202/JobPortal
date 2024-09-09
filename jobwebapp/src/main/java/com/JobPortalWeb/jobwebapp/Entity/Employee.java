package com.JobPortalWeb.jobwebapp.Entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="employee")
@Getter
@Setter
public class Employee implements UserDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
	public String firstName;
	public String lastName;
	@Column(unique = true)
	public String email;
	public String password;
	public String contactNum;
	public String country;
	public String state;
	public String city;
	public String pinCode;
	public String street;
	public Date registrationDate;
	
	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Qualification> qualification;
	
	@OneToMany(mappedBy = "employee" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	private List<WorkExperience> workExperiences;
	
	@OneToMany(mappedBy = "employee" , cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	private List<EmployeeSkill> employeeskill;
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL ,fetch=FetchType.EAGER)
	private List<EmployeeProjects> employeeProjects;
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "employee",cascade = CascadeType.ALL)
	private Set<EmployeeRole> employeerole;
	
	
	@OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER,mappedBy = "employee")
	@JoinColumn(name = "employee_id")
	@JsonIgnore
	private EmployeeProfileInfo employeeProfileInfo;
	
	 @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(name = "employee_bookmarked_jobs",
	               joinColumns = @JoinColumn(name = "employee_id"),
	               inverseJoinColumns = @JoinColumn(name = "job_id"))
	    private Set<Job> bookmarkedJobs;
	
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
	
	public List<Qualification> getQualification() {
		return qualification;
	}
	public void setQualification(List<Qualification> qualification) {
		this.qualification = qualification;
	}
	public List<WorkExperience> getWorkExperiences() {
		return workExperiences;
	}
	public void setWorkExperiences(List<WorkExperience> workExperiences) {
		this.workExperiences = workExperiences;
	}
	
	
	
	public List<EmployeeProjects> getEmployeeProjects() {
		return employeeProjects;
	}
	public void setEmployeeProjects(List<EmployeeProjects> employeeProjects) {
		this.employeeProjects = employeeProjects;
	}
	public List<EmployeeSkill> getEmployeeskill() {
		return employeeskill;
	}
	public void setEmployeeskill(List<EmployeeSkill> employeeskill) {
		this.employeeskill = employeeskill;
	}
	
	

	public Set<EmployeeRole> getEmployeerole() {
		return employeerole;
	}
	public void setEmployeerole(Set<EmployeeRole> employeerole) {
		this.employeerole = employeerole;
	}
	
	
	

	public EmployeeProfileInfo getEmployeeProfileInfo() {
		return employeeProfileInfo;
	}
	public void setEmployeeProfileInfo(EmployeeProfileInfo employeeProfileInfo) {
		this.employeeProfileInfo = employeeProfileInfo;
	}
	
	

	public Employee(int id, String firstName, String lastName, String email, String password, String contactNum,
			String country, String state, String city, String pinCode, String street, Date registrationDate,
			List<Qualification> qualification, List<WorkExperience> workExperiences, List<EmployeeSkill> employeeskill,
			List<EmployeeProjects> employeeProjects, Set<EmployeeRole> employeerole,
			EmployeeProfileInfo employeeProfileInfo) {
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
		this.workExperiences = workExperiences;
		this.employeeskill = employeeskill;
		this.employeeProjects = employeeProjects;
		this.employeerole = employeerole;
		this.employeeProfileInfo = employeeProfileInfo;
	}
	public Employee() {
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		  return employeerole.stream()
	                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
	                .collect(Collectors.toSet());
	}


   

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement according to your requirements
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement according to your requirements
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement according to your requirements
    }

    @Override
    public boolean isEnabled() {
        return true; // Implement according to your requirements
    }
	
	
}
