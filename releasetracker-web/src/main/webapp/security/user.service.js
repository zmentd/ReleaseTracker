(function () {
    'use strict';
 
    angular
        .module('releasetracker')
        .factory('UserService', UserService);
 
    UserService.$inject = ['$http', 'FlashService'];
    function UserService($http, FlashService) {
        var service = {};
        service.LocateUsers	= LocateUsers;
 
        return service;
 
        function LocateUsers(request ) {
            
        	return $http({
        	    url: '/user/v1/locateUsers',
        	    dataType: 'json',
        	    method: 'POST',
        	    data: request,
        	    headers: {
        	        "Content-Type": "application/json",
        	        "Authorization": "Basic"
        	    }
        	}).then(function(response){
        	    var output = [];
                if (response.data.success) {
                	response.data.users.forEach(function (user)
        	        {
                		output.push( user );
        	        });
                	FlashService.Success( response.data.message );
                } else {
                    FlashService.Error(response.data.message);
                }

                return output;
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
