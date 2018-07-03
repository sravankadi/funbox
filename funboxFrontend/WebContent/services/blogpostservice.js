/**
 * BlogService
 */
app.factory('BlogService',function($http){
	var blogService={}
	blogService.addBlog=function(blog){
		return $http.post("http://localhost:9020/funboxMiddleware/addblogpost",blog)
		
	}
	blogService.blogsApproved=function(){
		return $http.get("http://localhost:9020/funboxMiddleware/blogsapproved")
		
	}
	blogService.blogsWaitingForApproval=function(){
		return $http.get("http://localhost:9020/funboxMiddleware/blogswaitingforapproval")
		
	}
	blogService.getBlogPost=function(id){
		return $http.get("http://localhost:9020/funboxMiddleware/getblogpost/"+id)
	}
	blogService.updateApprovalStatus=function(blogPost){
		return $http.put("http://localhost:9020/funboxMiddleware/updatestatusapproval",blogPost)
	}
	blogService.hasUserLikedBlog=function(blogpostId){
		return $http.get("http://localhost:9020/funboxMiddleware/hasuserlikedblog/"+blogpostId)
	}
	blogService.updateBlogPostLikes=function(blogPostId){
		return $http.put("http://localhost:9020/funboxMiddleware/updateblogpostlikes/"+blogPostId)
	}

	return blogService;
}) 