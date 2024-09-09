package com.JobPortalWeb.jobwebapp.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JobPortalWeb.jobwebapp.Entity.Bookmark;
import com.JobPortalWeb.jobwebapp.Entity.Employee;
import com.JobPortalWeb.jobwebapp.Entity.Job;
import com.JobPortalWeb.jobwebapp.Repository.BookmarkRepository;

@Service
public class BookmarkService {
	@Autowired
	private BookmarkRepository bookmarkRepository;
	
	
	   public void saveOrUpdateBookmark(Employee employee, Job job, boolean isBookmarked) {
	        Bookmark bookmark = bookmarkRepository.findByEmployeeAndJob(employee, job)
	                .orElse(new Bookmark(employee, job, isBookmarked));
	        bookmark.setBookmarked(isBookmarked);
	        bookmarkRepository.save(bookmark);
	    }
	   
//	   
//	    public List<Bookmark> getBookmarkedJobs(Employee employee) {
//	        return bookmarkRepository.findByEmployee(employee);
//	    }
	    public List<Job> getBookmarkedJobs(Employee employee) {
	        List<Bookmark> bookmarks = bookmarkRepository.findByEmployee(employee);
	        return bookmarks.stream()
	                        .filter(Bookmark::isBookmarked)
	                        .map(Bookmark::getJob)
	                        .collect(Collectors.toList());
	    }

}
