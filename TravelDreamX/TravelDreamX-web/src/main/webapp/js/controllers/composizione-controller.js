'use strict';
travelDreamApp.controller('composizioneController', function($scope, $rootScope) {
    //****************************
    //***** Inizializzazione *****
    //****************************        

    $scope.getPVdaRootScope = function() {
        $scope.PV = $rootScope.PV;
        if (typeof $scope.PV === 'undefined') {
            inizializzaPV();
        }
        ;
    };
    var inizializzaPV = function() {
        $scope.PV = {
            composizioneCollection: [
                {
                    voce: {
                        tipo: 'volo',
                        costo: 1000.99,
                        dataOra: '22-feb-2014 10:25',
                        rotta: {
                            cittaPartenza: 'Milano',
                            cittaArrivo: 'Amsterdam'
                        }
                    }
                },
                {voce: {
                        tipo: 'soggiorno'
                    }
                },
                {voce: {
                        tipo: 'visita'
                    }
                }
            ]
        };
    };
    
    //**********************************       
    //***** Lista voci - SELEZIONE *****
    //**********************************       

    $scope.isSelezionato = function(data) {
        if (typeof data === 'number')
            return $scope.indice === data;
        else
            return $scope.selezionato === data;
    };
    $scope.seleziona = function(tipo, indice) {
        $scope.selezionato = tipo;
        $scope.indice = indice;
    };

    $scope.deseleziona = function(indice) {
        if (indice === $scope.indice) {
            $scope.selezionato = 'nonSelezionato';
            $scope.indice = -1;
        }
    };

    $scope.isCompleta = function(voce) {
        //console.log(voce.tipo + " " + (typeof voce.costo !== 'undefined') + " costo " + voce.costo);
        return (typeof voce.costo !== 'undefined');
    };

    $scope.isVolo = function(voce) {
        return (($scope.isCompleta(voce)) && (voce.tipo === 'volo'));
    };

    $scope.isSoggiorno = function(voce) {
        return (($scope.isCompleta(voce)) && (voce.tipo === 'soggiorno'));
    };

    $scope.isVisita = function(voce) {
        return (($scope.isCompleta(voce)) && (voce.tipo === 'visita'));
    };

    //*********************************        
    //***** Lista voci - GESTIONE *****
    //*********************************        

    $scope.eliminaVoce = function(indice) {
        $scope.deseleziona(indice);
        if (indice > -1) {
            $scope.PV.composizioneCollection.splice(indice, 1);
        }
    };

    $scope.creaVoce = function(tipo) {
        var composizione = {
            voce: {
                tipo: tipo
            }
        };

        $scope.PV.composizioneCollection.push(composizione);
        console.log($scope.PV.composizioneCollection);
    };


});