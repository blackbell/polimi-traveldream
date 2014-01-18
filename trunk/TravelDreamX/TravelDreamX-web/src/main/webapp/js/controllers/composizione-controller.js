'use strict';
travelDreamApp.controller('composizioneController', function($scope, $rootScope, searchService) {
    //****************************
    //***** Inizializzazione *****
    //****************************
    toastr.options = {
        positionClass: "toast-center"
    };

    $scope.inizializzaRicerca = function() {
        $scope.PB = new Object();
        $scope.trovaPBParams = {
            tipo: ''
        };
        $scope.waiting = false;
    };


    $scope.getPVdaRootScope = function() {
        if (typeof $rootScope.PV === 'undefined') {
            inizializzaPV();
        }
        ;
    };
    var inizializzaPV = function() {
        $rootScope.PV = {
            voci: [
                {
                    tipo: 'Volo'
                },
                {
                    tipo: 'Soggiorno'
                },
                {
                    tipo: 'Visita'
                }
            ]
        };
        $rootScope.indiceSelezionato = -1;
    };

    $scope.getVociPV = function() {
        return $rootScope.PV.voci;
    };
    //**********************************       
    //***** Lista voci - SELEZIONE *****
    //**********************************       

    $scope.isSelezionato = function(data) {
        if (typeof data === 'number')
            return $rootScope.indiceSelezionato === data;
        else
            return $scope.tipoVoceSelezionata === data;
    };
    $scope.seleziona = function(tipo, indice) {
        $scope.tipoVoceSelezionata = tipo;
        $scope.trovaPBParams.tipo = tipo;
        $rootScope.indiceSelezionato = indice;
    };

    $scope.deseleziona = function(indice) {
        if (indice === $scope.indiceSelezionato) {
            $scope.tipoVoceSelezionata = 'nonSelezionato';
            $rootScope.indiceSelezionato = -1;
        } else {
            $rootScope.indiceSelezionato -= 1;
        }
        ;
    };

    $scope.isCompleta = function(voce) {
        //console.log(voce.tipo + " " + (typeof voce.costo !== 'undefined') + " costo " + voce.costo);
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

    //**************************************        
    //***** Lista voci - GESTIONE VOCI *****
    //**************************************        

    $scope.eliminaVoce = function(indice) {
        if (indice > -1) {
            $scope.deseleziona(indice);
            toastr.success("Hai eliminato la voce: " + $rootScope.PV.voci[indice].tipo + ".");
            $rootScope.PV.voci.splice(indice, 1);
        }
    };

    $scope.creaVoce = function(tipo) {
        var voce = {
            tipo: tipo
        };
        var aggiungiInTesta = function(voce) {
            $rootScope.PV.voci.unshift(voce);
            if ($rootScope.indiceSelezionato !== -1)
                $rootScope.indiceSelezionato += 1;
        };
        aggiungiInTesta(voce);
        toastr.success("Hai aggiunto la voce: " + tipo + ".");
        console.log($rootScope.PV.voci);
    };

    //***** RICERCA *****
    $scope.trovaPB = function() {
        $scope.waiting = true;
        searchService.trovaPB($scope.trovaPBParams, function(esito) {
            if (esito.result) {
                $scope.PB[$scope.trovaPBParams.tipo] = esito.returnedObj;
//                $scope.voli = esito.returnedObj;
            } else
                toastr.error(esito.message, "ERRORE:");
            $scope.waiting = false;
        });
    };

    $scope.aggiungiPBaPV = function(PB) {
        $rootScope.aggiungiPBaPV(PB);
    };

    $rootScope.aggiungiPBaPV = function(PB) {
        $rootScope.PV.voci[$rootScope.indiceSelezionato] = PB;
        toastr.success($rootScope.PV.voci[$rootScope.indiceSelezionato].tipo + ' aggiunto al pacchetto viaggio.');
        console.log($rootScope.PV.voci);
    };

    $scope.numStelle = 3;
    $scope.getNumStelle = function(num) {
        $scope.numStelleVuote = 5 - num;
        return new Array(num);
    };
    $scope.aumentaStelle = function(indice) {
        $scope.numStelle = $scope.numStelle + indice + 1;
        console.log($scope.numStelle);
        console.log($scope.numStelleVuote);
    };
    $scope.diminuisciStelle = function(indice) {
        $scope.numStelle = indice + 1;
        console.log($scope.numStelle);
        console.log($scope.numStelleVuote);
    };
    
});