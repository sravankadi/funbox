/**
 * BlogInDetailCtrl
 */
app.controller('BlogInDetailCtrl',function($scope,$location,BlogService,$rootScope,$routeParams,$sce){
	
	var id=$routeParams.id// id of the blogpost which has to be viwed by the user
	$scope.showCommentsIsCliked=false;
	BlogService.getBlogPost(id).then(function(response){
		//response.data -> blogpost object[select * from blogpost where id=?]
		$scope.blogPost=response.data
		$scope.content=$sce.trustAsHtml($scope.blogPost.blogContent)
	},function(response){
		$scope.error=response.data
		if(response.status==401)
			$location.path('/login')

	})
	BlogService.hasUserLikedBlog(id).then(function(response){
		if(response.data=='')//response.data=select * from blogpostlikes where blogpost_id=?  and user_email=?

			 $scope.isLiked=false//glyphicon color is black
			 else
				 $scope.isLiked=true //glyphicon color is blue
			
	},function(response){
		$scope.error=response.data
		if(response.status==401)
			$location.path('/login')
	})  
		//Approve button is clicked
	$scope.approve=function(blogPost){
		blogPost.approved=true
		BlogService.updateApprovalStatus(blogPost).then(function(response){
			$location.path('/blogwaitingforapproval')
		},function(response){
			$scope.error=response.data
			if(response.status==401)
				$location.path('/login')
		})
	}
	//reject button is clicked
	$scope.reject=function(blogPost){
		
		blogPost.approved=false
		BlogService.updateApprovalStatus(blogPost).then(function(response){
			$location.path('/blogwaitingforapproval')
		},function(response){
			$scope.error=response.data
			if(response.status==401)
				$location.path('/login')
		})
		
	}
	$scope.updateBlogPostLikes=function(blogPostId){
		BlogService.updateBlogPostLikes(blogPostId).then(function(response){
			$scope.blogPost=response.data//updated blogpost object
               $scope.isLiked=!$scope.isLiked;
		},function(response){
			$scope.error=response.data
			if(response.status==401)
				$location.path('/login')
		})
	
	}
	$scope.addComment=function(){
		BlogService.addComment($scope.commentTxt,id).then(
				//id is the id of the blogpost
				//commentTxt is the comment entered by the user
				function(response){
			getAllBlogComments()
			$scope.commentTxt=''
		},function(response){
			$scope.error=response.data
			if(response.status==401)
				$location.path('/login')
		})
		
	}
	function getAllBlogComments(){
		BlogService.getAllBlogComments(id).then(function(response){
			$scope.blogComments=response.data
		},function(response){
			$scope.error=response.data
			if(response.status==401)
				$location.path('/login')
		})
		
	}
	$scope.showComments=function(){
		$scope.showCommentsIsClicked=!$scope.showCommentsIsClicked
	}
getAllBlogComments()

})