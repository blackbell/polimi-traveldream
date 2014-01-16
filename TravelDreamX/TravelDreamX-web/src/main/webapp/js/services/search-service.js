/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
travelDreamApp.factory('searchService', function($http) {
    var __trovaPB = function( trovaPBParams, callback){
        $http({method: 'POST', data:trovaPBParams, url: 'trovaPB.json'}).
                success(function (data, status, headers, config){
                    callback(data);
                }).
                 error(function (data, status, headers, config){
                    toastr.error("Errore " + status);
                });
    };
    
    return {
        trovaPB: __trovaPB  
    };
});

