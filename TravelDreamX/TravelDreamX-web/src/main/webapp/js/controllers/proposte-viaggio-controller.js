/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('proposteViaggioController', function($scope, searchService) {
    
    $scope.waiting = false;
    $scope.parametriRicercaPV = {
        idPacchetto: null,
        nome: null,
        giornoInizio: new Date(),
        giornoFine: new Date(new Date().getTime() + (24 * 60 * 60 * 1000 * 7)),
        cittaAlbergo: null,
        nazionePartenza: null,
        nazioneArrivo: null
    };
    $scope.isPresente = function(pv, tipo) {
        for (var index = 0; index < pv.voci.length; ++index) {
            if (pv.voci[index].tipo === tipo)
                return true;
        }
        return false;
    };

    $scope.calcolaPrezzo = function(pv) {
        var prezzoTotale = 0;
        for (var index = 0; index < pv.voci.length; ++index) {
            prezzoTotale += pv.voci[index].costo;
        }
        return prezzoTotale;
    };
    
    $scope.tooltipVolo = {
        title: 'Volo'
    };
    $scope.tooltipSoggiorno = {
        title: 'Soggiorno'
    };
    $scope.tooltipVisita = {
        title: 'Visita'
    };
    $scope.getFotoPV = function (pv) {
        for(var i=0; i< pv.voci.length; i++){
            if(pv.voci[i].tipo === 'Soggiorno'){
                $scope.fotoPV = pv.voci[i].albergo.urlFoto;
                break;
            }
        };
    };
    $scope.trovaPV = function() {
        $scope.waiting=true;
        searchService.trovaPV($scope.parametriRicercaPV, function(esito) {
            if (esito.result) {
                $scope.PVs = esito.returnedObj;
            } else {
                toastr.error(esito.message, "ERRORE:");
            }
            $scope.waiting = false;
        });
    };
    
});


