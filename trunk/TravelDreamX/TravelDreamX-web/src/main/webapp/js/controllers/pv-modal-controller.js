/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('pvModalController', function($scope, $rootScope, $location) {

    $scope.fwdToPersonalizzaPV = function (pv){
        $rootScope.PV=pv;
        $location.path('/composizionePV');
    };
});

