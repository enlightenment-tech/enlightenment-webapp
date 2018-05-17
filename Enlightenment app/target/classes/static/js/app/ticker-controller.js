app.controller('ticker-controller', function($scope, $http, $compile, $interval) {
	var vm = this;
	vm.submit = function() {
		$http({
			method : "POST",
			url : "/login",
		data: $.param({email: vm.email, password: vm.password}),
	    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		}).then(function (response) {
			$('#body').html($compile(response.data)($scope));
		}, function (response) {
			alert('login error');
		});
	}
	vm.startKrakenTicker = function() {
			$http({
				method : "GET",
				url : "/krakenticker"
			}).then(function mySuccess(response) {
				$scope.ticker = response.data;
			}, function myError(response) {
				$scope.myWelcome = response.statusText;
			});
	}
});