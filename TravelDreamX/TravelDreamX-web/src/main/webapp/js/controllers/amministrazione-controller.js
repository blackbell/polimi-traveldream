/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('amministrazioneController', function($scope, amministrazioneService) {
    toastr.options = {
        positionClass: "toast-center"
    };
    $scope.listaUtenti = new Object();
    $scope.listaPagamenti = new Object();
    $scope.waiting = false;
    $scope.parametriRicercaUtenti = {
        email: new String(),
        idPagamento: -1
    };
    $scope.parametriRicercaPagamenti = {
        pagatoDa: new String(),
        pagatoA: new String(),
        utente: new Object(),
        PV: new Object()
    };
    
    $scope.recuperaUtenti = function() {
        $scope.waiting = true;
        amministrazioneService.recuperaUtenti( function(esito) {
            if (esito.result) {
                $scope.listaUtenti = esito.returnedObject;
                $scope.waiting = false;
            } else{
                toastr.success(esito.message, "ERRORE:");
                $scope.waiting = false;
            }
        });
    };
    $scope.recuperaPagamentiUtente = function (utente){
        $scope.parametriRicercaPagamenti.utente = utente;
        $scope.recuperaPagamenti();
    };
    $scope.recuperaPagamenti = function() {
        $scope.waiting = true;
        amministrazioneService.recuperaPagamenti($scope.parametriRicercaUtente, function(esito) {
            if (esito.result) {
                $scope.listaPagamenti = esito.returnedObject;
                $scope.waiting = false;
            } else {
                toastr.success(esito.message, "ERRORE:");
                $scope.waiting = false;
            }
        });
    };
    $scope.disattivaUtente = function(utente) {
        $scope.waiting = true;
        amministrazioneService.disattivaUtente(utente, function(esito) {
            if (esito.result) {
                $scope.listaPagamenti = esito.returnedObject;
                toastr.success("l'utente " + utente.email + " è stato disabilitato.", "SUCCESSO:");
                $scope.waiting = false;
            } else {
                toastr.success(esito.message, "ERRORE:");
                $scope.waiting = false;
        }
        });
    };
});

