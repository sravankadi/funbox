app.controller('BlogCtrl',function($scope,BlogService,$location,$rootScope){
	
	$scope.addBlog=function(blog){
		
		BlogService.addBlog(blog).then(function(response){
			alert('blogpost hasbeen inserted successfully... It is waiting for approval..')
			$location.path('/home')
		},function(response){
			$scope.error==response.data
			if(response.status==401)
				$location.path('/login')
			
		})
		
	}
	function blogsApproved(){
		 BlogService.blogsApproved().then(function(response){
			 $scope.blogsApproved=response.data
		 },function(response){
			 $scope.error==response.data
				if(response.status==401)
					$location.path('/login')
				
		 })
	}
	function blogsWaitingForApproval(){
		 BlogService.blogsWaitingForApproval().then(function(response){
			 $scope.blogsWaitingForApproval=response.data
		 },function(response){
			 $scope.error==response.data
				if(response.status==401)
					$location.path('/login')
				
		 })
	}
	blogsApproved()
	if($rootScope.loggedInUser.role=='ADMIN')
	
	blogsWaitingForApproval()
	
	
})