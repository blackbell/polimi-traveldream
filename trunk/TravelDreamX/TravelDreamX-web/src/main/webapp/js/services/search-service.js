/* 
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
travelDreamApp.factory('searchService', function($http) {
    var eliminaParametriRicercaPBInutilizzati = function(parPB) {
        if (!parPB.cittaPartenzaVolo)
            delete parPB.cittaPartenzaVolo;
        if (!parPB.cittaArrivoVolo)
            delete parPB.cittaArrivoVolo;
        if (!parPB.dataOraVolo)
            delete parPB.dataOraVolo;
        if (!parPB.nomeAlbergo)
            delete parPB.nomeAlbergo;
        if (!parPB.cittaAlbergo)
            delete parPB.cittaAlbergo;
        if (!parPB.dataInizioSoggiorno)
            delete parPB.dataInizioSoggiorno;
        if (!parPB.dataFineSoggiorno)
            delete parPB.dataFineSoggiorno;
        if (!parPB.nomeMuseo)
            delete parPB.nomeMuseo;
        if (!parPB.cittaMuseo)
            delete parPB.cittaMuseo;
        if (!parPB.giornoVisita)
            delete parPB.giornoVisita;
        
        return parPB;
    };
    var eliminaParametriRicercaPVInutilizzati = function(parPV) {
        if (!parPV.idPacchetto)
            delete parPV.idPacchetto;
        if (!parPV.idPacchetto)
            delete parPV.nome;
        if (!parPV.cittaAlbergo)
            delete parPV.cittaAlbergo;
        if (!parPV.nazionePartenza)
            delete parPV.nazionePartenza;
        if (!parPV.nazioneArrivo)
            delete parPV.nazioneArrivo;
        if (!parPV.giornoInizio)
            delete parPV.giornoInizio;
        if (!parPV.giornoFine)
            delete parPV.giornoFine;
        
        return parPV;
    };
    var __trovaPB = function(trovaPBParams, callback) {
        trovaPBParams = eliminaParametriRicercaPBInutilizzati(trovaPBParams);
        $http({method: 'POST', data: trovaPBParams, url: 'trovaPB.json'}).
                success(function(data, status, headers, config) {
                    callback(data);
                }).
                error(function(data, status, headers, config) {
                    toastr.error("Errore " + status);
                });
    };
    var __trovaPV = function(trovaPVParams, callback) {
        trovaPVParams = eliminaParametriRicercaPVInutilizzati(trovaPVParams);
        $http({method: 'POST', data: trovaPVParams, url: 'pv.json'}).
                success(function(data, status, headers, config) {
                    callback(data);
                }).
                error(function(data, status, headers, config) {
                    toastr.error("Errore " + status);
                });
    };
    return {
        trovaPB: __trovaPB,
        trovaPV: __trovaPV
    };
});

