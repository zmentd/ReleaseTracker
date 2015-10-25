(function () {
    'use strict';

    angular
        .module('releasetracker')
        .controller('ReleaseCalendarController', ReleaseCalendarController);

    ReleaseCalendarController.$inject = ['$rootScope'];
    function ReleaseCalendarController($rootScope) {
        var vm = this;

        vm.user = $rootScope.globals.currentUser.user;

        initController();

        function initController() {
        }
    }

})();