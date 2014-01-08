'use strict';

travelDreamApp.factory('loginService', function($http) {
    var __login = function(utente, callback){
        $http({method: 'POST', data:utente, url: 'login.json'}).
                success(function (data, status, headers, config){
                    callback(data);
                }).
                 error(function (data, status, headers, config){
                    toastr.error("Errore " + status);
                });
    };
    
    return {
      login: __login  
    };
});