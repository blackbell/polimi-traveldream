'use strict';

travelDreamApp.controller('autenticazioneController', function($scope, $location, registrazioneService, loginService) {

    $scope.waiting = false;
    
    $scope.registrazione = function(utente, form) {
        if(form.$valid){
            $scope.waiting = true;
            registrazioneService.registrazione(utente,function(esito){
                console.log("Esito registrazione: " + JSON.stringify(esito));
                if(esito.result){
                    //$location.path(result.newUrl);
                    toastr.success("l'utente " + utente.email + " puo' ora loggarsi.","Registrazione avvenuta con successo");
                    $scope.dismiss();
                }else
                    toastr.error("l'utente " + utente.email + " e' gia' presente.","Registrazione fallita");
                $scope.waiting = false;
            });
        } else {
            toastr.error("Compila tutti i campi richiesti nel modo corretto.","ERRORE");
        }
    };
    
    $scope.login = function(utente, form) {
        if(form.$valid){
            $scope.waiting = true;
            loginService.login(utente,function(esito){
                console.log("Esito login: " + JSON.stringify(esito));
                if(esito.result){
                    //$location.path(result.newUrl);
                    //$scope.dismiss();
                    $scope.utente = esito.returnedObj;
                    toastr.success("l'utente " + utente.email + " è ora loggato","Login avvenuto con successo");
                }else
                    toastr.error("Email " + utente.email + " o password " + utente.password + " errati.","Login fallito");
                $scope.waiting = false ;
            });
        } else {
            toastr.error("Compila tutti i campi richiesti nel modo corretto.","ERRORE");
        }
    };
   
    $scope.logout = function (){
        delete( $scope.utente );
        toastr.success("Logout effettuato. DA SISTEMARE", "Arrivederci!");
    };
    
    $scope.checkEmail = function(formEmail){
        if(formEmail.$valid){
            toastr.success("Il campo è stato compilato correttamente","OK");
        } else {
            toastr.warning("Inserisci un indirizzo valido (es. maro.rossi@jmail.com)","Attenzione:");
        };
    };
    $scope.checkPwd = function(formPwd){
        if(formPwd.$valid){
            toastr.success("Il campo è stato compilato correttamente","OK");
        } else {
            toastr.warning("La password deve avere lunghezza compresa tra 5 e 15 caratteri","Attenzione:");
        };
    };
    $scope.isLogged = function (){
        return typeof $scope.utente !== 'undefined';
    };
});

