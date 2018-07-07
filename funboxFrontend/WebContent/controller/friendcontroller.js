/**
 * FriendController
 */
app.controller('FriendCtrl',function($scope,$location,FriendService){
	function getSuggestedUsers(){
		FriendService.getSuggestedUsers().then(function(response){
			$scope.suggestedUsers=response.data
		},function(response){
			$scope.error==response.data
			if(response.status==401)
				$location.path('/login')
		})
	}
getSuggestedUsers()
$scope.sendFriendRequest=function(toIdValue){
	FriendService.sendFriendRequest(toIdValue).then(function(response){
		alert('Friend Request has been send successfully..... ')
		getSuggestedUsers()
	},function(response){
		$scope.error==response.data
		if(response.status==401)
			$location.path('/login')
	})
}
function getPendingRequests(){
	FriendService.getPendingRequests().then(function(response){
		$scope.pendingRequests=response.data
	},function(response){
		$scope.error==response.data
		if(response.status==401)
			$location.path('/login')
	})
}
getPendingRequests()

     $scope.updateStatus=function(friendRequest,updatedStatus){
	 //char updatedStatus=[A or D]
	  //friendRequest is friend object
	  //friendRequest.status=A or friendRequest.status=D
	friendRequest.status=updatedStatus;
    FriendService.updateStatus(friendRequest).then(
			function(response){
		getPendingRequests()
	},
	function(response){
		$scope.error==response.data
		if(response.status==401)
			$location.path('/login')
	})
	
}

       function getAllFriends(){
    	   FriendService.getAllFriends().then(function(response){
    		   //response.data=?
    		   $scope.friends=response.data
    		   },function(response){
    		   $scope.error==response.data
    			if(response.status==401)
    				$location.path('/login')
    	   })
       }
       getAllFriends()
 })