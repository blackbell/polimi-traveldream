/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use-strict';
travelDreamApp.controller('pbModalController', function($scope, $rootScope, $location) {
    
    $scope.aggiungiPBaPV = function (PB){
        $rootScope.aggiungiPBaPV(PB);
        $scope.dismiss();
    };
    $scope.isLocation = function (view){
         console.log('$location.path()');
        console.log($location.path());
        console.log('view');
        console.log(view);
        return $location.path().indexOf(view) !== -1;
    };
});
