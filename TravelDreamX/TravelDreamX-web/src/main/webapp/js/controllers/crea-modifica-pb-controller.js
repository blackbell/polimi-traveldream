/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('creaModificaPBController', function($scope, $rootScope, gestioneOffertaService) {
    toastr.options = {
        positionClass: "toast-center"
    };
    $scope.getNumStelle = function(num) {
        $scope.numStelleVuote = 5 - num;
        return new Array(num);
    };
    $scope.initCreaModificaPB = function() {
        if (typeof $rootScope.EDBperPB !== 'undefined') {
            console.log($rootScope.EDBperPB);
            $scope.EDB = $rootScope.EDBperPB;
            $scope.PBdaSalvare = $rootScope.tipoPBdaCreare;
            if ($scope.PBdaSalvare.tipo === 'Volo')
                $scope.PBdaSalvare['rotta'] = $scope.EDB;
            if ($scope.PBdaSalvare.tipo === 'Soggiorno')
                $scope.PBdaSalvare['albergo'] = $scope.EDB;
            if ($scope.PBdaSalvare.tipo === 'Visita')
                $scope.PBdaSalvare['museo'] = $scope.EDB;
            
            delete $rootScope.tipoPBdaCreare;
            delete $rootScope.EDBperPB;
        }else{
            $scope.PBdaSalvare=$rootScope.PBdaModif;
            if($scope.PBdaSalvare.tipo === 'Volo')
                $scope.EDB = $scope.PBdaSalvare.rotta;
            if($scope.PBdaSalvare.tipo === 'Soggiorno')
                $scope.EDB = $scope.PBdaSalvare.albergo;
            if($scope.PBdaSalvare.tipo === 'Visita')
                $scope.EDB = $scope.PBdaSalvare.museo;
            delete $rootScope.PBdaModif;
        }
    };
    $scope.salvaPB = function() {
        gestioneOffertaService.salvaPB($scope.PBdaSalvare, function(esito) {
            if (esito.result) {
                toastr.success("pb salvato correttamente", esito.message);
            } else
                toastr.error(esito.message, "ERRORE:");
            delete $rootScope.tipoPBdaCreare;
            delete $rootScope.EDBperPB;
            console.log("EDBperPB eliminato? " + $rootScope.EDBperPB);
        });
    };
});


