/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('pvModalController', function($scope, $rootScope, $location, gestioneOffertaService) {
    toastr.options = {
        positionClass: "toast-center"
    };
    $scope.setIndexes = function (pv){
        console.log(pv);
        for(var index = 0; index < pv.voci.length; ++index){
            if( pv.voci[index].tipo === 'Soggiorno')
                $scope.indexSoggiorno = index;
            if( pv.voci[index].tipo === 'Volo')
                $scope.indexVolo = index;
            if( pv.voci[index].tipo === 'Visita')
                $scope.indexVisita = index;
        }   
    };
   
    $scope.fwdToComposizione = function (pv){
        $scope.dismiss();
        $rootScope.PV=pv;
        console.log("[BUG] mancano i dati di volo e visita perchè il pv non è completo");
        console.log(pv);
        $location.path('/composizionePV');
    };
    
    $scope.isPresente = function(pv, tipo) {
        for(var index = 0; index < pv.voci.length; ++index){
            if(pv.voci[index].tipo === tipo)
                return true;
        }
        return false;
    };
    
    $scope.isAbilitato = function (pv){
        return pv.abilitato;
    };
    $scope.abilita = function( pv ){
        gestioneOffertaService.abilita(pv ,function (esito){
            if(esito.result){
                toastr.succ(esito.message, "SUCCESSO:");
            }else {
                toastr.error(esito.message, "ERRORE:");
            }
            
        });
    }; 
    $scope.disabilita = function( pv ){
        gestioneOffertaService.abilita(pv ,function (esito){
            if(esito.result){
                toastr.succ(esito.message, "SUCCESSO:");
            }else {
                toastr.error(esito.message, "ERRORE:");
            }
            
        });
    };
    $scope.calcolaPrezzo = function(pv) {
        var prezzoTotale = 0;
        for (var index = 0; index < pv.voci.length; ++index) {
            prezzoTotale += pv.voci[index].costo;
        }
        return prezzoTotale;
    };
});

