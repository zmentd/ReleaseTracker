(function () {
    'use strict';

    angular
        .module('releasetracker')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$rootScope'];
    function HomeController($rootScope) {
        var vm = this;

        vm.user = $rootScope.globals.currentUser;

        initController();

        function initController() {
        }
    }

})();