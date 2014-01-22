/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.factory('amministrazioneService', function($http) {

    var __recuperaUtenti = function(callback) {
        $http({method: 'POST', url: 'recuperaUtenti.json'}).
                success(function(data, status, headers, config) {
                    callback(data);
                }).
                error(function(data, status, headers, config) {
                    toastr.error("Errore " + status);
                });
    };
    var __disattivaUtente = function(utente, callback) {
        $http({method: 'POST', data: utente, url: 'disattivaUtente.json'}).
                success(function(data, status, headers, config) {
                    callback(data);
                }).
                error(function(data, status, headers, config) {
                    toastr.error("Errore " + status);
                });
    };
    var __abilitaUtente = function(utente, callback) {
        $http({method: 'POST', data: utente, url: 'attivaUtente.json'}).
                success(function(data, status, headers, config) {
                    callback(data);
                }).
                error(function(data, status, headers, config) {
                    toastr.error("Errore " + status);
                });
    };
    var __recuperaPagamenti = function(parametriRicercaPagamenti, callback) {
        $http({method: 'POST', data: parametriRicercaPagamenti, url: 'recuperaPagamenti.json'}).
                success(function(data, status, headers, config) {
                    callback(data);
                }).
                error(function(data, status, headers, config) {
                    toastr.error("Errore " + status);
                });
    };


    return {
        recuperaUtenti: __recuperaUtenti,
        disattivaUtente: __disattivaUtente,
        abilitaUtente:__abilitaUtente,
        recuperaPagamenti: __recuperaPagamenti
    };
});
