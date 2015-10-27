//(function () {
//    'use strict';

    app = angular
        .module('releasetracker')
        .controller('UserTypeaheadCtrl', UserTypeaheadCtrl);

    UserTypeaheadCtrl.$inject = ['UserService', '$location', '$rootScope', 'FlashService', '$scope'];
    function UserTypeaheadCtrl( UserService, $location, $rootScope, FlashService, $scope) {
        var vm 				= this;
    	var service 		= {};
    	service.LocateUsers = LocateUsers;
        
    	return service;
       
        function LocateUsers( viewValue ) {
            vm.dataLoading = true;
            var request = {
            		orderBy:"Name",
            		isAsc:"false",
            		countPerPage:"8",
            		namePrefix:"T"
            };
            
            UserService.LocateUsers(request, function (response){
                if (response.success) {
                	var output = [];
                	$response.data.objects.forEach(function (user)
        	        {
        	            output.push(user.firstName + ' ' + user.lastName );
        	        });
                	vm.locatedUsers	= response.list;
                	FlashService.Success( response.message );
                	return output;
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        }
        
        this.myMethod = function(){
        	return ["Travis Zimmerman"];
        }
//
//		$scope.user = {
//		    state: 'Arizona'
//		};
//		
//		$scope.states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Dakota', 'North Carolina', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'];
    }

//})();