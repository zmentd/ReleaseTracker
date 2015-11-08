//(function () {
//    'use strict';

var app = angular
        .module('releasetracker')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$rootScope', 'HomeService', '$controller', '$location', 'FlashService', '$scope'];
    function HomeController($rootScope, HomeService, $controller, $location, FlashService, $scope ) {
        var vm = this;
        vm.user = $rootScope.globals.currentUser;
        vm.findAssignedTo	= findAssignedTo();

        function findAssignedTo() {
            vm.dataLoading = true;
            var request = {
            		assignedToId:vm.user.id
            };
            HomeService.findAssignedTo(request, function (response){
                if (response.success) {
                	vm.ideas 	= response.ideas;
                	FlashService.Success( response.message );
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        }

    }

//})();