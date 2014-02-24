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
        } else {
            $scope.PBdaSalvare = $rootScope.PBdaModif;
            if ($scope.PBdaSalvare.tipo === 'Volo')
                $scope.EDB = $scope.PBdaSalvare.rotta;
            if ($scope.PBdaSalvare.tipo === 'Soggiorno')
                $scope.EDB = $scope.PBdaSalvare.albergo;
            if ($scope.PBdaSalvare.tipo === 'Visita')
                $scope.EDB = $scope.PBdaSalvare.museo;
            delete $rootScope.PBdaModif;
        }
    };
    var isComplete = function(pb) {
        if (pb.tipo === 'Volo') {
            if (!pb.dataOra){
                toastr.warning("Inserire una data");
                return false;
            };
            if (!pb.costo){
                toastr.warning("Inserire un costo");
                return false;
            };
        };
        if (pb.tipo === 'Visita') {
            if (!pb.dataOra){
                toastr.warning("Inserire una data");
                return false;
            };
            if (!pb.costo){
                toastr.warning("Inserire un costo");
                return false;
            };
        };
        if (pb.tipo === 'Soggiorno') {
            if (!pb.giornoInizio){
                toastr.warning("Inserire una data d'inizio soggiorno");
                return false;
            };
            if (!pb.giornoInizio){
                toastr.warning("Inserire una data di fine soggiorno");
                return false;
            };
            if (!pb.numeroPersone){
                toastr.warning("Inserire il numero di persone");
                return false;
            };
            if (!pb.costo){
                toastr.warning("Inserire un costo");
                return false;
            };
        };
        
        return true;
    };
    $scope.salvaPB = function() {
        if (isComplete($scope.PBdaSalvare)) {
            gestioneOffertaService.salvaPB($scope.PBdaSalvare, function(esito) {
                if (esito.result) {
                    toastr.success("pb salvato correttamente", esito.message);
                } else
                    toastr.error(esito.message, "ERRORE:");
                delete $rootScope.tipoPBdaCreare;
                delete $rootScope.EDBperPB;
                $scope.dismiss();
                console.log("EDBperPB eliminato? " + $rootScope.EDBperPB);
            });
        };
    };
});


