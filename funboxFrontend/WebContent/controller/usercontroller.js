/**
 * UserController
 * 1. Registration - Data is from View to Controller $scope.register=function(user)
 */
app.controller('UserCtrl',function($scope,UserService,$location,$rootScope,$cookieStore){
	
	$scope.register=function(user){
		console.log(user)
		UserService.register(user).then(function(response){
			$location.path('/login')
		},function(response){
			$scope.error=response.data //ErrorClazz
			console.log(response.data)
			console.log(response.status)
		})
	}
	
	$scope.login=function(user){
		UserService.login(user).then(function(response){
			//response.data ={email:"...",firstname:"..",lastname:"...",}
			$rootScope.loggedInUser=response.data //loggedin User object
			$cookieStore.put('loggedInUser',response.data)
			$location.path('/home')
		},function(response){
			$scope.error=response.data
		})
	}
	
	$scope.updateProfile=function(user){
		//updated user object
		UserService.updateProfile(user).then(
				function(response){
					//store the updated user details in $rootScope and also in $cookieStore
					$rootScope.loggedInUser=response.data//updated user object from middleware
					$cookieStore.put('loggedInUser',response.data)
					alert('Updated user details successfully...')
					$location.path('/home')
				},
				function(response){
					$scope.error=response.data
					console.log(response.status)
					if(response.status==401)//Unauthorized access
						$location.path('/login')
				})
	}
	
})

