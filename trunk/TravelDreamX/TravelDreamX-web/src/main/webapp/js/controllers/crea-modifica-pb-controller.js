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
        console.log($rootScope.EDBperPB);
        $scope.EDB = $rootScope.EDBperPB;
        $scope.PBdaSalvare = $rootScope.tipoPBdaCreare;
        if ($scope.PBdaSalvare.tipo === 'Volo')
            $scope.PBdaSalvare['rotta'] = $scope.EDB;
        if ($scope.PBdaSalvare.tipo === 'Soggiorno')
            $scope.PBdaSalvare['albergo'] = $scope.EDB;
        if ($scope.PBdaSalvare.tipo === 'Visita')
            $scope.PBdaSalvare['museo'] = $scope.EDB;
    };
    $scope.salvaPB = function() {
        gestioneOffertaService.salvaPB($scope.PBdaSalvare, function(esito) {
            if (esito.result) {
                toastr.success("pb salvato correttamente", esito.message);
            } else
                toastr.error(esito.message, "ERRORE:");
            delete $rootScope.tipoPBdaCreare;
            delete $rootScope.EDBperPB;
            console.log("EDBperPB eliminato? "+$rootScope.EDBperPB);
        });
    };
});


