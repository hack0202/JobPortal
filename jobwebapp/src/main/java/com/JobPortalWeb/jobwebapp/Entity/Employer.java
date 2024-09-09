package com.JobPortalWeb.jobwebapp.Entity;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Table(name="employer")
public class Employer implements UserDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
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
	
	@OneToMany(fetch=FetchType.EAGER ,mappedBy = "employer" ,cascade = CascadeType.ALL)
	private Set<EmployerRole> roles;
	
	
	
	
	
	public int getId() 
	{
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
	

	
	public Employer() {
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());
	}

	 @Override
	    public String getUsername() {
	        return this.email;
	    }

	 

	    @Override
	    public boolean isAccountNonExpired() {
	        return true; // Customize according to your logic
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true; // Customize according to your logic
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true; // Customize according to your logic
	    }

	    @Override
	    public boolean isEnabled() {
	        return true; // Customize according to your logic
	    }
	
	
}
