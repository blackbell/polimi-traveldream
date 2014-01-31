/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('menuController', function($scope, $location) {
    $scope.getActive = function(viewLocation) {
        return $location.path() === viewLocation;
    };
});