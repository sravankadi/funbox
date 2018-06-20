package com.text.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.text.model.Job;
@Repository("jobDao")
@Transactional
public class JobDaoImpl implements JobDao {
	@Autowired
private SessionFactory sessionFactory;
	public void saveJob(Job job) {
		Session session=sessionFactory.getCurrentSession();
		session.save(job);

	}
	//jobs which are active.
	public List<Job> getActiveJobs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Job where active=true");
		return query.list(); //list of active jobs
		
	}
	//No vacancies for the job positions
	public List<Job> getInActiveJobs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Job where active=false");
		return query.list();
	}
	public void updateJob(Job job) {// activating and deactivating job positions
	  Session session=sessionFactory.getCurrentSession();
	  session.update(job);
		
	}
	

}