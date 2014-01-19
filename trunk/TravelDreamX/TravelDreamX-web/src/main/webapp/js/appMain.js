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
                    otherwise({
                        redirectTo: '/'
                    });
        });