package com.JobPortalWeb.jobwebapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortalWeb.jobwebapp.Entity.JobCategory;
import com.JobPortalWeb.jobwebapp.Service.JobCategoryService;

@RestController
@RequestMapping("/jobcategory")
@CrossOrigin("http://localhost:5173/")
public class JobCategoryController {
	
	@Autowired
	private JobCategoryService jobCategoryService;
	
	   @PostMapping
	    public ResponseEntity<JobCategory> addJobCategory(@RequestBody JobCategory jobCategory) {

	        return new ResponseEntity<>(jobCategoryService.createJobCategory(jobCategory), HttpStatus.CREATED);
	    }
	   @GetMapping
	    public ResponseEntity<List<JobCategory>> getAllJobCategories() {

	        return ResponseEntity.ok(jobCategoryService.getAllJobCategories());
	    }
	   
	    @PutMapping("/{id}")
	    public ResponseEntity<JobCategory> updateJobCategory(@PathVariable("id") int id,
	                                                         @RequestBody JobCategory jobCategory) {

	        return ResponseEntity.ok(jobCategoryService.updateJobCategory(id, jobCategory));
	    }

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") int id){
		return ResponseEntity.ok(jobCategoryService.deleteJobCategoryById(id));
	}

}
