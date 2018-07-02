package com.text.Dao;

import com.text.model.BlogPost;
import com.text.model.BlogPostLikes;

public interface BlogPostLikesDao {
BlogPostLikes hasUserLikedBlogPost(int blogpostId,String email);
//null will be returned / 1 blogpostlikes objects will be returned
//Null - glyphicon will be balck color
//1 - glyphicon will be blue color

BlogPost updateBlogPostLikes(int blogPostId, String email);

}