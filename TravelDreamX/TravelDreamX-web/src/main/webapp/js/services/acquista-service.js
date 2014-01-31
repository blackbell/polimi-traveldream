/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

'use strict';

travelDreamApp.factory('acquistaService', function($http) {
    var eliminaProprietàNullePV = function (p){
        if(!p.cittaAlbergo)
            delete p.cittaAlbergo;
        if(!p.giornoFine)
            delete p.giornoFine;
        if(!p.giornoInizio)
            delete p.giornoInizio;
        if(!p.nazioneArrivo)
            delete p.nazioneArrivo;
        if(!p.nazionePartenza)
            delete p.nazionePartenza;
        return p;
    };
    var __acquistaPV = function(pv, callback){
        pv = eliminaProprietàNullePV(pv);
        $http({method: 'POST', data:pv, url: 'acquistaPV.json'}).
                success(function (data, status, headers, config){
                    callback(data);
                }).
                 error(function (data, status, headers, config){
                    toastr.error("Errore " + status);
                });
    };
    return{
        acquistaPV: __acquistaPV
    };
});

