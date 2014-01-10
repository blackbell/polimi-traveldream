'use strict';

travelDreamApp.controller('menuController', function($scope, $location) {
    $scope.getActive = function(viewLocation) {
        return $location.path() === viewLocation;
    };
    $scope.pullRight = true;
    
    
});