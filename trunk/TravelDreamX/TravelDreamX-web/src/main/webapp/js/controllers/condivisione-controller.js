/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';
travelDreamApp.controller('condivisioneController', function($scope, $rootScope) {
    toastr.options = {
        positionClass: "toast-center"
    };
    
    $scope.emails = new Array(new Object());
    $scope.getLinkCondivisione = function() {
        if (typeof $rootScope.linkCondivisione !== 'undefined')
            $scope.linkCondivisione = $rootScope.linkCondivisione;
    };
    $scope.aggiungiCampoEmail = function() {
        $scope.emails.push(new Object());
    };
    $scope.condividi = function (){
        toastr.success("il tuo pacchetto è stato condiviso!", "Successo:");
        $scope.dismiss();
    };
    $scope.ceAlmenoUnIndirizzo = function (){
        if($scope.emails[0].indirizzoEmail.length>6)
            return true;
        else
            return false;
    };
});


