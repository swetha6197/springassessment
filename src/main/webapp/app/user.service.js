angular.module('crudApp').factory('User', User);

User.$inject = [ '$resource' ];

function User($resource) {
	var resourceUrl = 'api/user/:id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}