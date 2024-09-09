package com.JobPortalWeb.jobwebapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortalWeb.jobwebapp.Entity.Bookmark;
import com.JobPortalWeb.jobwebapp.Entity.Employee;
import com.JobPortalWeb.jobwebapp.Entity.Job;
import com.JobPortalWeb.jobwebapp.Repository.EmployeeRepo;
import com.JobPortalWeb.jobwebapp.Service.BookmarkService;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

	@Autowired
	private BookmarkService bookmarkService;
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@PostMapping
	public ResponseEntity<String> saveOrUpdateBookmark(@RequestParam Integer employeeId, @RequestParam Integer jobId, @RequestParam boolean isBookmarked) {
	    Employee employee = new Employee(); // Fetch employee by ID from the database
	    employee.setId(employeeId);
	    Job job = new Job(); // Fetch job by ID from the database
	    job.setId(jobId);
	    bookmarkService.saveOrUpdateBookmark(employee, job, isBookmarked);

	    String message = isBookmarked ? "Bookmarked" : "Bookmark removed";
	    return ResponseEntity.ok(message);
	}

	@GetMapping
    public ResponseEntity<List<Job>> getBookmarkedJobs(@RequestParam Integer employeeId) {
        // Fetch the employee from the database
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Get bookmarked jobs for the employee
        List<Job> bookmarkedJobs = bookmarkService.getBookmarkedJobs(employee);

        return ResponseEntity.ok(bookmarkedJobs);
    }
}
