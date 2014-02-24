/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('pagamentiUtenteController', function($scope, $rootScope, $modal, amministrazioneService) {
    toastr.options = {
        positionClass: "toast-center"
    };
    $scope.getPagamentidaRootScope = function (){
        $scope.pagamenti = $rootScope.pagamenti;
        delete $rootScope.pagamenti;
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
    
    var apriModalePagamentiUtente = function() {
        var modalePagamentiUtente = {
            template: 'templates/modal/pagamentiUtente.html',
            show: true,
            backdrop: 'static'
        };
        var popUpModal = function(modal) {
            $modal(modal);
        };
        popUpModal(modalePagamentiUtente);
    };
    
    var parametriRicercaPagamenti = new Object();
    $scope.recuperaPagamenti = function() {
        $scope.waiting = true;
        parametriRicercaPagamenti.utente = $rootScope.utente;
        amministrazioneService.recuperaPagamenti(parametriRicercaPagamenti, function(esito) {
            if (esito.result) {
                $rootScope.pagamenti = { 
                    listaPagamenti: esito.returnedObj,
                    utente: parametriRicercaPagamenti.utente.email
                };
                apriModalePagamentiUtente();
                $scope.waiting = false;
            } else {
                if(esito.message === "OPERATION_FAILED")
                    toastr.warning("Non ci sono pagamenti per l'utente " + parametriRicercaPagamenti.utente.email);
                else
                    toastr.error(esito.message, "ERRORE:");
                $scope.waiting = false;
            }
        });
    };
});

