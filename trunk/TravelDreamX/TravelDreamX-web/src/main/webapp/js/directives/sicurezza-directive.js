/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

//http://blog.brunoscopelliti.com/deal-with-users-authentication-in-an-angularjs-web-app
'use-strict';
travelDreamApp.directive('controllaLivello', ['$rootScope', '$location', function ($root, $location) {
	return {
		link: function (scope, elem, attrs, ctrl) {
			$root.$on('$routeChangeStart', function(event, currRoute, prevRoute){
				if (! ( typeof  currRoute.access.livelloAbilitazione !== 'undefined') 
                                        &&  
                                      ( $root.utente.livello !== currRoute.access.livelloAbilitazione )) {
					$location.path('proposteViaggio');
				}				
			});
		}
	};
}]);

