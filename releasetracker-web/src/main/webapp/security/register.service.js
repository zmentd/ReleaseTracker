(function () {
    'use strict';
 
    angular
        .module('releasetracker')
        .factory('RegistrarService', RegistrarService);
 
    RegistrarService.$inject = ['$http'];
    function RegistrarService($http) {
        var service = {};
        service.Register	= Register;
 
        return service;
 
        function Register(user, callback) {
            
        	$http({
        	    url: '/registrar/v1/register',
        	    dataType: 'json',
        	    method: 'POST',
        	    data: user,
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
