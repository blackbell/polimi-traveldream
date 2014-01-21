/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';
travelDreamApp.controller('condivisioneController', function($scope, $rootScope) {
    $scope.getLinkCondivisione = function() {
        if (typeof $rootScope.linkCondivisione !== 'undefined')
            $scope.linkCondivisione = $rootScope.linkCondivisione;
    };
});


