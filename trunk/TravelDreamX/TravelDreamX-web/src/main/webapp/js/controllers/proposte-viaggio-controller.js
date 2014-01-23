/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
'use strict';

travelDreamApp.controller('proposteViaggioController', function($scope, searchService) {
    $scope.PVs = [
        {
            nome: 'Splendida avventura',
            urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
            voci: [
                {
                    tipo: 'Volo',
                    costo: 120
                },
                {
                    tipo: 'Soggiorno',
                    costo: 137,
                    giornoInizio: new Date(),
                    giornoFine: new Date(),
                    numeroPersone: 3,
                    albergo: {
                        urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
                        descrizione: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate adipiscing neque eu semper. Sed venenatis dignissim justo, et dapibus nulla auctor eget. Curabitur molestie tristique porta. Fusce condimentum posuere euismod. Quisque congue risus in turpis porta, at malesuada magna ornare. Sed viverra molestie augue. Praesent tincidunt rutrum metus ornare accumsan. Pellentesque tincidunt varius eros id elementum. Sed rhoncus eget nisi id tincidunt. Etiam commodo sit amet erat non malesuada.Praesent lorem augue, ullamcorper sit amet ipsum vitae, consectetur adipiscing nisl. Vivamus dignissim nunc nisl, id aliquet sapien bibendum ut. Fusce at interdum lorem. Maecenas accumsan turpis at tortor iaculis iaculis. Aliquam quis arcu id risus egestas interdum. Nulla mattis arcu bibendum placerat egestas. Nullam eget ornare enim. Cras commodo, mauris viverra sodales auctor, ligula dui placerat eros, eu tincidunt quam elit ac lacus. Vivamus ut condimentum quam, eu consectetur elit. Ut euismod tortor vitae purus gravida sollicitudin. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.Quisque dapibus eros ut egestas adipiscing. Nam hendrerit vel libero non commodo. In hac habitasse platea dictumst. Etiam sit amet eros id neque gravida iaculis eu non felis. Aenean at laoreet tortor, non placerat elit. Duis nec justo id lectus semper varius. Pellentesque condimentum pharetra consectetur. Curabitur in nisl diam. Nunc quis massa in arcu ultrices elementum. Ut felis tortor, vehicula vel tristique posuere, iaculis eu lacus. Cras molestie erat eget nisi dapibus faucibus. Duis faucibus turpis id nunc rhoncus, ac vulputate dui fringilla. Phasellus semper lorem at magna viverra fermentum. Praesent imperdiet, massa in semper faucibus, sapien erat ullamcorper nunc, eget vehicula erat dolor accumsan lectus.Vivamus fringilla euismod leo. Donec a nibh nisi. Donec at metus gravida, fermentum tellus in, auctor dui. Suspendisse sollicitudin erat sit amet congue semper. Aenean mi justo, laoreet ut nulla nec, convallis condimentum libero. Nullam eget velit eget arcu ullamcorper vestibulum. Cras sem quam, condimentum sed urna id, ullamcorper porta metus. Integer leo mi, faucibus nec ipsum ut, interdum porttitor leo. Quisque pretium aliquam ipsum, vitae venenatis purus venenatis non. Nam et bibendum purus. Ut porttitor libero nec urna faucibus pretium. Suspendisse non dapibus felis. Morbi ipsum enim, ultricies eu bibendum eget, tincidunt ut massa. Nunc hendrerit neque eu urna cursus luctus. Suspendisse posuere pharetra varius. Maecenas id diam a leo congue rutrum id in justo.',
                        citta: 'Milano',
                        nome: 'La stella di natale'
                    }
                },
                {
                    tipo: 'Visita',
                    costo: 37
                }
            ]
        },
        {nome: 'Splendida avventura',
            urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
            voci: [
                {
                    tipo: 'Volo',
                    costo: 120
                },
                {
                    tipo: 'Soggiorno',
                    costo: 137,
                    giornoInizio: new Date(),
                    giornoFine: new Date(),
                    numeroPersone: 3,
                    albergo: {
                        urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
                        descrizione: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate adipiscing neque eu semper. Sed venenatis dignissim justo, et dapibus nulla auctor eget. Curabitur molestie tristique porta. Fusce condimentum posuere euismod. Quisque congue risus in turpis porta, at malesuada magna ornare. Sed viverra molestie augue. Praesent tincidunt rutrum metus ornare accumsan. Pellentesque tincidunt varius eros id elementum. Sed rhoncus eget nisi id tincidunt. Etiam commodo sit amet erat non malesuada.Praesent lorem augue, ullamcorper sit amet ipsum vitae, consectetur adipiscing nisl. Vivamus dignissim nunc nisl, id aliquet sapien bibendum ut. Fusce at interdum lorem. Maecenas accumsan turpis at tortor iaculis iaculis. Aliquam quis arcu id risus egestas interdum. Nulla mattis arcu bibendum placerat egestas. Nullam eget ornare enim. Cras commodo, mauris viverra sodales auctor, ligula dui placerat eros, eu tincidunt quam elit ac lacus. Vivamus ut condimentum quam, eu consectetur elit. Ut euismod tortor vitae purus gravida sollicitudin. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.Quisque dapibus eros ut egestas adipiscing. Nam hendrerit vel libero non commodo. In hac habitasse platea dictumst. Etiam sit amet eros id neque gravida iaculis eu non felis. Aenean at laoreet tortor, non placerat elit. Duis nec justo id lectus semper varius. Pellentesque condimentum pharetra consectetur. Curabitur in nisl diam. Nunc quis massa in arcu ultrices elementum. Ut felis tortor, vehicula vel tristique posuere, iaculis eu lacus. Cras molestie erat eget nisi dapibus faucibus. Duis faucibus turpis id nunc rhoncus, ac vulputate dui fringilla. Phasellus semper lorem at magna viverra fermentum. Praesent imperdiet, massa in semper faucibus, sapien erat ullamcorper nunc, eget vehicula erat dolor accumsan lectus.Vivamus fringilla euismod leo. Donec a nibh nisi. Donec at metus gravida, fermentum tellus in, auctor dui. Suspendisse sollicitudin erat sit amet congue semper. Aenean mi justo, laoreet ut nulla nec, convallis condimentum libero. Nullam eget velit eget arcu ullamcorper vestibulum. Cras sem quam, condimentum sed urna id, ullamcorper porta metus. Integer leo mi, faucibus nec ipsum ut, interdum porttitor leo. Quisque pretium aliquam ipsum, vitae venenatis purus venenatis non. Nam et bibendum purus. Ut porttitor libero nec urna faucibus pretium. Suspendisse non dapibus felis. Morbi ipsum enim, ultricies eu bibendum eget, tincidunt ut massa. Nunc hendrerit neque eu urna cursus luctus. Suspendisse posuere pharetra varius. Maecenas id diam a leo congue rutrum id in justo.',
                        citta: 'Milano',
                        nome: 'La stella di natale'
                    }
                },
                {
                    tipo: 'Visita',
                    costo: 37
                }
            ]
        },
        {nome: 'Splendida avventura',
            urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
            voci: [
                {
                    tipo: 'Volo',
                    costo: 120
                },
                {
                    tipo: 'Soggiorno',
                    costo: 137,
                    giornoInizio: new Date(),
                    giornoFine: new Date(),
                    numeroPersone: 3,
                    albergo: {
                        urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
                        descrizione: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate adipiscing neque eu semper. Sed venenatis dignissim justo, et dapibus nulla auctor eget. Curabitur molestie tristique porta. Fusce condimentum posuere euismod. Quisque congue risus in turpis porta, at malesuada magna ornare. Sed viverra molestie augue. Praesent tincidunt rutrum metus ornare accumsan. Pellentesque tincidunt varius eros id elementum. Sed rhoncus eget nisi id tincidunt. Etiam commodo sit amet erat non malesuada.Praesent lorem augue, ullamcorper sit amet ipsum vitae, consectetur adipiscing nisl. Vivamus dignissim nunc nisl, id aliquet sapien bibendum ut. Fusce at interdum lorem. Maecenas accumsan turpis at tortor iaculis iaculis. Aliquam quis arcu id risus egestas interdum. Nulla mattis arcu bibendum placerat egestas. Nullam eget ornare enim. Cras commodo, mauris viverra sodales auctor, ligula dui placerat eros, eu tincidunt quam elit ac lacus. Vivamus ut condimentum quam, eu consectetur elit. Ut euismod tortor vitae purus gravida sollicitudin. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.Quisque dapibus eros ut egestas adipiscing. Nam hendrerit vel libero non commodo. In hac habitasse platea dictumst. Etiam sit amet eros id neque gravida iaculis eu non felis. Aenean at laoreet tortor, non placerat elit. Duis nec justo id lectus semper varius. Pellentesque condimentum pharetra consectetur. Curabitur in nisl diam. Nunc quis massa in arcu ultrices elementum. Ut felis tortor, vehicula vel tristique posuere, iaculis eu lacus. Cras molestie erat eget nisi dapibus faucibus. Duis faucibus turpis id nunc rhoncus, ac vulputate dui fringilla. Phasellus semper lorem at magna viverra fermentum. Praesent imperdiet, massa in semper faucibus, sapien erat ullamcorper nunc, eget vehicula erat dolor accumsan lectus.Vivamus fringilla euismod leo. Donec a nibh nisi. Donec at metus gravida, fermentum tellus in, auctor dui. Suspendisse sollicitudin erat sit amet congue semper. Aenean mi justo, laoreet ut nulla nec, convallis condimentum libero. Nullam eget velit eget arcu ullamcorper vestibulum. Cras sem quam, condimentum sed urna id, ullamcorper porta metus. Integer leo mi, faucibus nec ipsum ut, interdum porttitor leo. Quisque pretium aliquam ipsum, vitae venenatis purus venenatis non. Nam et bibendum purus. Ut porttitor libero nec urna faucibus pretium. Suspendisse non dapibus felis. Morbi ipsum enim, ultricies eu bibendum eget, tincidunt ut massa. Nunc hendrerit neque eu urna cursus luctus. Suspendisse posuere pharetra varius. Maecenas id diam a leo congue rutrum id in justo.',
                        citta: 'Milano',
                        nome: 'La stella di natale'
                    }
                },
                {
                    tipo: 'Visita',
                    costo: 37
                }
            ]
        },
        {nome: 'Splendida avventura',
            urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
            voci: [
                {
                    tipo: 'Volo',
                    costo: 120
                },
                {
                    tipo: 'Soggiorno',
                    costo: 137,
                    giornoInizio: new Date(),
                    giornoFine: new Date(),
                    numeroPersone: 3,
                    albergo: {
                        urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
                        descrizione: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate adipiscing neque eu semper. Sed venenatis dignissim justo, et dapibus nulla auctor eget. Curabitur molestie tristique porta. Fusce condimentum posuere euismod. Quisque congue risus in turpis porta, at malesuada magna ornare. Sed viverra molestie augue. Praesent tincidunt rutrum metus ornare accumsan. Pellentesque tincidunt varius eros id elementum. Sed rhoncus eget nisi id tincidunt. Etiam commodo sit amet erat non malesuada.Praesent lorem augue, ullamcorper sit amet ipsum vitae, consectetur adipiscing nisl. Vivamus dignissim nunc nisl, id aliquet sapien bibendum ut. Fusce at interdum lorem. Maecenas accumsan turpis at tortor iaculis iaculis. Aliquam quis arcu id risus egestas interdum. Nulla mattis arcu bibendum placerat egestas. Nullam eget ornare enim. Cras commodo, mauris viverra sodales auctor, ligula dui placerat eros, eu tincidunt quam elit ac lacus. Vivamus ut condimentum quam, eu consectetur elit. Ut euismod tortor vitae purus gravida sollicitudin. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.Quisque dapibus eros ut egestas adipiscing. Nam hendrerit vel libero non commodo. In hac habitasse platea dictumst. Etiam sit amet eros id neque gravida iaculis eu non felis. Aenean at laoreet tortor, non placerat elit. Duis nec justo id lectus semper varius. Pellentesque condimentum pharetra consectetur. Curabitur in nisl diam. Nunc quis massa in arcu ultrices elementum. Ut felis tortor, vehicula vel tristique posuere, iaculis eu lacus. Cras molestie erat eget nisi dapibus faucibus. Duis faucibus turpis id nunc rhoncus, ac vulputate dui fringilla. Phasellus semper lorem at magna viverra fermentum. Praesent imperdiet, massa in semper faucibus, sapien erat ullamcorper nunc, eget vehicula erat dolor accumsan lectus.Vivamus fringilla euismod leo. Donec a nibh nisi. Donec at metus gravida, fermentum tellus in, auctor dui. Suspendisse sollicitudin erat sit amet congue semper. Aenean mi justo, laoreet ut nulla nec, convallis condimentum libero. Nullam eget velit eget arcu ullamcorper vestibulum. Cras sem quam, condimentum sed urna id, ullamcorper porta metus. Integer leo mi, faucibus nec ipsum ut, interdum porttitor leo. Quisque pretium aliquam ipsum, vitae venenatis purus venenatis non. Nam et bibendum purus. Ut porttitor libero nec urna faucibus pretium. Suspendisse non dapibus felis. Morbi ipsum enim, ultricies eu bibendum eget, tincidunt ut massa. Nunc hendrerit neque eu urna cursus luctus. Suspendisse posuere pharetra varius. Maecenas id diam a leo congue rutrum id in justo.',
                        citta: 'Milano',
                        nome: 'La stella di natale'
                    }
                },
                {
                    tipo: 'Visita',
                    costo: 37
                }
            ]
        },
        {nome: 'Splendida avventura',
            urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
            voci: [
                {
                    tipo: 'Volo',
                    costo: 120
                },
                {
                    tipo: 'Soggiorno',
                    costo: 137,
                    giornoInizio: new Date(),
                    giornoFine: new Date(),
                    numeroPersone: 3,
                    albergo: {
                        urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
                        descrizione: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate adipiscing neque eu semper. Sed venenatis dignissim justo, et dapibus nulla auctor eget. Curabitur molestie tristique porta. Fusce condimentum posuere euismod. Quisque congue risus in turpis porta, at malesuada magna ornare. Sed viverra molestie augue. Praesent tincidunt rutrum metus ornare accumsan. Pellentesque tincidunt varius eros id elementum. Sed rhoncus eget nisi id tincidunt. Etiam commodo sit amet erat non malesuada.Praesent lorem augue, ullamcorper sit amet ipsum vitae, consectetur adipiscing nisl. Vivamus dignissim nunc nisl, id aliquet sapien bibendum ut. Fusce at interdum lorem. Maecenas accumsan turpis at tortor iaculis iaculis. Aliquam quis arcu id risus egestas interdum. Nulla mattis arcu bibendum placerat egestas. Nullam eget ornare enim. Cras commodo, mauris viverra sodales auctor, ligula dui placerat eros, eu tincidunt quam elit ac lacus. Vivamus ut condimentum quam, eu consectetur elit. Ut euismod tortor vitae purus gravida sollicitudin. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.Quisque dapibus eros ut egestas adipiscing. Nam hendrerit vel libero non commodo. In hac habitasse platea dictumst. Etiam sit amet eros id neque gravida iaculis eu non felis. Aenean at laoreet tortor, non placerat elit. Duis nec justo id lectus semper varius. Pellentesque condimentum pharetra consectetur. Curabitur in nisl diam. Nunc quis massa in arcu ultrices elementum. Ut felis tortor, vehicula vel tristique posuere, iaculis eu lacus. Cras molestie erat eget nisi dapibus faucibus. Duis faucibus turpis id nunc rhoncus, ac vulputate dui fringilla. Phasellus semper lorem at magna viverra fermentum. Praesent imperdiet, massa in semper faucibus, sapien erat ullamcorper nunc, eget vehicula erat dolor accumsan lectus.Vivamus fringilla euismod leo. Donec a nibh nisi. Donec at metus gravida, fermentum tellus in, auctor dui. Suspendisse sollicitudin erat sit amet congue semper. Aenean mi justo, laoreet ut nulla nec, convallis condimentum libero. Nullam eget velit eget arcu ullamcorper vestibulum. Cras sem quam, condimentum sed urna id, ullamcorper porta metus. Integer leo mi, faucibus nec ipsum ut, interdum porttitor leo. Quisque pretium aliquam ipsum, vitae venenatis purus venenatis non. Nam et bibendum purus. Ut porttitor libero nec urna faucibus pretium. Suspendisse non dapibus felis. Morbi ipsum enim, ultricies eu bibendum eget, tincidunt ut massa. Nunc hendrerit neque eu urna cursus luctus. Suspendisse posuere pharetra varius. Maecenas id diam a leo congue rutrum id in justo.',
                        citta: 'Milano',
                        nome: 'La stella di natale'
                    }
                },
                {
                    tipo: 'Visita',
                    costo: 37
                }
            ]
        },
        {nome: 'Splendida avventura',
            urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
            voci: [
                {
                    tipo: 'Volo',
                    costo: 120
                },
                {
                    tipo: 'Soggiorno',
                    costo: 137,
                    giornoInizio: new Date(),
                    giornoFine: new Date(),
                    numeroPersone: 3,
                    albergo: {
                        urlFoto: 'http://q-ec.bstatic.com/images/hotel/840x460/216/21693450.jpg',
                        descrizione: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vulputate adipiscing neque eu semper. Sed venenatis dignissim justo, et dapibus nulla auctor eget. Curabitur molestie tristique porta. Fusce condimentum posuere euismod. Quisque congue risus in turpis porta, at malesuada magna ornare. Sed viverra molestie augue. Praesent tincidunt rutrum metus ornare accumsan. Pellentesque tincidunt varius eros id elementum. Sed rhoncus eget nisi id tincidunt. Etiam commodo sit amet erat non malesuada.Praesent lorem augue, ullamcorper sit amet ipsum vitae, consectetur adipiscing nisl. Vivamus dignissim nunc nisl, id aliquet sapien bibendum ut. Fusce at interdum lorem. Maecenas accumsan turpis at tortor iaculis iaculis. Aliquam quis arcu id risus egestas interdum. Nulla mattis arcu bibendum placerat egestas. Nullam eget ornare enim. Cras commodo, mauris viverra sodales auctor, ligula dui placerat eros, eu tincidunt quam elit ac lacus. Vivamus ut condimentum quam, eu consectetur elit. Ut euismod tortor vitae purus gravida sollicitudin. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.Quisque dapibus eros ut egestas adipiscing. Nam hendrerit vel libero non commodo. In hac habitasse platea dictumst. Etiam sit amet eros id neque gravida iaculis eu non felis. Aenean at laoreet tortor, non placerat elit. Duis nec justo id lectus semper varius. Pellentesque condimentum pharetra consectetur. Curabitur in nisl diam. Nunc quis massa in arcu ultrices elementum. Ut felis tortor, vehicula vel tristique posuere, iaculis eu lacus. Cras molestie erat eget nisi dapibus faucibus. Duis faucibus turpis id nunc rhoncus, ac vulputate dui fringilla. Phasellus semper lorem at magna viverra fermentum. Praesent imperdiet, massa in semper faucibus, sapien erat ullamcorper nunc, eget vehicula erat dolor accumsan lectus.Vivamus fringilla euismod leo. Donec a nibh nisi. Donec at metus gravida, fermentum tellus in, auctor dui. Suspendisse sollicitudin erat sit amet congue semper. Aenean mi justo, laoreet ut nulla nec, convallis condimentum libero. Nullam eget velit eget arcu ullamcorper vestibulum. Cras sem quam, condimentum sed urna id, ullamcorper porta metus. Integer leo mi, faucibus nec ipsum ut, interdum porttitor leo. Quisque pretium aliquam ipsum, vitae venenatis purus venenatis non. Nam et bibendum purus. Ut porttitor libero nec urna faucibus pretium. Suspendisse non dapibus felis. Morbi ipsum enim, ultricies eu bibendum eget, tincidunt ut massa. Nunc hendrerit neque eu urna cursus luctus. Suspendisse posuere pharetra varius. Maecenas id diam a leo congue rutrum id in justo.',
                        citta: 'Milano',
                        nome: 'La stella di natale'
                    }
                },
                {
                    tipo: 'Visita',
                    costo: 37
                }
            ]
        }

    ];
    $scope.waiting = false;
    $scope.parametriRicercaPV = {
        idPacchetto: null,
        dataInizio: null,
        dataFine: null,
        cittaAlbergo: null,
        nazionePartenza: null,
        nazioneArrivo: null
    };
    $scope.isPresente = function(pv, tipo) {
        for (var index = 0; index < pv.voci.length; ++index) {
            if (pv.voci[index].tipo === tipo)
                return true;
        }
        return false;
    };

    $scope.calcolaPrezzo = function(pv) {
        var prezzoTotale = 0;
        for (var index = 0; index < pv.voci.length; ++index) {
            prezzoTotale += pv.voci[index].costo;
        }
        return prezzoTotale;
    };
    
    $scope.tooltipVolo = {
        title: 'Volo'
    };
    $scope.tooltipSoggiorno = {
        title: 'Soggiorno'
    };
    $scope.tooltipVisita = {
        title: 'Visita'
    };
    
    $scope.trovaPV = function() {
        $scope.waiting=true;
        searchService.trovaPV($scope.parametriRicercaPV, function(esito) {
            if (esito.result) {
                $scope.PVs = esito.returnedObj;
            } else {
                toastr.error(esito.message, "ERRORE:");
            }
            $scope.waiting = false;
        });
    };
    
});


