/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import java.util.Date;

/**
 *
 * @author Dario
 */
public class ParametriRicercaPV {

    private Boolean disabilitatiInclusi = false;

    private Integer idPacchetto;
    
    private String nome;
    private String cittaAlbergo;
    private Date dataInizio;
    private Date dataFine;

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

    
    public Date getDataInizio() {
        return dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        long time = dataFine.getTime();
        time = ((long)time / (1000))*(1000);
        this.dataFine = new Date(time);
    }

    
    public void setDataInizio(Date dataInizio) {
        long time = dataInizio.getTime();
        time = ((long)time / (24*60*60*1000))*(24*60*60*1000);
        this.dataInizio = new Date(time);
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
