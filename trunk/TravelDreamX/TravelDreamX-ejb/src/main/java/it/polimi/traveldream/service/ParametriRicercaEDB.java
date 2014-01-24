/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.TipoEDB;

/**
 * Incapsula tutti i criteri che è possibile specificare per la ricerca di una 
 * EDB
 * @author Dario
 */
public class ParametriRicercaEDB {
    private String nome;
    private String citta;
    private Integer stelle; 
    private String aeroportoPartenza;
    private String cittàPartenza;
    private String nazionePartenza;
    private String aeroportoArrivo;
    private String cittàArrivo;
    private String nazioneArrivo;
    private String compagniaAerea;
    private TipoEDB tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public Integer getStelle() {
        return stelle;
    }

    public void setStelle(Integer stelle) {
        this.stelle = stelle;
    }

    public String getAeroportoPartenza() {
        return aeroportoPartenza;
    }

    public void setAeroportoPartenza(String aeroportoPartenza) {
        this.aeroportoPartenza = aeroportoPartenza;
    }

    public String getCittàPartenza() {
        return cittàPartenza;
    }

    public void setCittàPartenza(String cittàPartenza) {
        this.cittàPartenza = cittàPartenza;
    }

    public String getNazionePartenza() {
        return nazionePartenza;
    }

    public void setNazionePartenza(String nazionePartenza) {
        this.nazionePartenza = nazionePartenza;
    }

    public String getAeroportoArrivo() {
        return aeroportoArrivo;
    }

    public void setAeroportoArrivo(String aeroportoArrivo) {
        this.aeroportoArrivo = aeroportoArrivo;
    }

    public String getCittàArrivo() {
        return cittàArrivo;
    }

    public void setCittàArrivo(String cittàArrivo) {
        this.cittàArrivo = cittàArrivo;
    }

    public String getNazioneArrivo() {
        return nazioneArrivo;
    }

    public void setNazioneArrivo(String nazioneArrivo) {
        this.nazioneArrivo = nazioneArrivo;
    }

    public String getCompagniaAerea() {
        return compagniaAerea;
    }

    public void setCompagniaAerea(String compagniaAerea) {
        this.compagniaAerea = compagniaAerea;
    }

    public TipoEDB getTipo() {
        return tipo;
    }

    public void setTipo(TipoEDB tipo) {
        this.tipo = tipo;
    }
    
    
}
