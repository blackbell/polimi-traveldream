/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('proposteViaggioController', function($scope) {
    $scope.PVs = [
        {
            nome: 'Splendida avventura',
            urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
            voci: [
                {
                    tipo: 'Volo',
                    costo: 120
                },
                {
                    tipo: 'Soggiorno',
                    costo: 137
                },
                {
                    tipo: 'Visita',
                    costo: 37
                }                
            ]
        },
        {    nome: 'Splendida avventura',
            urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
            voci: [
                {
                    tipo: 'Volo',
                    costo: 120
                },
                {
                    tipo: 'Soggiorno',
                    costo: 137
                },
                {
                    tipo: 'Visita',
                    costo: 37
                }                
            ]
        },
        {    nome: 'Splendida avventura',
            urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
            voci: [
                {
                    tipo: 'Volo',
                    costo: 120
                },
                {
                    tipo: 'Soggiorno',
                    costo: 137
                },
                {
                    tipo: 'Visita',
                    costo: 37
                }                
            ]
        },
        {    nome: 'Splendida avventura',
            urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
            voci: [
                {
                    tipo: 'Volo',
                    costo: 120
                },
                {
                    tipo: 'Soggiorno',
                    costo: 137
                },
                {
                    tipo: 'Visita',
                    costo: 37
                }                
            ]
        },
        {    nome: 'Splendida avventura',
            urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
            voci: [
                {
                    tipo: 'Volo',
                    costo: 120
                },
                {
                    tipo: 'Soggiorno',
                    costo: 137
                },
                {
                    tipo: 'Visita',
                    costo: 37
                }                
            ]
        },
        {    nome: 'Splendida avventura',
            urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
            voci: [
                {
                    tipo: 'Volo',
                    costo: 120
                },
                {
                    tipo: 'Soggiorno',
                    costo: 137
                },
                {
                    tipo: 'Visita',
                    costo: 37
                }                
            ]
        }
        
    ];

    $scope.isPresente = function(pv, tipo) {
        for(var index = 0; index < pv.voci.length; ++index){
            if(pv.voci[index].tipo === tipo)
                return true;
        }
        return false;
    };
    
    $scope.calcolaPrezzo = function(pv) {
        var prezzoTotale = 0;
        for(var index = 0; index < pv.voci.length; ++index){
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
});


