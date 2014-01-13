'use strict';
travelDreamApp.controller('composizioneController', function($scope, $rootScope, searchService) {
    //****************************
    //***** Inizializzazione *****
    //****************************        
    $scope.alerts = [];
    $scope.getPVdaRootScope = function() {
        $scope.PV = $rootScope.PV;
        if (typeof $scope.PV === 'undefined') {
            inizializzaPV();
        }
        ;
    };
    var inizializzaPV = function() {
        $scope.PV = {
            voci: [
                {
                    tipo: 'Volo'
//                    costo: 1000.99,
//                    dataOra: '22-feb-2014 10:25',
//                    rotta: {
//                        cittaPartenza: 'Milano',
//                        cittaArrivo: 'Amsterdam'
//                    }
                },
                {
                    tipo: 'Soggiorno'
                },
                {
                    tipo: 'Visita'
                }
            ]
        };
    };

    //**********************************       
    //***** Lista voci - SELEZIONE *****
    //**********************************       

    $scope.isSelezionato = function(data) {
        if (typeof data === 'number')
            return $scope.indiceSelezionato === data;
        else
            return $scope.tipoVoceSelezionata === data;
    };
    $scope.seleziona = function(tipo, indice) {
        $scope.tipoVoceSelezionata = tipo;
        $scope.indiceSelezionato = indice;
    };

    $scope.deseleziona = function(indice) {
        if (indice === $scope.indiceSelezionato) {
            $scope.tipoVoceSelezionata = 'nonSelezionato';
            $scope.indiceSelezionato = -1;
        }
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
            var alert = {
                "type": "warning",
                "title": "",
                "tipo": $scope.PV.voci[indice].tipo,
                "content": "Hai eliminato una voce di tipo {{alert.tipo}}."
            };
            $scope.alerts.push(alert);
            $scope.deseleziona(indice);
            $scope.PV.voci.splice(indice, 1);
        }
    };

    $scope.creaVoce = function(tipo) {
        var voce = {
            tipo: tipo
        };
        var alert = {
            "type": "success",
            "title": "",
            "tipo": tipo,
            "content": "Hai aggiunto la voce: {{alert.tipo}}."
        };
        $scope.alerts.push(alert);
        $scope.PV.voci.push(voce);
        console.log($scope.PV.voci);
    };

    //***** RICERCA *****
    $scope.trovaPB = function() {
        searchService.trovaPB( function (esito){
            if (esito.result) {
            $scope.voli = esito.returnedObj;
        } else
            toastr.error(esito.message, "ERRORE:");
        });
    };

    $scope.aggiungiPBaPV = function(PB) {
        $scope.PV.voci[$scope.indiceSelezionato] = PB;
        toastr.success( $scope.PV.voci[$scope.indiceSelezionato].tipo + ' aggiunto al pacchetto viaggio.');
        console.log($scope.PV.voci);
    };
    
    $scope.numStelle = 3;
    $scope.getNumStelle = function(num) {
        $scope.numStelleVuote = 5 - num;
        return new Array(num);   
    };
    $scope.aumentaStelle = function (indice){
        $scope.numStelle = $scope.numStelle +indice +1;
        console.log($scope.numStelle);
        console.log($scope.numStelleVuote);
    };
    $scope.diminuisciStelle = function (indice){
        $scope.numStelle = indice +1;
        console.log($scope.numStelle);
        console.log($scope.numStelleVuote);
    };
    $scope.soggiorni = [
        { 
            costo: 100.00,
            giornoInizio: new Date(),
            giornoFine: new Date(),
            numeroPersone: 2,
            albergo: {
                nome: 'Ciccio',
                citta: 'Meda',
                stelle: 3,
                urlFoto: 'img/Soggiorno.png'
            }
        },
        { 
            costo: 200.00,
            giornoInizio: new Date(),
            giornoFine: new Date(),
            numeroPersone: 2,
            albergo: {
                nome: 'Puccio',
                citta: 'Pistoia',
                stelle: 5,
                urlFoto: 'img/Soggiorno.png'
            }
        },
        { 
            costo: 300.00,
            giornoInizio: new Date(),
            giornoFine: new Date(),
            numeroPersone: 2,
            albergo: {
                nome: 'Ciccione Barabbazzo Hotel casinas',
                citta: 'Vernasca',
                stelle: 4,
                urlFoto: 'img/Soggiorno.png'
            }
        }
    ];
//    $scope.initRicercaVolo = function() {
//        $scope.ricercaParams = {
//            tipo: $scope.tipoVoceSelezionata,
//            rotta: {aeroportoPartenza: '',
//                cittaPartenza: '',
//                nazionePartenza: '',
//                aeroportoArrivo: '',
//                cittaArrivo: '',
//                nazioneArrivo: '',
//                compagniaAerea: ''
//            },
//            costo: 0,
//            dataOra: ''
//        };
//    };
});