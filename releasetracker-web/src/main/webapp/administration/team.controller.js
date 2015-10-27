//(function () {
//    'use strict';

var app = angular
        .module('releasetracker')
        .controller('TeamController', TeamController);

    TeamController.$inject = ['TeamService', 'UserService', '$controller', '$location', '$rootScope', 'FlashService', '$scope'];
    function TeamController(TeamService, UserService, $controller, $location, $rootScope, FlashService, $scope) {
        var vm 					= this;
        $scope.data = {
        		selectedUser: null,
        };
        vm.getAllTeams 			= getAllTeams();
        
//        var typeaheadScope		= $scope.$new();
//        $controller( 'UserTypeaheadCtrl', { $scope : typeaheadScope } );
//        vm.userTypeaheadCtrl	= UserTypeaheadCtrl;

        function getAllTeams() {
            vm.dataLoading = true;
            var request = {
            		orderBy:"Name",
            		isAsc:"true",
            		countPerPage:"-1",
            		page:"1"
            };
            TeamService.GetAllTeams(request, function (response){
                if (response.success) {
                	vm.teams 	= response.list;
                	vm.count	= response.count;
                	FlashService.Success( response.message );
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
        }

        $scope.setSelectedUser = function( suser, field, team ){
           var user = $scope.data.selectedUser; 
        }

        $scope.onSelect = function( $item, $model, $label ){
           var user = $scope.data.selectedUser; 
        }

        $scope.locatUsers = function( viewValue ){
            var userList	= [];
            vm.dataLoading = true;
            var request = {
            		orderBy:"Name",
            		isAsc:"false",
            		countPerPage:"8",
            		namePrefix:viewValue
            };
            
            userList = UserService.LocateUsers( request );
            
        	return userList;
        }
        
        $scope.addTeam = function( team, index ) {
        	//$scope.user not updated yet
        	//
        	var newteam = team;
        	var request = {
        			team: team
        	}
        	vm.dataLoading = true;
        	TeamService.AddTeam(request, function (response){
        		if (response.success) {
        			newteam	= response.team;
        			getAllTeams();
        		} else {
        			FlashService.Error(response.message);
        			vm.dataLoading = false;
        		}
        	});
        	return newteam;
        };

        $scope.saveTeam = function(team) {
        	//$scope.user not updated yet
        	angular.extend(team, {id: team.id});
        	var newteam = team;
        	var request = {
        			team: team
        	}
        	vm.dataLoading = true;
        	if( newteam.id != null ){
	        	TeamService.UpdateTeam(request, function (response){
	        		if (response.success) {
	        			newteam	= response.team;
	        		} else {
	        			FlashService.Error(response.message);
	        			vm.dataLoading = false;
	        		}
	        	});
        	}
        	return newteam;
        };

        // remove user
        $scope.removeTeam = function(index) {
        	vm.dataLoading = true;
        	var teams  	= vm.teams;
        	var team	= teams[index].team;
        	var request = {
        			team: team
        	}
        	TeamService.RemoveTeam(request, function (response){
        		if (response.success) {
        			vm.teams.splice(index, 1);
        		} else {
        			FlashService.Error(response.message);
        			vm.dataLoading = false;
        		}
        	});
        };

        // add user
        $scope.addTeamRow = function() {
        	$scope.inserted = {
        			id: $scope.count + 1,
        			name: '',
        			obs: '',
        	};
        	vm.teams.push($scope.inserted);
        }; 

        // add user
        $scope.isPersisted = function( team ) {
        	var persisted 	= team != null;
        	persisted		= persisted && team.id != null;
        	return persisted;
        }; 
        $scope.states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Dakota', 'North Carolina', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'];
    }
	

//})();