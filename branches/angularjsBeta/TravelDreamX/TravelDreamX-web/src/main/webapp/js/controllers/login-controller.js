'use strict';

travelDreamApp.controller('loginController', function($scope, $route, registrazioneService) {

    $scope.inizializzaUtente = function() {
        
        $scope.utente = {
            
        };

    };

    $scope.close = function(){
        //$('#loginbutton').click();
    };
    
    $scope.login = function(utente, form) {
        if(form.$valid){
            registrazioneService.registrazione(utente,function(esito){
                if(esito.result){
                    //$location.path(result.newUrl);
                    toastr.success("l'utente " + utente.email + " puo' ora loggarsi.","Registrazione avvenuta con successo");
                }else
                    toastr.error("l'utente " + utente.email + " e' gia' presente.","Registrazione fallita");
            });
        } else {
            alert('error');
            $route.reload();
            //toastr.error("Compila tutti i campi richiesti nel modo corretto.","ERRORE");
            
        }
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
    
});

