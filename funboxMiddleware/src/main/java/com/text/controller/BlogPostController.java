package com.text.controller;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.text.Dao.BlogPostDao;
import com.text.Dao.BlogPostLikesDao;
import com.text.Dao.UserDao;
import com.text.model.BlogComment;
import com.text.model.BlogPost;
import com.text.model.BlogPostLikes;
import com.text.model.ErrorClazz;
import com.text.model.User;

@RestController
public class BlogPostController {
	@Autowired
	private BlogPostDao blogPostDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogPostLikesDao blogPostLikesDao;
	//{blogTitle:'Introduction to DBMS',blogContent:'--------------'}
	@RequestMapping(value="/addblogpost",method=RequestMethod.POST)
	   public ResponseEntity<?> saveBlogPost(HttpSession session,@RequestBody BlogPost blogPost){//Authentication And Authorization
		   //check for authentication
		String email=(String)session.getAttribute("email");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(7,"Unauthorized access..please login");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);

	 }
	
		blogPost.setPostedOn(new Date());
		//postedBy-author,logged in user
User postedBy=userDao.getUser(email);
blogPost.setPostedBy(postedBy);
blogPostDao.saveBlogPost(blogPost);
return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);

} 
	@RequestMapping(value="/blogsapproved",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogsApproved(HttpSession session){
		String email=(String)session.getAttribute("email");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(7,"Unauthorized access..please login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);

	  }
		List<BlogPost> blogsApproved=blogPostDao.approvedBlogs();
		return new ResponseEntity<List<BlogPost>>(blogsApproved,HttpStatus.OK);
	} 
	@RequestMapping(value="/blogswaitingforapproval",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogsWaitingForApproval(HttpSession session){
		
		String email=(String)session.getAttribute("email");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(7,"Unauthorized access..please login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);

	  }
		User user=userDao.getUser(email);
		if(!user.getRole().equals("ADMIN")){
			ErrorClazz errorClazz=new ErrorClazz(8,"Access Denined......");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		List<BlogPost> blogsWaitingForApproval=blogPostDao.blogsWaitingForApproval();
		
		return new ResponseEntity<List<BlogPost>>(blogsWaitingForApproval,HttpStatus.OK);
	}
	@RequestMapping(value="/getblogpost/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogPost(@PathVariable int id,HttpSession session){
		String email=(String)session.getAttribute("email");
	if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(7,"Unauthorized access..please login");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
		
		BlogPost blogPost=blogPostDao.getBlogPost(id);
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updatestatusapproval",method=RequestMethod.PUT)
		public ResponseEntity<?> updateApprovalStatus(@RequestBody BlogPost blogPost,HttpSession session){
			String email=(String)session.getAttribute("email");
			if(email==null){
				ErrorClazz errorClazz=new ErrorClazz(7,"Unauthorized access..please login");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);

		  }
			User user=userDao.getUser(email);
			if(!user.getRole().equals("ADMIN")){
				ErrorClazz errorClazz=new ErrorClazz(8,"Access Denined......");
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			try{
			blogPostDao.updateApprovalStatus(blogPost);
			}catch(Exception e){
				ErrorClazz errorClazz=new ErrorClazz(10,"Unable to approve/reject the blogpost"+e.getMessage());
				return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	@RequestMapping(value="/hasuserlikedblog/{blogPostId}",method=RequestMethod.GET)
	public ResponseEntity<?> hasUserLikedBlogPost(@PathVariable int blogPostId,HttpSession session){
		String email=(String)session.getAttribute("email");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(7,"Unauthorized access..please login");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		//if blogpostlikes is null,response.data=?
		//if blogpostlikes is 1 object,response.data=[blogpostlikes object]

	 }

		BlogPostLikes blogPostLikes=blogPostLikesDao.hasUserLikedBlogPost(blogPostId, email);
		return new ResponseEntity<BlogPostLikes>(blogPostLikes,HttpStatus.OK);
	}
	@RequestMapping(value="/updateblogpostlikes/{blogPostId}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateBlogPostLikes(@PathVariable int blogPostId,HttpSession session){
		String email=(String)session.getAttribute("email");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(7,"Unauthorized access..please login");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);

	 }
		BlogPost blogPost=blogPostLikesDao.updateBlogPostLikes(blogPostId,email);
		
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		//blogpost likes count is updated
		
	}
	@RequestMapping(value="/addcomment/{commentTxt}/{id}",method=RequestMethod.POST)
	public ResponseEntity<?> addBlogComment(@PathVariable String commentTxt,@PathVariable int id,HttpSession session){
		String email=(String)session.getAttribute("email");
		if(email==null){
		ErrorClazz errorClazz=new ErrorClazz(7,"Unauthorized access..please login");
return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);

 }
		 
		BlogComment blogComment=new BlogComment();
		BlogPost blogPost=blogPostDao.getBlogPost(id);
		User user=userDao.getUser(email);
		blogComment.setBlogPost(blogPost);
		blogComment.setCommentedBy(user);
		blogComment.setCommentTxt(commentTxt);
		blogComment.setCommentedOn(new Date());
		blogPostDao.addBlogComment(blogComment);
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
		
	}
	@RequestMapping(value="/getblogcomments/{blogPostId}",method=RequestMethod.GET)
	public ResponseEntity<?> getAllBlogComments(@PathVariable int blogPostId,HttpSession session){
		String email=(String)session.getAttribute("email");
		if(email==null){
		ErrorClazz errorClazz=new ErrorClazz(7,"Unauthorized access..please login");
        return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		List<BlogComment> blogComments=blogPostDao.getAllBlogComments(blogPostId);
		return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
	}
	}