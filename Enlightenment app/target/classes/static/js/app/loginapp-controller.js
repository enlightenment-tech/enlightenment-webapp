app.controller('loginapp-controller', function($scope, $http, $compile,
		$location) {
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
});