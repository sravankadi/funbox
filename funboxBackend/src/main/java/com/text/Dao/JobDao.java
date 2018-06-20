package com.text.Dao;

import java.util.List;

import com.text.model.Job;

public interface JobDao {
	  void saveJob(Job job);
	  List<Job> getActiveJobs();//active is true,for other roles
	  //For admin to return all jobs
	  List<Job> getInActiveJobs();
	void updateJob(Job job);
	}
