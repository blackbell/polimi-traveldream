/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('pvModalController', function($scope, $rootScope, $location) {

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
        $location.path('/composizionePV');
    };
    
    $scope.isPresente = function(pv, tipo) {
        for(var index = 0; index < pv.voci.length; ++index){
            if(pv.voci[index].tipo === tipo)
                return true;
        }
        return false;
    };
});

