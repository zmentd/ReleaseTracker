(function () {
    'use strict';
 
    angular
        .module('releasetracker')
        .factory('HomeService', HomeService);
 
    HomeService.$inject = ['$http'];
    function HomeService($http) {
        var service = {};
        service.findAssignedTo	= findAssignedTo;

        return service;
 
        function findAssignedTo(request, callback) {
            
        	$http({
        	    url: '/idea/v1/findAssignedTo',
        	    dataType: 'json',
        	    method: 'POST',
        	    data: request,
        	    headers: {
        	        "Content-Type": "application/json",
        	        "Authorization": "Basic"
        	    }
        	}).success(function(response){
        		callback(response);
        	});
        }
  
        // private functions
 
        function handleSuccess(res) {
            return res.data;
        }
 
        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }
})();
