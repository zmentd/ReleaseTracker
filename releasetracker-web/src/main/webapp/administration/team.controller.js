(function () {
    'use strict';

    angular
        .module('releasetracker')
        .controller('TeamController', TeamController);

    TeamController.$inject = ['TeamService', '$location', '$rootScope', 'FlashService', '$scope'];
    function TeamController(TeamService, $location, $rootScope, FlashService, $scope) {
        var vm = this;
        vm.getAllTeams = getAllTeams();
 
        (function initController() {
        })();

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
    
        $scope.checkName = function(data, id) {
        	if (id === 2 && data !== 'awesome') {
        		return "Username 2 should be `awesome`";
        	}
        };

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
        	if( !persisted ){
        		var why = "why";
        	}
        	return persisted;
        }; 
    }

})();