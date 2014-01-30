/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';
travelDreamApp.controller('condivisioneController', function($scope, $rootScope) {
    toastr.options = {
        positionClass: "toast-center"
    };
    
    $scope.emails = new Array(new Object());
    $scope.getLinkCondivisione = function() {
        if (typeof $rootScope.linkCondivisione !== 'undefined')
            $scope.linkCondivisione = $rootScope.linkCondivisione;
    };
});


