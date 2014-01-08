'use strict';

travelDreamApp.controller('loginController', function($scope, $route, loginService) {

    $scope.inizializzaUtente = function() {
        
        $scope.utente = {
            
        };

    };

    $scope.close = function(){
        //$('#loginbutton').click();
    };
    
    $scope.login = function(utente, form) {
        if(form.$valid){
            loginService.login(utente,function(esito){
                if(esito.result){
                    //$location.path(result.newUrl);
                    toastr.success("l'utente " + utente.email + " è loggato","Login avvenuto con successo");
                }else
                    toastr.error("l'utente " + utente.email + " non e' gia' presente.","Login fallito");
            });
        } else {
            toastr.error("Compila tutti i campi richiesti nel modo corretto.","ERRORE");
        }
    };
    
});

