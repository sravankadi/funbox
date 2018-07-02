package com.text.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.text.Dao.JobDao;
import com.text.Dao.UserDao;
import com.text.model.ErrorClazz;
import com.text.model.Job;
import com.text.model.User;

@RestController
public class JobController {
    @Autowired
	private JobDao jobDao;
    @Autowired
    private UserDao userDao;
    @RequestMapping(value="/addjob",method=RequestMethod.POST)
    public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session){
    	//Authentication and Authorization
    	//Authentication - who u are? use HttpSession
    	//Authorization is - role?
    	String email=(String)session.getAttribute("email");
    	if(email==null){//not logged in
    		ErrorClazz errorClazz=new ErrorClazz(7,"Unauthorized access.. please login");
    		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//2nd callback fun
    	}
    	//If email !=null, user is an authenticated user
    	//check user is authorized to insert a new job or not. check the role of the user
    	//check if the logged in user is admin or not
    	User user=userDao.getUser(email);
    	if(!user.getRole().equals("ADMIN")){
    		ErrorClazz errorClazz=new ErrorClazz(8,"Access Denied..");
    		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//2nd callback fun
    	}
    	//Authentiacated and authorized to insert new job details
    	try{
    		job.setPostedOn(new Date());
    		job.setActive(true);//job position is still available
    	jobDao.saveJob(job);
    	return new ResponseEntity<Void>(HttpStatus.OK);//1st call back fun
    	}catch(Exception e){
    		ErrorClazz errorClazz=new ErrorClazz(4,"Unable to insert job details.."+e.getMessage());
    		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);//2nd callback fun
    	}
    }
    @RequestMapping(value="/activejobs",method=RequestMethod.GET)//select * from job where active=true
    public ResponseEntity<?> getActiveJobs(HttpSession session){//Authentication
    	String email=(String)session.getAttribute("email");
    	if(email==null){
    		ErrorClazz errorClazz=new ErrorClazz(7,"Unauthorized access.. please login");
    		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//2nd callback fun
    	}
    	
    	List<Job> activeJobs=jobDao.getActiveJobs();
    	return new ResponseEntity<List<Job>>(activeJobs,HttpStatus.OK);
    }
    @RequestMapping(value="/inactivejobs",method=RequestMethod.GET)//select * from job where active=false
    public ResponseEntity<?> getInActiveJobs(HttpSession session){//Authenctication and authorization
    	String email=(String)session.getAttribute("email");
    	if(email==null){
    		ErrorClazz errorClazz=new ErrorClazz(7,"Unauthorized access.. please login");
    		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//2nd callback fun
    	}
    	User user=userDao.getUser(email);
    	if(!user.getRole().equals("ADMIN")){
    		ErrorClazz errorClazz=new ErrorClazz(8,"Access Denied..");
    		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//2nd callback fun
    	}
          List<Job> inActiveJobs=jobDao.getInActiveJobs();
          return new ResponseEntity<List<Job>>(inActiveJobs,HttpStatus.OK);
    }
    @RequestMapping(value="/updatejob",method=RequestMethod.PUT)
    public ResponseEntity<?> updateJob(HttpSession session,@RequestBody Job job){//job with updated active value
    	String email=(String)session.getAttribute("email");
    	if(email==null){
    		ErrorClazz errorClazz=new ErrorClazz(7,"Unauthorized access.. please login");
    		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//2nd callback fun
    	}
    	User user=userDao.getUser(email);
    	if(!user.getRole().equals("ADMIN")){
    		ErrorClazz errorClazz=new ErrorClazz(8,"Access Denied..");
    		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//2nd callback fun
    	}
    	jobDao.updateJob(job);
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }
}











