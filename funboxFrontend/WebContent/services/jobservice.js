/**
 * JobService
 */
app.factory('JobService',function($http){
	var jobService={}
	
	jobService.addJob=function(job){
		return $http.post("http://localhost:9020/funboxMiddleware/addjob",job)

			}
	jobService.getActiveJobs=function(){
		return $http.get("http://localhost:9020/funboxMiddleware/activejobs")

	}
	jobService.getInActiveJobs=function(){
		return $http.get("http://localhost:9020/funboxMiddleware/inactivejobs")

	}
	jobService.updateActiveStatus=function(job){
		return $http.put("http://localhost:9020/funboxMiddleware/updatejob",job)

		
	}
   return jobService;

})
/**
 * 
 */