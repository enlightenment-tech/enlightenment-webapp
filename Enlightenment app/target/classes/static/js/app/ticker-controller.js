app.controller('ticker-controller', function($stomp, $scope) {

	$scope.myres = [];

	$stomp.connect('/ticker-data', {}).then(
			function(frame) {
				var subscription = $stomp.subscribe('/topic/myticker',
						function(payload, headers, res) {
							$scope.myres = payload;
							$scope.$apply($scope.myres);
						});

				$stomp.send('/app/ticker', '');
			});
});