angular.module("crudApp").controller("GeneralController", GeneralController);

GeneralController.inject = [ '$scope', 'User' ];

function GeneralController($scope, User) {
	
	$scope.users = User.query();

	$scope.user= {};
	
	$scope.buttonText="Submit";
	
	$scope.saveUser = function() {
		if ($scope.user.id !== undefined) {
			User.update($scope.user, function() {
				$scope.users = User.query();
				$scope.user = {};
				$scope.buttonText="Submit";
			});
		} else {
			User.save($scope.user, function() {
				$scope.users = User.query();
				$scope.user = {};
			});
		}
	}

	$scope.updateUserInit = function(user) {
		$scope.buttonText="Update";
		$scope.user= user;
	}

	$scope.deleteUser = function(user) {
		user.$delete({id: user.id}, function() {
			$scope.users = User.query();
		});
	}
	
	

	
}