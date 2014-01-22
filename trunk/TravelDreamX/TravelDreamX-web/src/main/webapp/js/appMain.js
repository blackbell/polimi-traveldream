'use strict';
var travelDreamApp = angular.module('travelDreamApp', ['ngRoute', '$strap.directives'])
        .config(function($routeProvider) {
            $routeProvider.
                    when('/composizionePV', {
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
                        controller: 'amministrazioneController',
                        access: {
                            livelloAbilitazione: 2
                        }
                    }).
                    otherwise({
                        redirectTo: '/proposteViaggio'
                    });
        });