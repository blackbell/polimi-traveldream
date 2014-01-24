/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.factory('salvaPVPBservice', function($http) {
    var eliminaProprietaNullePV = function (PV){
        if(!PV.nome)
            delete PV.nome;
        if(!PV.cittaAlbergo)
            delete PV.cittaAlbergo;
        if(!PV.giornoFine)
            delete PV.giornoFine;
        if(!PV.giornoInizio)
            delete PV.giornoInizio;
        return PV;
    };
    var __salvaPV = function(PV, callback){
        PV = eliminaProprietaNullePV(PV);
        $http({method: 'POST', data:PV, url: 'salvaPV.json'}).
                success(function (data, status, headers, config){
                    callback(data);
                }).
                 error(function (data, status, headers, config){
                    toastr.error("Errore " + status);
                });
    };
    
    return {
      salvaPV: __salvaPV  
    };
});

