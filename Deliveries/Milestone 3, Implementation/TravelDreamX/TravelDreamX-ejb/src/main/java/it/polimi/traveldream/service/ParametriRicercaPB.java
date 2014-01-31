/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.TipoPB;
import java.util.Date;

/**
 * Incapsula tutti i criteri che è possibile specificare per la ricerca di un PB
 * @author Dario
 */
public class ParametriRicercaPB {
    
    private TipoPB tipo;
    /* Volo */
    private String cittaPartenzaVolo;
    private String cittaArrivoVolo;
    private Date dataOraVolo;
    
    /* Albergo */
    private String nomeAlbergo;
    private String cittaAlbergo;
    private Date dataInizioSoggiorno;
    private Date dataFineSoggiorno;

    /* Museo */
    private String nomeMuseo;
    private String cittaMuseo;
    private Date giornoVisita;

    public Date getGiornoVisita() {
        return giornoVisita;
    }

    public void setGiornoVisita(Date giornoVisita) {
        long time = giornoVisita.getTime();
        time = ((long)time / (long)(24*60*60*1000))*(24*60*60*1000);
        this.giornoVisita = new Date(time);
    }
    
    private boolean disabilitatiInclusi = false;
    
    public TipoPB getTipo() {
        return tipo;
    }

    public void setTipo(TipoPB tipo) {
        this.tipo = tipo;
    }

    public String getNomeAlbergo() {
        return nomeAlbergo;
    }

    public void setNomeAlbergo(String nomeAlbergo) {
        this.nomeAlbergo = nomeAlbergo;
    }

    public String getNomeMuseo() {
        return nomeMuseo;
    }

    public void setNomeMuseo(String nomeMuseo) {
        this.nomeMuseo = nomeMuseo;
    }

    public String getCittaAlbergo() {
        return cittaAlbergo;
    }

    public void setCittaAlbergo(String cittaAlbergo) {
        this.cittaAlbergo = cittaAlbergo;
    }

    public String getCittaMuseo() {
        return cittaMuseo;
    }

    public void setCittaMuseo(String cittaMuseo) {
        this.cittaMuseo = cittaMuseo;
    }

    public String getCittaPartenzaVolo() {
        return cittaPartenzaVolo;
    }

    public void setCittaPartenzaVolo(String cittaPartenzaVolo) {
        this.cittaPartenzaVolo = cittaPartenzaVolo;
    }

    public String getCittaArrivoVolo() {
        return cittaArrivoVolo;
    }

    public void setCittaArrivoVolo(String cittàArrivoVolo) {
        this.cittaArrivoVolo = cittàArrivoVolo;
    }

    public Date getDataInizioSoggiorno() {
        return dataInizioSoggiorno;
    }

    
    public Date getDataOraVolo() {
        return dataOraVolo;
    }

    public void setDataOraVolo(Date dataOraVolo) {
        long time = dataOraVolo.getTime();
        time = ((long)time / (1000))*(1000);
        this.dataOraVolo = new Date(time);
    }

    public Date getDataFineSoggiorno() {
        return dataFineSoggiorno;
    }

    public void setDataFineSoggiorno(Date dataFineSoggiorno) {
        long time = dataFineSoggiorno.getTime();
        time = ((long)time / (1000))*(1000);
        this.dataFineSoggiorno = new Date(time);
//        this.dataFineSoggiorno = dataFineSoggiorno;
    }

    
    public void setDataInizioSoggiorno(Date dataInizioSoggiorno) {
        long time = dataInizioSoggiorno.getTime();
        time = ((long)time / (24*60*60*1000))*(24*60*60*1000);
        this.dataInizioSoggiorno = new Date(time);
//        this.dataInizioSoggiorno = dataInizioSoggiorno;
    }
    
    public boolean isDisabilitatiInclusi() {
        return disabilitatiInclusi;
    }

    public void setDisabilitatiInclusi(boolean disabilitatiInclusi) {
        this.disabilitatiInclusi = disabilitatiInclusi;
    }
}
