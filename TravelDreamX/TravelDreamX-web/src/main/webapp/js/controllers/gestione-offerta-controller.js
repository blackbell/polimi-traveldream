/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('gestioneOffertaController', function($scope, $rootScope,$modal, gestioneOffertaService, searchService) {
    toastr.options = {
        positionClass: "toast-center"
    };

    var mettiPVinRootScope = function(isAbilitato) {
        $rootScope.PV.abilitato = isAbilitato;
    };
    $scope.inizializzaRicercaEDB = function() {
        $scope.PBdaCreare = {
            tipo:''
        };
        $scope.EDB = new Object();
        $scope.waiting = false;
        $scope.parametriRicercaEDB = {
            nome: null,
            citta: null,
            stelle: null,
            aeroportoPartenza: null,
            cittaPartenza: null,
            nazionePartenza: null,
            aeroportoArrivo: null,
            cittaArrivo: null,
            nazioneArrivo: null,
            compagniaAerea: null,
            tipo: null
        };
    };
    $scope.tooltipDisattiva = {
        title: "Clicca per disabilitare il PB"
    };
    $scope.tooltipAbilita = {
        title: "Clicca per attivare il PB"
    };
    $scope.selezionaTipoNuovoPB = function(tipo) {
        $scope.PBdaCreare.tipo = tipo;
        if(tipo==='Soggiorno')
            $scope.parametriRicercaEDB.tipo = 'Albergo';
        if(tipo==='Volo')
            $scope.parametriRicercaEDB.tipo = 'Rotta';
        if(tipo==='Visita')
            $scope.parametriRicercaEDB.tipo = 'Museo';
    };

    $scope.abilitaPV = function(pv) {
        gestioneOffertaService.abilitaPV(pv.idPacchetto, function(esito) {
            if (esito.result) {
                mettiPVinRootScope(esito.returnedObj);
                toastr.success("pacchetto " + pv.idPacchetto + " abilitato.", esito.message);
            } else {
                toastr.error(esito.message, "ERRORE:");
            }

        });
    };
    $scope.disabilitaPV = function(pv) {
        gestioneOffertaService.disabilitaPV(pv.idPacchetto, function(esito) {
            if (esito.result) {
                mettiPVinRootScope(esito.returnedObj);
                toastr.success("pacchetto " + pv.idPacchetto + " disabilitato.", esito.message);
            } else {
                toastr.error(esito.message, "ERRORE:");
            }

        });
    };
    $scope.abilitaPB = function(pb) {
        gestioneOffertaService.abilitaPB(pb.idVoce, function(esito) {
            if (esito.result) {
                toastr.success("pb " + pb.idVoce + " abilitato.", esito.message);
            } else {
                toastr.error(esito.message, "ERRORE:");
            }

        });
    };
    $scope.disabilitaPB = function(pb) {
        gestioneOffertaService.disabilitaPB(pb.idVoce, function(esito) {
            if (esito.result) {
                toastr.success("pb " + pb.idVoce + " disabilitato.", esito.message);
            } else {
                toastr.error(esito.message, "ERRORE:");
            }

        });
    };
    $scope.trovaEDB = function() {
        $scope.waiting = true;
        searchService.trovaEDB($scope.parametriRicercaEDB, function(esito) {
            if (esito.result) {
                $scope.EDB[$scope.parametriRicercaEDB.tipo] = esito.returnedObj;
            } else {
                toastr.error(esito.message, "ERRORE:");
            }
            $scope.waiting = false;
        });
    };
    var apriModaleCreaModificaPB = function() {
        var modaleCreaModificaPB = {
            template: 'templates/modal/creaModificaPB.html',
            show: true,
            backdrop: 'static'
        };
        var popUpModal = function(modal) {
            // do something
            $modal(modal);
        };
        popUpModal(modaleCreaModificaPB);
    };
    $scope.scegliEDB = function(edb){
        console.log(edb);
        $rootScope.EDBperPB = edb;
        $rootScope.tipoPBdaCreare = $scope.PBdaCreare;
        apriModaleCreaModificaPB();
    };
});
