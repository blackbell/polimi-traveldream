/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';
var miavar;
travelDreamApp.controller('menuController', function($scope, $rootScope, $location) {
    $scope.beforeCreaViaggio = function(){
        console.log('menuController.beforeCreaViaggio()');
        miavar = $rootScope;
        delete $rootScope.PV;
    };
    
    $scope.getActive = function(viewLocation) {
        return $location.path() === viewLocation;
    };
    
});