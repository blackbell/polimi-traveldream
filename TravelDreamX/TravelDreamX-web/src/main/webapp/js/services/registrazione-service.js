'use strict';

travelDreamApp.factory('registrazioneService', function($http) {
    var __registrazione = function(utente, callback){
        $http({method: 'POST', data:utente, url: 'registrazione.json'}).
                success(function (data, status, headers, config){
                    callback(data);
                }).
                 error(function (data, status, headers, config){
                    toastr.error("Errore " + status);
                });
    };
    
    return {
      registrazione: __registrazione  
    };
});