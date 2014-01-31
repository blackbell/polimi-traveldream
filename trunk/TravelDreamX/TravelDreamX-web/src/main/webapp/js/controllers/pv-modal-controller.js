/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('pvModalController', function($scope, $rootScope, $location, $modal) {
    toastr.options = {
        positionClass: "toast-center"
    };
    $scope.mettiPVinRootScope = function (pv){
        $rootScope.PV=pv;
    };
    $scope.setIndexes = function (pv){
        var numeroVoli = 0;
        var findVoloAR = function (pv) {
            numeroVoli++;
                if(numeroVoli===2 && pv.voci[index].dataOra > pv.voci[$scope.indexVoloAndata].dataOra){
                    $scope.indexVoloRitorno = index;
                }else {
                    $scope.indexVoloRitorno = $scope.indexVoloAndata;
                    $scope.indexVoloAndata = index;
                };
            return pv;
        };
        console.log(pv);
        for(var index = 0; index < pv.voci.length; ++index){
            if( pv.voci[index].tipo === 'Soggiorno')
                $scope.indexSoggiorno = index;
            if( pv.voci[index].tipo === 'Visita')
                $scope.indexVisita = index;
            if( pv.voci[index].tipo === 'Volo'){ 
                pv = findVoloAR(pv);
            }
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
    
    $scope.calcolaPrezzo = function(pv) {
        var prezzoTotale = 0;
        for (var index = 0; index < pv.voci.length; ++index) {
            prezzoTotale += pv.voci[index].costo;
        }
        return prezzoTotale;
    };
    var apriModaleAcquista = function() {
        var modaleAcquista = {
            template: 'templates/modal/acquista.html',
            show: true,
            backdrop: 'static'
        };
        var popUpModal = function(modal) {
            // do something
            $modal(modal);
        };
        popUpModal(modaleAcquista);
    };
    $scope.vaiAdAcquista = function (){
        if(typeof $rootScope.utente !== 'undefined'){
            $scope.dismiss();
            apriModaleAcquista();
        }
        else 
            toastr.warning("è necessario effettuare il login per procedere all'acquisto", "ATTENZIONE:");
    };
    
});

