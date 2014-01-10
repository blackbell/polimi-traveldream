'use strict';
travelDreamApp.controller('composizioneController', function($scope, $rootScope) {
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
    $scope.isSelezionato = function (tipo){
        return $scope.selezionato === tipo;
    };
    $scope.seleziona = function (tipo){
        $scope.selezionato = tipo;
    };
    
    $scope.isScelto = function(voce) {
        //console.log(voce.tipo + " " + (typeof voce.costo !== 'undefined') + " costo " + voce.costo);
        return (typeof voce.costo !== 'undefined');
    };
    $scope.isVolo = function(voce) {
        return (($scope.isScelto(voce)) && (voce.tipo === 'volo'));
    };

    $scope.eliminaVoce = function(indice) {
        $scope.selezionato = 'nonSelezionato';
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