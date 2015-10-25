(function () {
    'use strict';
 
    angular
        .module('releasetracker')
        .factory('TeamService', TeamService);
 
    TeamService.$inject = ['$http'];
    function TeamService($http) {
        var service = {};
        service.GetAllTeams	= GetAllTeams;
        service.AddTeam		= AddTeam;
        service.UpdateTeam	= UpdateTeam;
        service.RemoveTeam	= RemoveTeam;
 
        return service;
 
        function GetAllTeams(request, callback) {
            
        	$http({
        	    url: '/team/v1/getAllTeams',
        	    dataType: 'json',
        	    method: 'POST',
        	    data: request,
        	    headers: {
        	        "Content-Type": "application/json",
        	        "Authorization": "Basic"
        	    }
        	}).success(function(response){
        		callback(response);
        	});
        }
        
        function AddTeam(team, callback) {
        	$http({
        	    url: '/team/v1/addTeam',
        	    dataType: 'json',
        	    method: 'POST',
        	    data: team,
        	    headers: {
        	        "Content-Type": "application/json",
        	        "Authorization": "Basic"
        	    }
        	}).success(function(response){
        		callback(response);
        	});
        }
        
        function UpdateTeam(team, callback) {
        	$http({
        	    url: '/team/v1/updateTeam',
        	    dataType: 'json',
        	    method: 'POST',
        	    data: team,
        	    headers: {
        	        "Content-Type": "application/json",
        	        "Authorization": "Basic"
        	    }
        	}).success(function(response){
        		callback(response);
        	});
        }
        
        function RemoveTeam(team, callback) {
        	$http({
        	    url: '/team/v1/removeTeam',
        	    dataType: 'json',
        	    method: 'POST',
        	    data: team,
        	    headers: {
        	        "Content-Type": "application/json",
        	        "Authorization": "Basic"
        	    }
        	}).success(function(response){
        		callback(response);
        	});
        }

        // private functions
 
        function handleSuccess(res) {
            return res.data;
        }
 
        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }
})();
