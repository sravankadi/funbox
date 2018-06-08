/**
 * UserService
 * 1. Register - call the middleware -> dao -> insert user details in User table
 */
app.factory('UserService',function($http){
	var userService={}
	
	userService.register=function(user){
		return $http.post("http://localhost:9090/funboxMiddleware/register",user)
	}
	
	userService.login=function(user){
		return $http.post("http://localhost:9090/funboxMiddleware/login",user)
	}
	
	userService.logout=function(){
		return $http.put("http://localhost:9090/funboxMiddleware/logout")
	}
     
	userService.updateProfile=function(user){//updated user profile
		return $http.put("http://localhost:9090/funboxMiddleware/update",user)
	}
	
	return userService;
})

