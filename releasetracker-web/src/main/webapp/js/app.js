// Define any routes for the app
// Note that this app is a single page app, and each partial is routed to using the URL fragment. For example, to select the 'home' route, the URL is http://localhost:8080/jboss-as-kitchensink-angularjs/#/home
(function () {
    'use strict';
 
    var app = angular
        .module('releasetracker', ['ngRoute', 'ngCookies', 'xeditable'])
        .config(config)
        .run(run);
 
    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/', {
                controller: 'HomeController',
                templateUrl: 'home/home.view.html',
                controllerAs: 'vm',
                access: {
                    restricted: true
                  }
            })
            .when('/releasecalendar', {
                controller: 'ReleaseCalendarController',
                templateUrl: 'releasecalendar/releasecalendar.view.html',
                controllerAs: 'vm',
                access: {
                    restricted: true
                  }
            })
            .when('/team', {
                controller: 'TeamController',
                templateUrl: 'administration/team.view.html',
                controllerAs: 'vm',
                access: {
                    restricted: true
                  }
            })
           .when('/workflow', {
                controller: 'WorkflowController',
                templateUrl: 'administration/workflow.view.html',
                controllerAs: 'vm',
                access: {
                    restricted: true
                  }
            })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'security/login.view.html',
                controllerAs: 'vm',
                access: {
                    restricted: false
                  }
            })
             .when('/logout', {
                controller: 'LogoutController',
                templateUrl: 'security/logout.view.html',
                controllerAs: 'vm',
                access: {
                    restricted: false
                  }
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'security/register.view.html',
                controllerAs: 'vm',
                access: {
                    restricted: false
                  }
            })
 
            .otherwise({ redirectTo: '/login' });
    }
 
    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http', 'editableOptions'];
    function run($rootScope, $location, $cookieStore, $http, editableOptions) {

    	// keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        editableOptions.theme = 'bs2';
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
 
        $rootScope.$on('$routeChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var loggedIn = $rootScope.globals.currentUser;
            if (next.access.restricted && !loggedIn) {
                $location.path('/login');
            }
        });
    }
    
	app.factory("flash", function($rootScope) {
	  var queue = [];
	  var currentMessage = "";

	  $rootScope.$on("$routeChangeSuccess", function() {
	    currentMessage = queue.shift() || "";
	  });

	  return {
	    setMessage: function(message) {
	      queue.push(message);
	    },
	    getMessage: function() {
	      return currentMessage;
	    }
	  };
	});
})();
