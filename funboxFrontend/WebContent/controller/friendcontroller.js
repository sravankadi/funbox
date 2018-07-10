/**
 * FriendController
 */
app.controller('FriendCtrl',function($scope,$location,FriendService,$rootScope){
	function getSuggestedUsers(){
		FriendService.getSuggestedUsers().then(function(response){
			//response.data? List<User> - suggestedUsers
		$scope.suggestedUsers=response.data
		},function(response){
			$scope.error=response.data
			if(response.status==401)
				$location.path('/login')
		
		})
		
		
	}
	 getSuggestedUsers()
	 
	 $scope.sendFriendRequest=function(toIdValue){
		 FriendService.sendFriendRequest(toIdValue).then(function(response){
			 alert('Friend request has been sent successfully..')
			 getSuggestedUsers()
		 },function(response){
			 $scope.error=response.data
				if(response.status==401)
					$location.path('/login')
		 })
	 }
	 function getPendingRequests(){
		 FriendService.getPendingRequests().then(function(response){
			 //response.data-> Type of data List<Friend>
			 $scope.pendingRequests=response.data
		 },function(response){
			 $scope.error=response.data
				if(response.status==401)
					$location.path('/login')
		 
		 })
	 }
	  getPendingRequests()
	  $scope.updateStatus=function(friendRequest,updatedStatus){
		//char updatedStatus=[A or D]
		  //friendRequest is Friend object
		  //friendRequest.status=A or friendRequest.status=D
		  friendRequest.status=updatedStatus; //[A or D]Friend {id=41,status='A',fromId,toId}
		  FriendService.updateStatus(friendRequest).then(
				  function(response){
			     getPendingRequests()
		  },
		  function(response){
			  $scope.error=response.data
				if(response.status==401)
					$location.path('/login')
		  })
	  }
	      function getAllFriends(){
	    	  FriendService.getAllFriends().then(function(response){
	    		//response.data=?
	    		  $scope.friends=response.data
	    	  },function(response){
	    		  $scope.error=response.data
					if(response.status==401)
						$location.path('/login')
	    	  
	    	  })
	      }
	       getAllFriends()
})