(function () {
    'use strict';
 
    angular
        .module('releasetracker')
        .controller('RegisterController', RegisterController);
 
    RegisterController.$inject = ['RegistrarService', 'AuthenticationService', '$location', '$rootScope', 'FlashService'];
    function RegisterController(RegistrarService, AuthenticationService, $location, $rootScope, FlashService) {
        var vm = this;
 
        vm.register = register;
 
        function register() {
            vm.dataLoading = true;
            RegistrarService.Register(vm.user, function (response){
                if (response.success) {
                    $rootScope.globals.currentUser	= response.user;
                    AuthenticationService.SetCredentials($rootScope.globals.currentUser, password);
                    $location.path('/');
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        }
    }
 
})();