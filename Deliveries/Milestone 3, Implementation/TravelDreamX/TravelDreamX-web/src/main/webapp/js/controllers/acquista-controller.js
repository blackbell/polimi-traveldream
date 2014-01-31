/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';
travelDreamApp.controller('acquistaController', function($scope, $rootScope, acquistaService) {

    $scope.getPVdaRootScope = function (){
        $scope.pv = $rootScope.PV;
    };
    $scope.acquistaPV = function (){
        console.log("rootScope.pv:");
        console.log($rootScope.PV);
        acquistaService.acquistaPV($rootScope.PV, function(esito){
            if(esito.result)
                toastr.success("Acquisto avvenuto con successo.");
            else
                toastr.error(esito.message,"ERRORE:");
        });
    };
    $scope.isCompleta = function(voce) {
        return (typeof voce.costo !== 'undefined');
    };
    $scope.isVolo = function(voce) {
        return (($scope.isCompleta(voce)) && (voce.tipo === 'Volo'));
    };

    $scope.isSoggiorno = function(voce) {
        return (($scope.isCompleta(voce)) && (voce.tipo === 'Soggiorno'));
    };

    $scope.isVisita = function(voce) {
        return (($scope.isCompleta(voce)) && (voce.tipo === 'Visita'));
    };
});
