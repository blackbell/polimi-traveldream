'use strict';
travelDreamApp.controller('composizioneController', function($scope, $rootScope) {
    var inizializzaPV = function (){
        $scope.PV = {
            composizioneCollection: [
                { 
                    voce:{
                        tipo: 'volo'
                    }
                }, 
                {   voce:{
                        tipo: 'soggiorno'
                    }
                }, 
                {   voce:{
                        tipo: 'visita'
                    }
                }
            ]
        };
    };
    
    $scope.getPVdaRootScope = function(){
        $scope.PV = $rootScope.PV;
        if(typeof $scope.PV === 'undefined'){
            inizializzaPV();
        };
    };
    
});