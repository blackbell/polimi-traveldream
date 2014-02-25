/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('amministrazioneController', function($scope, $rootScope, $modal, amministrazioneService) {
    toastr.options = {
        positionClass: "toast-center"
    };
    $scope.livelloSelezionato = -1;
    $scope.listaUtenti = new Object();
    $scope.listaPagamenti = new Object();
    $scope.waiting = false;
    $scope.parametriRicercaUtenti = {
        email: new String(),
        idPagamento: -1
    };
    $scope.parametriRicercaPagamenti = {
        pagatoDa: null,
        pagatoA: null,
        utente: null,
        PV: null
    };
    $scope.tooltipDisattiva = {
        title: "Clicca per disabilitare l'utente"
    };
    $scope.tooltipAbilita = {
        title: "Clicca per attivare l'utente"
    };
    $scope.tooltipAmministratore = {
        title: "Amministratore"
    };
    $scope.tooltipImpiegato = {
        title: "Impiegato"
    };
    $scope.tooltipUtenteRegistrato = {
        title: "Utente registrato"
    };
    $scope.recuperaUtenti = function() {
        $scope.waiting = true;
        amministrazioneService.recuperaUtenti( function(esito) {
            if (esito.result) {
                $scope.listaUtenti = esito.returnedObj;
                $scope.waiting = false;
            } else{
                toastr.error(esito.message, "ERRORE:");
                $scope.waiting = false;
            }
        });
    };
       
    $scope.recuperaPagamentiUtente = function(utenteXX) {
        $scope.waiting = true;
        $scope.parametriRicercaPagamenti.utente = utenteXX;
        amministrazioneService.recuperaPagamenti($scope.parametriRicercaPagamenti, function(esito) {
            if (esito.result) {
                $rootScope.pagamenti = { 
                    listaPagamenti: esito.returnedObj,
                    utente:  utenteXX.email
                    //utente: $scope.parametriRicercaPagamenti.utente.email
                };
                apriModalePagamentiUtente();
                $scope.waiting = false;
            } else {
                if(esito.message === "OPERATION_FAILED")
                    toastr.warning("Non ci sono pagamenti per l'utente " + $scope.parametriRicercaPagamenti.utente.email);
                else
                    toastr.error(esito.message, "ERRORE:");
                $scope.waiting = false;
            }
        });
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
    $scope.disattivaUtente = function(utente) {
        $scope.waiting = true;
        amministrazioneService.disattivaUtente(utente, function(esito) {
            if (esito.result) {
                for( var i=0; i<$scope.listaUtenti.length; i++){
                    if($scope.listaUtenti[i].email === utente.email){
                        $scope.listaUtenti[i].abilitato = false;
                        break;
                    }
                };
                toastr.success("l'utente " + utente.email + " è stato disabilitato.", "SUCCESSO:");
                $scope.waiting = false;
            } else {
                toastr.error(esito.message, "ERRORE:");
                $scope.waiting = false;
        }
        });
    };
    $scope.abilitaUtente = function(utente) {
        $scope.waiting = true;
        amministrazioneService.abilitaUtente(utente, function(esito) {
            if (esito.result) {
                for( var i=0; i<$scope.listaUtenti.length; i++){
                    if($scope.listaUtenti[i].email === utente.email){
                        $scope.listaUtenti[i].abilitato = true;
                        break;
                    }
                };
                toastr.success("l'utente " + utente.email + " è stato abilitato.", "SUCCESSO:");
                $scope.waiting = false;
            } else {
                toastr.error(esito.message, "ERRORE:");
                $scope.waiting = false;
        }
        });
    };
    $scope.modificaLivelloUtente = function (utente, livelloSelezionato) {
        if (typeof livelloSelezionato !== 'undefined'){
            utente.livello = livelloSelezionato;
            amministrazioneService.modificaLivelloUtente(utente, function(esito) {
            if (esito.result) {
                toastr.success("il livello dell'utente " + utente.email + " è stato cambiato in " + utente.livello, "SUCCESSO:");
                $scope.waiting = false;
            } else {
                toastr.error(esito.message, "ERRORE:");
                $scope.waiting = false;
            }
            $scope.livelloSelezionato = -1;
            $scope.dismiss();
        });
        }else {
            toastr.error("la modifica NON è stata apportata", "Non hai selezionato nessun livello:");
        }
    };
});

