/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('amministrazioneController', function($scope, amministrazioneService) {
    $scope.listaUtenti = new Object();
    $scope.waiting = false;
    $scope.parametriRicercaUtente = {
        email: new String(),
        idPagamento: -1
    };
    $scope.getUtenti = function() {
        $scope.waiting = true;
        amministrazioneService.getUtenti( $scope.parametriRicercaUtente, function(esito) {
            if (esito.result) {
                $scope.listaUtenti = esito.returnedObject;
                $scope.waiting = false;
            } else
                $scope.waiting = false;
        });
    };
});

