/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.factory('amministrazioneService', function($http) {

    var __getUtenti = function(parametriRicercaUtenti, callback) {
        $http({method: 'GET', data: parametriRicercaUtenti, url: 'getUtenti.json'}).
                success(function(data, status, headers, config) {
                    callback(data);
                }).
                error(function(data, status, headers, config) {
                    toastr.error("Errore " + status);
                });
    };
    var __disattivaUtente = function(utente, callback) {
        $http({method: 'UPDATE', data: utente, url: 'disattivaUtente.json'}).
                success(function(data, status, headers, config) {
                    callback(data);
                }).
                error(function(data, status, headers, config) {
                    toastr.error("Errore " + status);
                });
    };
    var __recuperaPagamenti = function(parametriRicercaPagamenti, callback) {
        $http({method: 'GET', data: parametriRicercaPagamenti, url: 'recuperaPagamenti.json'}).
                success(function(data, status, headers, config) {
                    callback(data);
                }).
                error(function(data, status, headers, config) {
                    toastr.error("Errore " + status);
                });
    };


    return {
        getUtenti: __getUtenti,
        disattivaUtente: __disattivaUtente,
        recuperaPagamenti: __recuperaPagamenti
    };
});
