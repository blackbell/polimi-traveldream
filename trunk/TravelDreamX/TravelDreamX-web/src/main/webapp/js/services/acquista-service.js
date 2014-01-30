/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

'use strict';

travelDreamApp.factory('acquistaService', function($http) {
    var __acquistaPV = function(pv, callback){
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

