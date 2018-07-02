package com.text.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class BlogPostLikes {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int likeId;
	@ManyToOne
private BlogPost blogpost;
	@ManyToOne
private User user;
public int getLikeId() {
	return likeId;
}
public void setLikeId(int likeId) {
	this.likeId = likeId;
}
public BlogPost getBlogpost() {
	return blogpost;
}
public void setBlogpost(BlogPost blogpost) {
	this.blogpost = blogpost;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}

}
