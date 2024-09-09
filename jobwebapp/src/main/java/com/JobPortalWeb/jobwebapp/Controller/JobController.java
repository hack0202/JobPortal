package com.JobPortalWeb.jobwebapp.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JobPortalWeb.jobwebapp.BeanDto.JobResponse;
import com.JobPortalWeb.jobwebapp.Service.JobService;

@RestController
@RequestMapping("/job")
@CrossOrigin("http://localhost:5173")
public class JobController {
  @Autowired
 private JobService jobService;
  
  @GetMapping("/{jobId}")
  public ResponseEntity<JobResponse> getJobByJobId(@PathVariable("jobId") int jobId) {

      return ResponseEntity.ok(jobService.getJobByJobId(jobId));
  }
  
  @GetMapping("/alljobs")
  public ResponseEntity<Page<JobResponse>> getAllJobs(
          @RequestParam(defaultValue = "0") int page, 
          @RequestParam(defaultValue = "10") int size) {
      Pageable pageable = PageRequest.of(page, size);
      Page<JobResponse> jobs = jobService.getAllJobs(pageable);
      return ResponseEntity.ok(jobs);
  }
  
  @GetMapping("/search")
  public ResponseEntity<Map<String, Object>> searchJobs(
          @RequestParam(value = "jobCategory", required = false) String jobCategory,
          @RequestParam(value = "jobType", required = false) String jobType,
          @RequestParam(value = "country", required = false) String country,
          @RequestParam(value = "skills", required = false) String skills,
          @RequestParam(value = "title", required = false) String title,
          @RequestParam(value = "salaryRange", required = false) String salaryRange,
          @RequestParam(value = "experience", required = false) String experience,
          @RequestParam(value = "companyName", required = false) String companyName,
          @RequestParam(value = "city", required = false) String city,
          @RequestParam(value = "page", defaultValue = "0") int page,
          @RequestParam(value = "size", defaultValue = "10") int size) {

      Pageable pageable = PageRequest.of(page, size, Sort.by("title")); // Customize sort as needed
      Page<JobResponse> jobPage = jobService.searchJob(
              jobCategory,
              jobType,
              country,
              skills,
              title,
              salaryRange,
              experience,
              companyName,
              city,
              pageable
      );

      Map<String, Object> response = new HashMap<>();
      response.put("content", jobPage.getContent());
      response.put("totalPages", jobPage.getTotalPages());
      response.put("totalElements", jobPage.getTotalElements());

      return ResponseEntity.ok(response);
  }

}
