(function () {
    'use strict';
 
    angular
        .module('releasetracker')
        .controller('RegisterController', RegisterController);
 
    RegisterController.$inject = ['RegistrarService', '$location', '$rootScope', 'FlashService'];
    function RegisterController(RegistrarService, $location, $rootScope, FlashService) {
        var vm = this;
 
        vm.register = register;
 
        function register() {
            vm.dataLoading = true;
            RegistrarService.Register(vm.user, function (response){
                if (response.success) {
                    $rootScope.globals.currentAuthUser	= response.user;
                    $location.path('/');
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        }
    }
 
})();