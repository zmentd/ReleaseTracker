(function () {
    'use strict';
 
    angular
        .module('releasetracker')
        .controller('LogoutController', LogoutController);
 
    LogoutController.$inject = ['$location', 'AuthenticationService', '$rootScope'];
    function LogoutController($location, AuthenticationService, $rootScope) {
        var vm = this;
        vm.logout = logout;
//        Session.clear();
        // reset login status
        AuthenticationService.ClearCredentials();
        $rootScope.globals.currentUser	= null;
        $location.path('/login');
   
        function logout() {
//            Session.clear();
            // reset login status
            AuthenticationService.ClearCredentials();
            $rootScope.globals.currentUser	= null;
            $location.path('/login');
        };
    }
})();