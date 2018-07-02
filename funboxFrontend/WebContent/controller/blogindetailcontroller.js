/**
 * BlogInDetailCtrl
 */
app.controller('BlogInDetailCtrl',function($scope,$location,BlogService,$rootScope,$routeParams,$sce){
	
	var id=$routeParams.id
	$scope.showCommentsIsClicked=false;
	BlogService.getBlogPost(id).then(function(response){
		$scope.blogPost=response.data
		$scope.content=$sce.trustAsHtml($scope.blogPost.blogContent)
	},function(response){
		$scope.error=response.data
		if(response.status==401)
			$location.path('/login')

	})
	BlogService.hasUserLikedBlog(id).then(function(response){
		if(response.data=='')

			 $scope.isLiked=false
			 else
				 $scope.isLiked=true 
			
	},function(response){
		$scope.error=response.data
		if(response.status==401)
			$location.path('/login')
	})  
		
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
			$scope.blogPost=response.data
               $scope.isLiked=!$scope.isLiked;
		},function(response){
			$scope.error=response.data
			if(response.status==401)
				$location.path('/login')
		})
	}

})