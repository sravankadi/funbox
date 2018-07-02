package com.text.Dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.text.model.BlogPost;
import com.text.model.BlogPostLikes;
import com.text.model.User;
@Repository
@Transactional
public class BlogPostLikesDaoImpl implements BlogPostLikesDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public BlogPostLikes hasUserLikedBlogPost(int blogpostId, String email) {
			
	Session session=sessionFactory.getCurrentSession();
	Query query=session.createQuery("from BlogPostLikes where blogpost.id=? and user.email=?");
	query.setInteger(0,blogpostId);
	query.setString(1, email);
	BlogPostLikes blogPostLikes=(BlogPostLikes)query.uniqueResult();
	return blogPostLikes;
	
	}

	public BlogPost updateBlogPostLikes(int blogPostId, String email) {
		Session session=sessionFactory.getCurrentSession();
		BlogPostLikes blogPostLikes=hasUserLikedBlogPost(blogPostId,email);
		BlogPost blogPost=(BlogPost) session.get(BlogPost.class,blogPostId);
		if(blogPostLikes==null){
			 blogPostLikes=new BlogPostLikes();
			 User user=(User)session.get(User.class,email);
			 blogPostLikes.setBlogpost(blogPost);
			 blogPostLikes.setUser(user);
			 session.save(blogPostLikes);
			 blogPost.setLikes(blogPost.getLikes() + 1);
			 session.update(blogPost);
		}else{
			session.delete(blogPostLikes);
			blogPost.setLikes(blogPost.getLikes() - 1);
			session.update(blogPost);
		
		}
		return blogPost;
	}
	
	

}