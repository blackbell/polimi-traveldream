/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('gestioneOffertaController', function($scope, $rootScope, gestioneOffertaService) {
    toastr.options = {
        positionClass: "toast-center"
    };
    
    var mettiPVinRootScope = function (isAbilitato){
        $rootScope.PV.abilitato = isAbilitato;
    };
    
    $scope.isAbilitato = function(p) {
        return p.abilitato;
    };
    $scope.abilitaPV = function(pv) {
        gestioneOffertaService.abilitaPV(pv.idPacchetto, function(esito) {
            if (esito.result) {
                mettiPVinRootScope(esito.returnedObj);
                toastr.succ("pacchetto " + pv.idPacchetto + " abilitato.", esito.message);
            } else {
                toastr.error(esito.message, "ERRORE:");
            }

        });
    };
    $scope.disabilitaPV = function(pv) {
        gestioneOffertaService.disabilitaPV(pv.idPacchetto, function(esito) {
            if (esito.result) {
                mettiPVinRootScope(esito.returnedObj);
                toastr.succ("pacchetto " + pv.idPacchetto + " disabilitato.", esito.message);
            } else {
                toastr.error(esito.message, "ERRORE:");
            }

        });
    };
    $scope.abilitaPB = function(pb) {
        gestioneOffertaService.abilitaPB(pb.idVoce, function(esito) {
            if (esito.result) {
                toastr.succ("pb " + pb.idVoce + " abilitato.", esito.message);
            } else {
                toastr.error(esito.message, "ERRORE:");
            }

        });
    };
    $scope.disabilitaPB = function(pb) {
        gestioneOffertaService.disabilitaPV(pb.idVoce, function(esito) {
            if (esito.result) {
                toastr.succ("pb " + pb.idVoce + " disabilitato.", esito.message);
            } else {
                toastr.error(esito.message, "ERRORE:");
            }

        });
    };
    
});
