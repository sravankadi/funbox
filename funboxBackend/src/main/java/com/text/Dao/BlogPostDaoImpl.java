package com.text.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.text.model.BlogComment;
import com.text.model.BlogPost;
@Repository("blogPostDao")
@Transactional


public class   BlogPostDaoImpl implements BlogPostDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void saveBlogPost(BlogPost blogPost) {
		Session session=sessionFactory.getCurrentSession();
		session.save(blogPost);
	}

	public List<BlogPost> approvedBlogs() {
		Session session=sessionFactory.getCurrentSession();
		
		Query query=session.createQuery("from BlogPost where approved=true");
		return  query.list();//List of blogpost objects which are approved
	}

	public List<BlogPost> blogsWaitingForApproval() {
Session session=sessionFactory.getCurrentSession();
		
		Query query=session.createQuery("from BlogPost where approved=false");
		return  query.list();
	}

	public BlogPost getBlogPost(int id) {
		Session session=sessionFactory.getCurrentSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class,id);
		return blogPost;
	}

	public void updateApprovalStatus(BlogPost blogPost) {
		Session session=sessionFactory.getCurrentSession();
		if(blogPost.isApproved()){
			session.update(blogPost);
		}
		else{
			session.delete(blogPost);
			
		}
	}
	public void addBlogComment(BlogComment blogComment) {
		Session session=sessionFactory.getCurrentSession();
		session.save(blogComment);
		
	}

	public List<BlogComment> getAllBlogComments(int blogPostId) {
Session session=sessionFactory.getCurrentSession();
		
		Query query=session.createQuery("from BlogComment where blogPost.id="+blogPostId);
		return  query.list();
	}

	
}
