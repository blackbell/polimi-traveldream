/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.factory('salvaPVPBservice', function($http) {
    var __salvaPV = function(PV, callback){
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

