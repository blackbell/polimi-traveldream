'use strict';

var travelDreamApp = angular.module('travelDreamApp', ['ngRoute','$strap.directives'])
        .config(function($routeProvider) {
            $routeProvider.
                when('/composizionePV', {
                    templateUrl: 'templates/composizionePV.html',
                    controller: 'composizioneController'
                 }). 
                 otherwise({
                    redirectTo: '/'       
                });      
    });