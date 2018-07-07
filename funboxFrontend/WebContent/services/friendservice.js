/**
 * FriendService
 */
app.factory('FriendService',function($http){
	var friendService={}
	
	
	friendService.getSuggestedUsers=function(){
		return $http.get("http://localhost:9020/funboxMiddleware/suggestedusers")
		
	}	
	friendService.sendFriendRequest=function(toIdValue){// toIdValue is User object
		return $http.post("http://localhost:9020/funboxMiddleware/addfriend",toIdValue)
		
	}	
	friendService.getPendingRequests=function(){
		return $http.get("http://localhost:9020/funboxMiddleware/pendingrequests")
		
	}	
	friendService.updateStatus=function(updatedFriendRequest){
		return $http.put("http://localhost:9020/funboxMiddleware/updatestatus",updatedFriendRequest)
		
	}	
	friendService.getAllFriends=function(){
		return $http.get("http://localhost:9020/funboxMiddleware/friends")
		
	}	

	return friendService;
})