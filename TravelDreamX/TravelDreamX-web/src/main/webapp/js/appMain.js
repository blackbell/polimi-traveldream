'use strict';
var travelDreamApp = angular.module('travelDreamApp', ['ngRoute', '$strap.directives'])
        .config(function($routeProvider) {
            $routeProvider.
                    when('/composizionePV', {
                        templateUrl: 'templates/composizionePV.html',
                        controller: 'composizioneController'
                    }).
                    when('/composizionePV/shared/:sharedID', {
                        templateUrl: 'templates/composizionePV.html',
                        controller: 'composizioneController'
                    }).
                    when('/proposteViaggio', {
                        templateUrl: 'templates/proposteViaggio.html',
                        controller: 'proposteViaggioController'
                    }).
                    when('/registrazione', {
                        templateUrl: 'templates/modal/registrazione.html',
                        controller: 'autenticazioneController'
                    }).
                    when('/amministrazione', {
                        templateUrl: 'templates/amministrazione.html',
                        controller: 'amministrazioneController'
                    }).
                    when('/trovaPB', {
                        templateUrl: 'templates/trovaPB.html',
                        controller: 'gestioneOffertaController'
                    }).        
                    otherwise({
                        redirectTo: '/proposteViaggio'
                    });
        });