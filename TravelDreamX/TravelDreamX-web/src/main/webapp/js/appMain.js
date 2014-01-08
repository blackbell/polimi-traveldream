'use strict';

var travelDreamApp = angular.module('travelDreamApp', ['ngRoute','$strap.directives'])
        .config(function($routeProvider) {
            $routeProvider.
            //eliminare REGISTRAZIONE
                when('/registrazione', {
                    templateUrl: 'templates/modal/registrazione.html',
                    controller: 'registrazioneController'
                 }). 
                 otherwise({
                    redirectTo: '/'       
                });      
    });