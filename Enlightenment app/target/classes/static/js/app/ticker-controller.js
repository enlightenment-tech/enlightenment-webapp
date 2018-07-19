app.controller('ticker-controller', function($stomp, $scope, $http, $compile,$interval, $window, $location) {
	var vm = this;
	vm.submit = function() {
		$http({
			method : "POST",
			url : "/login",
			data : $.param({
				email : vm.email,
				password : vm.password
			}),
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			}
		}).then(function(response) {
			$('#body').html($compile(response.data)($scope));
			// $window.sessionStorage.setItem('XSRF-TOKEN');
			$location.path('/');
		}, function(response) {
			// $window.sessionStorage.removeItem('XSRF-TOKEN');
			alert('login error');
		});
	}
	
	
	$scope.myres = [];
	  
    $stomp.connect('/ticker-data', {})
          .then(function (frame) {
              var subscription = $stomp.subscribe('/topic/myticker', 
                  function (payload, headers, res) {
                      $scope.myres = payload;
                      $scope.$apply($scope.myres);
//                      var a = payload.ask.price;
              });
          
              $stomp.send('/app/ticker', '');
       });

//	vm.startKrakenTicker = function() {
//
//		$http({
//			method : "GET",
//			url : "/krakenticker"
//		}).then(function mySuccess(response) {
//			$scope.ticker = response.data;
//		}, function myError(response) {
//			$scope.myWelcome = response.statusText;
//		});
//	}
});