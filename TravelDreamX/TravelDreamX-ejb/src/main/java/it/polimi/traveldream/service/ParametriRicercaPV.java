/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import java.util.Date;

/**
 * Incapsula tutti i criteri che è possibile specificare per la ricerca di un PV
 * @author Dario
 */
public class ParametriRicercaPV {

    private Boolean disabilitatiInclusi = false;

    private Integer idPacchetto;
    
    private String nome;
    private String cittaAlbergo;
    private Date giornoInizio;
    private Date giornoFine;

    private String nazionePartenza;
    private String nazioneArrivo;

    public Integer getIdPacchetto() {
        return idPacchetto;
    }

    public void setIdPacchetto(Integer idPacchetto) {
        this.idPacchetto = idPacchetto;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCittaAlbergo() {
        return cittaAlbergo;
    }

    public void setCittaAlbergo(String cittaAlbergo) {
        this.cittaAlbergo = cittaAlbergo;
    }

    
    public Date getGiornoInizio() {
        return giornoInizio;
    }

    public Date getGiornoFine() {
        return giornoFine;
    }

    public void setGiornoFine(Date giornoFine) {
        long time = giornoFine.getTime();
        time = ((long)time / (1000))*(1000);
        this.giornoFine = new Date(time);
    }

    
    public void setGiornoInizio(Date giornoInizio) {
        long time = giornoInizio.getTime();
        time = ((long)time / (24*60*60*1000))*(24*60*60*1000);
        this.giornoInizio = new Date(time);
    }
    
    public Boolean isDisabilitatiInclusi() {
        return disabilitatiInclusi;
    }

    public void setDisabilitatiInclusi(Boolean disabilitatiInclusi) {
        this.disabilitatiInclusi = disabilitatiInclusi;
    }

    public String getNazionePartenza() {
        return nazionePartenza;
    }

    public void setNazionePartenza(String nazionePartenza) {
        this.nazionePartenza = nazionePartenza;
    }

    public String getNazioneArrivo() {
        return nazioneArrivo;
    }

    public void setNazioneArrivo(String nazioneArrivo) {
        this.nazioneArrivo = nazioneArrivo;
    }
    
}
