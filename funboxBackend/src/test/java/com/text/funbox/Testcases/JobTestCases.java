package com.text.funbox.Testcases;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.text.Dao.JobDao;
import com.text.Dao.UserDao;
import com.text.model.Job;
import com.text.model.User;

public class JobTestCases {
	
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	 static JobDao  jobDao;
	
	@BeforeClass
	public static void initialize()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.text");
		context.refresh();
		
		//get the userDAO from context
				jobDao =  (JobDao) context.getBean("jobDao");
	}
	
	@Test
	public void createJobTestCase()
	{
				
	Job job=new Job();

	job.setCompanyName("Wipro");
	job.setJobDescription("Software Developer");
	job.setJobTitle("WebTechnology");
	job.setJobLocation("Bangalore");
	job.setPostedOn(new Date());
	job.setSalary("4.5 Lac");
	job.setSkillsRequired("C,Java,SQL");
	
	job.setExperienceRequired("2.5 Years");
	jobDao.saveJob(job);
	
	assertEquals(job.getId(),job.getId());
	
	
	
	}
}
