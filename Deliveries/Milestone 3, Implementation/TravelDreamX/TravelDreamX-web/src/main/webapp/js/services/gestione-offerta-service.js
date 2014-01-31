/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
travelDreamApp.factory('gestioneOffertaService', function($http) {
    
    var __salva = function( pv, callback){
        $http({method: 'POST', data:pv, url: 'salvaPV.json'}).
                success(function (data, status, headers, config){
                    callback(data);
                }).
                 error(function (data, status, headers, config){
                    toastr.error("Errore " + status);
                });
    };
    var __salvaPB = function( pb, callback){
        $http({method: 'POST', data:pb, url: 'salvaPB.json'}).
                success(function (data, status, headers, config){
                    callback(data);
                }).
                 error(function (data, status, headers, config){
                    toastr.error("Errore " + status);
                });
    };
    var __abilitaPV = function( id, callback){
        $http({method: 'POST', data:id, url: 'attivaPV.json'}).
                success(function (data, status, headers, config){
                    callback(data);
                }).
                 error(function (data, status, headers, config){
                    toastr.error("Errore " + status);
                });
    };
    var __disabilitaPV = function( id, callback){
        $http({method: 'POST', data:id, url: 'disattivaPV.json'}).
                success(function (data, status, headers, config){
                    callback(data);
                }).
                 error(function (data, status, headers, config){
                    toastr.error("Errore " + status);
                });
    };
    var __abilitaPB = function( id, callback){
        $http({method: 'POST', data:id, url: 'attivaPB.json'}).
                success(function (data, status, headers, config){
                    callback(data);
                }).
                 error(function (data, status, headers, config){
                    toastr.error("Errore " + status);
                });
    };
    var __disabilitaPB = function( id, callback){
        $http({method: 'POST', data:id, url: 'disattivaPB.json'}).
                success(function (data, status, headers, config){
                    callback(data);
                }).
                 error(function (data, status, headers, config){
                    toastr.error("Errore " + status);
                });
    };
    return {
        abilitaPV: __abilitaPV,
        disabilitaPV: __disabilitaPV,
        abilitaPB: __abilitaPB,
        disabilitaPB: __disabilitaPB,
        modifica: __salva,
        salvaPB: __salvaPB
    };
}); 

