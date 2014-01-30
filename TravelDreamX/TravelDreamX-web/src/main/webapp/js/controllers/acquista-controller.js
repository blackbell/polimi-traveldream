/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';
travelDreamApp.controller('acquistaController', function($scope, $rootScope, acquistaService) {

    $scope.acquistaPV = function (){
        console.log("rootsope.pv");
        console.log($rootScope.PV);
        acquistaService.acquistaPV($rootScope.PV, function(esito){
            if(esito.result)
                toastr.success("Acquisto avvenuto con successo.");
            else
                toastr.error(esito.message,"ERRORE:");
        });
    };
    
});
