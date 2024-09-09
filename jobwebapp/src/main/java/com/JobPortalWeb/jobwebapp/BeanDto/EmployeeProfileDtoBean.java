package com.JobPortalWeb.jobwebapp.BeanDto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class EmployeeProfileDtoBean {
	
	  private Integer id;
	    private String photopath;
	    private String github;
	    private String linkedin;
	    private String bio;
	    private String website;
	    private String resumepath;
	    
//	    private MultipartFile photopath;
	    
	
		
		public String getGithub() {
			return github;
		}
		public void setGithub(String github) {
			this.github = github;
		}
		public String getLinkedin() {
			return linkedin;
		}
		public void setLinkedin(String linkedin) {
			this.linkedin = linkedin;
		}
		public String getBio() {
			return bio;
		}
		public void setBio(String bio) {
			this.bio = bio;
		}
		public String getWebsite() {
			return website;
		}
		public void setWebsite(String website) {
			this.website = website;
		}
	
	
	    
	    public String getPhotopath() {
			return photopath;
		}
		public void setPhotopath(String photopath) {
			this.photopath = photopath;
		}
		public String getResumepath() {
			return resumepath;
		}
		public void setResumepath(String resumepath) {
			this.resumepath = resumepath;
		}
		

		public EmployeeProfileDtoBean() {
	    	
	    }

}
