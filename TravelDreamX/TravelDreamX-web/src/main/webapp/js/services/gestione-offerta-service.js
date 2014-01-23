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
    return {
        abilita: __salva,
        disabilita: __salva,
        modifica: __salva
    };
}); 

