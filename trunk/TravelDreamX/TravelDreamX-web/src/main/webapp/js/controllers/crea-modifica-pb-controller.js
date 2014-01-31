/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('creaModificaPBController', function($scope, $rootScope) {
    toastr.options = {
        positionClass: "toast-center"
    };
    
    $scope.initCreaModificaPB = function(){
        console.log($rootScope.EDBperPB);
        $scope.EDB = $rootScope.EDBperPB;
        $scope.PBdaSalvare = $rootScope.tipoPBdaCreare;
        if($scope.PBdaSalvare.tipo === 'Volo')
            $scope.PBdaSalvare['Rotta'] = $scope.EDB;
        if($scope.PBdaSalvare.tipo === 'Soggiorno')
            $scope.PBdaSalvare['Albergo'] = $scope.EDB;
        if($scope.PBdaSalvare.tipo === 'Visita')
            $scope.PBdaSalvare['Museo'] = $scope.EDB;
        delete $rootScope.EDBperPB;
        delete $rootScope.tipoPBdaCreare;
    };
});


