(function () {
    'use strict';

    angular
        .module('releasetracker')
        .controller('WorkflowController', WorkflowController);

    WorkflowController.$inject = ['$rootScope'];
    function WorkflowController($rootScope) {
        var vm = this;

        vm.user = $rootScope.globals.currentUser.user;

        initController();

        function initController() {
        }
    }

})();