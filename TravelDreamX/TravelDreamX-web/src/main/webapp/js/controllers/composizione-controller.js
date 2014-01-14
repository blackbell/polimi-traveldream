'use strict';
travelDreamApp.controller('composizioneController', function($scope, $rootScope, searchService) {
    //****************************
    //***** Inizializzazione *****
    //****************************
    toastr.options = {
        positionClass: "toast-center"
    };
    $scope.waiting = false;
    $scope.getPVdaRootScope = function() {
        if (typeof $rootScope.PV === 'undefined') {
            inizializzaPV();
        }
        ;
    };
    var inizializzaPV = function() {
        $rootScope.PV = {
            voci: [
                {
                    tipo: 'Volo'
                },
                {
                    tipo: 'Soggiorno'
                },
                {
                    tipo: 'Visita'
                }
            ]
        };
        $rootScope.indiceSelezionato = -1;
    };

    $scope.getVociPV = function() {
        return $rootScope.PV.voci;
    };
    //**********************************       
    //***** Lista voci - SELEZIONE *****
    //**********************************       

    $scope.isSelezionato = function(data) {
        if (typeof data === 'number')
            return $rootScope.indiceSelezionato === data;
        else
            return $scope.tipoVoceSelezionata === data;
    };
    $scope.seleziona = function(tipo, indice) {
        $scope.tipoVoceSelezionata = tipo;
        $rootScope.indiceSelezionato = indice;
    };

    $scope.deseleziona = function(indice) {
        if (indice === $scope.indiceSelezionato) {
            $scope.tipoVoceSelezionata = 'nonSelezionato';
            $rootScope.indiceSelezionato = -1;
        } else {
            $rootScope.indiceSelezionato -= 1;
        }
        ;
    };

    $scope.isCompleta = function(voce) {
        //console.log(voce.tipo + " " + (typeof voce.costo !== 'undefined') + " costo " + voce.costo);
        return (typeof voce.costo !== 'undefined');
    };

    $scope.isVolo = function(voce) {
        return (($scope.isCompleta(voce)) && (voce.tipo === 'Volo'));
    };

    $scope.isSoggiorno = function(voce) {
        return (($scope.isCompleta(voce)) && (voce.tipo === 'Soggiorno'));
    };

    $scope.isVisita = function(voce) {
        return (($scope.isCompleta(voce)) && (voce.tipo === 'Visita'));
    };

    //**************************************        
    //***** Lista voci - GESTIONE VOCI *****
    //**************************************        

    $scope.eliminaVoce = function(indice) {
        if (indice > -1) {
            $scope.deseleziona(indice);
            toastr.success("Hai eliminato la voce: " + $rootScope.PV.voci[indice].tipo + ".");
            $rootScope.PV.voci.splice(indice, 1);
        }
    };

    $scope.creaVoce = function(tipo) {
        var voce = {
            tipo: tipo
        };
        var aggiungiInTesta = function(voce) {
            $rootScope.PV.voci.unshift(voce);
            if ($rootScope.indiceSelezionato !== -1)
                $rootScope.indiceSelezionato += 1;
        };
        aggiungiInTesta(voce);
        toastr.success("Hai aggiunto la voce: " + tipo + ".");
        console.log($rootScope.PV.voci);
    };

    //***** RICERCA *****
    $scope.trovaPB = function() {
        $scope.waiting = true;
        searchService.trovaPB(function(esito) {
            if (esito.result) {
                $scope.voli = esito.returnedObj;
            } else
                toastr.error(esito.message, "ERRORE:");
            $scope.waiting = false;
        });
    };

    $scope.aggiungiPBaPV = function(PB) {
        $rootScope.aggiungiPBaPV(PB);
    };

    $rootScope.aggiungiPBaPV = function(PB) {
        $rootScope.PV.voci[$rootScope.indiceSelezionato] = PB;
        toastr.success($rootScope.PV.voci[$rootScope.indiceSelezionato].tipo + ' aggiunto al pacchetto viaggio.');
        console.log($rootScope.PV.voci);
    };

    $scope.numStelle = 3;
    $scope.getNumStelle = function(num) {
        $scope.numStelleVuote = 5 - num;
        return new Array(num);
    };
    $scope.aumentaStelle = function(indice) {
        $scope.numStelle = $scope.numStelle + indice + 1;
        console.log($scope.numStelle);
        console.log($scope.numStelleVuote);
    };
    $scope.diminuisciStelle = function(indice) {
        $scope.numStelle = indice + 1;
        console.log($scope.numStelle);
        console.log($scope.numStelleVuote);
    };
    $scope.soggiorni = [
        {
            costo: 100.00,
            tipo: 'Soggiorno',
            giornoInizio: new Date(),
            giornoFine: new Date(),
            numeroPersone: 2,
            albergo: {
                nome: 'Ciccio',
                citta: 'Meda',
                stelle: 3,
                urlFoto: 'img/piscine.jpg',
                descrizione: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam sem neque, vulputate nec sem at, pharetra gravida quam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Fusce bibendum posuere magna eget adipiscing. Suspendisse non tellus vulputate, scelerisque enim adipiscing, convallis dolor. Duis ut convallis ipsum. Fusce tortor odio, adipiscing in aliquam non, pharetra quis arcu. Curabitur euismod porta diam eget suscipit. Pellentesque ipsum massa, vulputate in metus feugiat, elementum ultricies magna. Nullam diam ligula, consequat id massa vel, laoreet sagittis tortor. Nam varius metus a congue consequat. Maecenas consequat adipiscing augue. Etiam vel ultricies enim. Vestibulum accumsan dignissim libero, vitae vehicula ipsum malesuada eu. Sed viverra quam massa, a accumsan justo dapibus eget. Praesent a nunc eu nisi laoreet cursus. Cras laoreet nulla in condimentum viverra. Sed interdum tellus vel metus pretium, a semper dolor sodales. Mauris congue convallis orci et tempor. Aenean commodo nec ligula nec malesuada. Vivamus gravida diam in velit aliquet tincidunt. Suspendisse vehicula rutrum fringilla. Ut a aliquet elit. Maecenas magna nibh, auctor a metus id, ultrices blandit magna. Nulla consectetur sed orci quis accumsan. Cras sagittis volutpat tellus, ut scelerisque turpis lobortis ac. Mauris eu felis vitae justo mollis adipiscing.Cras gravida nisi magna. Quisque et bibendum augue, ut viverra justo. In hac habitasse platea dictumst. In ornare tincidunt lorem, eu tincidunt risus dictum eu. Nam et est eu elit tristique vehicula. Integer convallis nunc elementum, tincidunt ante eu, vehicula est. Aenean facilisis auctor diam, non accumsan erat rhoncus nec. Cras tempus justo justo, porttitor rutrum augue tempor ut. Nam auctor est lacus, a condimentum ipsum euismod eget. Integer et porttitor elit, eget viverra lorem. Quisque suscipit placerat nisl, et pulvinar ligula auctor eu. Nullam pulvinar faucibus metus vel porttitor. Vivamus et aliquam eros. Fusce fringilla, nisl placerat blandit cursus, nunc arcu feugiat sem, non vehicula justo augue ac nunc. Praesent a enim leo. Cras posuere interdum augue eu aliquam. Pellentesque vel cursus ipsum. Aliquam consectetur, dolor sit amet ultrices fermentum, magna purus posuere nisl, at tincidunt dolor mauris nec mi. Nullam turpis enim, feugiat quis dignissim non, dignissim quis nulla. Donec dapibus, nulla nec blandit facilisis, tortor dui consequat est, nec rutrum urna libero quis orci. Ut tristique dui lectus, vitae viverra urna bibendum interdum. Vestibulum luctus gravida tellus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nulla facilisi. Nullam risus odio, scelerisque sed urna a, semper ultrices nisi. Aliquam tempus tincidunt dui, vel molestie mi luctus eu. Donec consectetur orci non metus pellentesque semper. Nunc dictum, augue sed placerat iaculis, lacus turpis accumsan est, et pulvinar ligula felis sed quam. Phasellus feugiat lacus risus, et blandit justo ornare sit amet. Sed tempus nisi quis enim accumsan, a vehicula mi tempor. Donec tincidunt sapien sit amet eros feugiat, eu pharetra tellus bibendum. Phasellus fringilla quam quis lacus ultrices consectetur. Aenean a ligula iaculis, faucibus ligula in, rhoncus mauris. Maecenas a dapibus nisl. Morbi iaculis luctus eros, vel posuere dui placerat in. Nullam tincidunt ante in ipsum feugiat pharetra.'
            }
        },
        {
            costo: 200.00,
            tipo: 'Soggiorno',
            giornoInizio: new Date(),
            giornoFine: new Date(),
            numeroPersone: 2,
            albergo: {
                nome: 'Puccio',
                citta: 'Pistoia',
                stelle: 5,
                urlFoto: 'img/piscine.jpg',
                descrizione: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam sem neque, vulputate nec sem at, pharetra gravida quam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Fusce bibendum posuere magna eget adipiscing. Suspendisse non tellus vulputate, scelerisque enim adipiscing, convallis dolor. Duis ut convallis ipsum. Fusce tortor odio, adipiscing in aliquam non, pharetra quis arcu. Curabitur euismod porta diam eget suscipit. Pellentesque ipsum massa, vulputate in metus feugiat, elementum ultricies magna. Nullam diam ligula, consequat id massa vel, laoreet sagittis tortor. Nam varius metus a congue consequat. Maecenas consequat adipiscing augue. Etiam vel ultricies enim. Vestibulum accumsan dignissim libero, vitae vehicula ipsum malesuada eu. Sed viverra quam massa, a accumsan justo dapibus eget. Praesent a nunc eu nisi laoreet cursus. Cras laoreet nulla in condimentum viverra. Sed interdum tellus vel metus pretium, a semper dolor sodales. Mauris congue convallis orci et tempor. Aenean commodo nec ligula nec malesuada. Vivamus gravida diam in velit aliquet tincidunt. Suspendisse vehicula rutrum fringilla. Ut a aliquet elit. Maecenas magna nibh, auctor a metus id, ultrices blandit magna. Nulla consectetur sed orci quis accumsan. Cras sagittis volutpat tellus, ut scelerisque turpis lobortis ac. Mauris eu felis vitae justo mollis adipiscing.Cras gravida nisi magna. Quisque et bibendum augue, ut viverra justo. In hac habitasse platea dictumst. In ornare tincidunt lorem, eu tincidunt risus dictum eu. Nam et est eu elit tristique vehicula. Integer convallis nunc elementum, tincidunt ante eu, vehicula est. Aenean facilisis auctor diam, non accumsan erat rhoncus nec. Cras tempus justo justo, porttitor rutrum augue tempor ut. Nam auctor est lacus, a condimentum ipsum euismod eget. Integer et porttitor elit, eget viverra lorem. Quisque suscipit placerat nisl, et pulvinar ligula auctor eu. Nullam pulvinar faucibus metus vel porttitor. Vivamus et aliquam eros. Fusce fringilla, nisl placerat blandit cursus, nunc arcu feugiat sem, non vehicula justo augue ac nunc. Praesent a enim leo. Cras posuere interdum augue eu aliquam. Pellentesque vel cursus ipsum. Aliquam consectetur, dolor sit amet ultrices fermentum, magna purus posuere nisl, at tincidunt dolor mauris nec mi. Nullam turpis enim, feugiat quis dignissim non, dignissim quis nulla. Donec dapibus, nulla nec blandit facilisis, tortor dui consequat est, nec rutrum urna libero quis orci. Ut tristique dui lectus, vitae viverra urna bibendum interdum. Vestibulum luctus gravida tellus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nulla facilisi. Nullam risus odio, scelerisque sed urna a, semper ultrices nisi. Aliquam tempus tincidunt dui, vel molestie mi luctus eu. Donec consectetur orci non metus pellentesque semper. Nunc dictum, augue sed placerat iaculis, lacus turpis accumsan est, et pulvinar ligula felis sed quam. Phasellus feugiat lacus risus, et blandit justo ornare sit amet. Sed tempus nisi quis enim accumsan, a vehicula mi tempor. Donec tincidunt sapien sit amet eros feugiat, eu pharetra tellus bibendum. Phasellus fringilla quam quis lacus ultrices consectetur. Aenean a ligula iaculis, faucibus ligula in, rhoncus mauris. Maecenas a dapibus nisl. Morbi iaculis luctus eros, vel posuere dui placerat in. Nullam tincidunt ante in ipsum feugiat pharetra.'
            }
        },
        {
            costo: 300.00,
            tipo: 'Soggiorno',
            giornoInizio: new Date(),
            giornoFine: new Date(),
            numeroPersone: 2,
            albergo: {
                nome: 'Ciccione Barabbazzo Hotel casinas',
                citta: 'Vernasca',
                stelle: 4,
                urlFoto: 'img/piscine.jpg',
                descrizione: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam sem neque, vulputate nec sem at, pharetra gravida quam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Fusce bibendum posuere magna eget adipiscing. Suspendisse non tellus vulputate, scelerisque enim adipiscing, convallis dolor. Duis ut convallis ipsum. Fusce tortor odio, adipiscing in aliquam non, pharetra quis arcu. Curabitur euismod porta diam eget suscipit. Pellentesque ipsum massa, vulputate in metus feugiat, elementum ultricies magna. Nullam diam ligula, consequat id massa vel, laoreet sagittis tortor. Nam varius metus a congue consequat. Maecenas consequat adipiscing augue. Etiam vel ultricies enim. Vestibulum accumsan dignissim libero, vitae vehicula ipsum malesuada eu. Sed viverra quam massa, a accumsan justo dapibus eget. Praesent a nunc eu nisi laoreet cursus. Cras laoreet nulla in condimentum viverra. Sed interdum tellus vel metus pretium, a semper dolor sodales. Mauris congue convallis orci et tempor. Aenean commodo nec ligula nec malesuada. Vivamus gravida diam in velit aliquet tincidunt. Suspendisse vehicula rutrum fringilla. Ut a aliquet elit. Maecenas magna nibh, auctor a metus id, ultrices blandit magna. Nulla consectetur sed orci quis accumsan. Cras sagittis volutpat tellus, ut scelerisque turpis lobortis ac. Mauris eu felis vitae justo mollis adipiscing.Cras gravida nisi magna. Quisque et bibendum augue, ut viverra justo. In hac habitasse platea dictumst. In ornare tincidunt lorem, eu tincidunt risus dictum eu. Nam et est eu elit tristique vehicula. Integer convallis nunc elementum, tincidunt ante eu, vehicula est. Aenean facilisis auctor diam, non accumsan erat rhoncus nec. Cras tempus justo justo, porttitor rutrum augue tempor ut. Nam auctor est lacus, a condimentum ipsum euismod eget. Integer et porttitor elit, eget viverra lorem. Quisque suscipit placerat nisl, et pulvinar ligula auctor eu. Nullam pulvinar faucibus metus vel porttitor. Vivamus et aliquam eros. Fusce fringilla, nisl placerat blandit cursus, nunc arcu feugiat sem, non vehicula justo augue ac nunc. Praesent a enim leo. Cras posuere interdum augue eu aliquam. Pellentesque vel cursus ipsum. Aliquam consectetur, dolor sit amet ultrices fermentum, magna purus posuere nisl, at tincidunt dolor mauris nec mi. Nullam turpis enim, feugiat quis dignissim non, dignissim quis nulla. Donec dapibus, nulla nec blandit facilisis, tortor dui consequat est, nec rutrum urna libero quis orci. Ut tristique dui lectus, vitae viverra urna bibendum interdum. Vestibulum luctus gravida tellus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nulla facilisi. Nullam risus odio, scelerisque sed urna a, semper ultrices nisi. Aliquam tempus tincidunt dui, vel molestie mi luctus eu. Donec consectetur orci non metus pellentesque semper. Nunc dictum, augue sed placerat iaculis, lacus turpis accumsan est, et pulvinar ligula felis sed quam. Phasellus feugiat lacus risus, et blandit justo ornare sit amet. Sed tempus nisi quis enim accumsan, a vehicula mi tempor. Donec tincidunt sapien sit amet eros feugiat, eu pharetra tellus bibendum. Phasellus fringilla quam quis lacus ultrices consectetur. Aenean a ligula iaculis, faucibus ligula in, rhoncus mauris. Maecenas a dapibus nisl. Morbi iaculis luctus eros, vel posuere dui placerat in. Nullam tincidunt ante in ipsum feugiat pharetra.'
            }
        }
    ];

});