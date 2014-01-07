var transitApp = angular.module('transitApp', [])

transitApp.service('busService', ['http', function($http){
	var Bus = {}
	Bus.markers= [],
	Bus.vehicles = []
	Bus.clearMarkers= function(){
		markers=[];
		vehicles = []
	};
	
	Bus.getCurrent= function(){
		$http.get('/transit').success(function(data){
			Bus.buses = data;
		})
		return Bus.data;
	}
	
	return {
		loadData: Bus.loadData,
		getMarkers: Bus.getMarkers(timestamp),
		getCurrent: Bus.getCurrent,
		markers: Bus.markers,
		clearMarkers: clearMarkers
	}
}])

transitApp.controller('homeCtrl', function($scope, busService){
	$scope.changeTime = function(timestamp){
		
	}
	$scope.live = function(){
		$scope.markers = busService.getCurrent();
		$scope.reloadMarkers();
	}
	$scope.buses = [];
	
	$scope.adheree = function(value) {
		var x;
		if(value < 0){
			x = Math.abs(value);
			return x+"mins late";
		} else if(value == 0){
			return "on Time";
		} else
	      return value+"mins early";
	}
	
	$scope.reloadMarkers =  function(){	
		for(var i = 0; i < buses.length; i++) {		
			myLatLng = new google.maps.LatLng(lat, lon);
			marker = new google.maps.Marker({
				position : myLatLng,
				icon : image,
				map : map,
				title : "Bus Number: "+ name,
				zIndex : 1
			});			
		}
	}
})