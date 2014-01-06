'use strict';

var travelDreamApp = angular.module('travelDreamApp', ['ngRoute','ui.bootstrap'])
        .config(function($routeProvider) {
            $routeProvider.
                when('/registrazione', {
                    templateUrl: 'htmlFragments/registrazione.html',
                    controller: 'registrazioneController'
                 }). 
                 otherwise({
                    redirectTo: '/registrazione'       
                });      
    });