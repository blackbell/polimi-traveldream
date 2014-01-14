/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use-strict';
travelDreamApp.controller('pbModalController', function($scope, $rootScope) {
    
    $scope.aggiungiPBaPV = function (PB){
        $rootScope.aggiungiPBaPV(PB);
        $scope.dismiss();
    };
});
