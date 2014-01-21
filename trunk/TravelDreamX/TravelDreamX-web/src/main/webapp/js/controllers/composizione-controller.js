'use strict';
travelDreamApp.controller('composizioneController', function($scope, $rootScope, $route, $location, $modal, searchService, salvaPVPBservice) {
    //****************************
    //***** Inizializzazione *****
    //****************************
    toastr.options = {
        positionClass: "toast-center"
    };

    $scope.inizializzaRicerca = function() {
        $scope.PB = new Object();
        $scope.waiting = false;
        $scope.parametriRicercaPB = {
            tipo: new String(),
            nomeAlbergo: new String(),
            nomeMuseo: new String(),
            cittaAlbergo: new String(),
            cittaMuseo: new String(),
            cittaPartenzaVolo: new String(),
            cittaArrivoVolo: new String(),
            dataInizioSoggiorno: new Date(),
            dataOraVolo: new Date(),
            dataFineSoggiorno: new Date(),
            giornoVisita: new Date()
        };
    };


    $scope.getPVdaRootScope = function() {
        if (typeof $rootScope.PV === 'undefined') {
            inizializzaPV();
        }
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
        $scope.parametriRicercaPB.tipo = tipo;
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
    $scope.popUpModal = function(modal) {
        // do something
        $modal(modal);
    };

    $scope.eliminaVoce = function(indice) {
        if (indice > -1) {
            $scope.deseleziona(indice);
            $rootScope.PV.voci.splice(indice, 1);
            toastr.success("Hai eliminato la voce: " + $rootScope.PV.voci[indice].tipo + ".");
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
        searchService.trovaPB($scope.parametriRicercaPB, function(esito) {
            if (esito.result) {
                $scope.PB[$scope.parametriRicercaPB.tipo] = esito.returnedObj;
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

    //***** SALVATAGGIO *****
    var escludiVociVuote = function(PV) {
        for (var i = 0; i < PV.voci.length; i++) {
            if (typeof PV.voci[i].costo === 'undefined') {
                PV.voci.splice(i, 1);
                escludiVociVuote(PV);
                break;
            }
        }
        $rootScope.PV = PV;
        console.log("PV da inoltrare con voci vuote escluse");
        console.log(PV);
        return PV;
    };
    var apriModaleCondivisione = function() {
        var modaleCondivisione = {
            template: 'templates/modal/condivisione.html',
            show: true,
            backdrop: 'static'
//                scope: $scope
        };
        $scope.popUpModal(modaleCondivisione);
    };
    $scope.salvaPV = function() {

        if (typeof $rootScope.utente !== 'undefined') {
            $rootScope.PV.proprietario = $rootScope.utente;

            // @TEST: controllo sicurezza lato Server 
            // PVdaSalvare.proprietario.email = 'emailFuffa';

            salvaPVPBservice.salvaPV(escludiVociVuote($rootScope.PV), function(esito) {
                if (esito.result) {
                    console.log("ESITO:");
                    console.log(esito.returnedObj);
                    $rootScope.PV = esito.returnedObj;
                    $rootScope.linkCondivisione = "http://localhost:8888/TravelDreamX-web/sharedPVid:" + $rootScope.PV.idPacchetto;
                    apriModaleCondivisione();
                    toastr.success("Puoi consultare il PV salvato dal menu utente", esito.message);
                } else
                    toastr.error(esito.message, "ERRORE:");
            });
        } else {
            toastr.error("Effettuare il Login o la Registrazione prima di procedere", "ERRORE:");
        }
        ;
    };
});