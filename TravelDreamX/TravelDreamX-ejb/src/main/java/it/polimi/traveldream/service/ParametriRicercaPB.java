/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.TipoPB;
import java.util.Date;

/**
 *
 * @author Dario
 */
public class ParametriRicercaPB {
    
    private TipoPB tipo;
    private String nomeAlbergo;
    private String nomeMuseo;
    private String cittaAlbergo;
    private String cittaMuseo;
    private String cittàPartenzaVolo;
    private String cittàArrivoVolo;
    private Date dataInizioSoggiorno;
    private Date dataOraVolo;
    private Date dataFineSoggiorno;

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

    public String getCittàPartenzaVolo() {
        return cittàPartenzaVolo;
    }

    public void setCittàPartenzaVolo(String cittàPartenzaVolo) {
        this.cittàPartenzaVolo = cittàPartenzaVolo;
    }

    public String getCittàArrivoVolo() {
        return cittàArrivoVolo;
    }

    public void setCittàArrivoVolo(String cittàArrivoVolo) {
        this.cittàArrivoVolo = cittàArrivoVolo;
    }

    public Date getDataOraVolo() {
        return dataOraVolo;
    }

    public void setDataOraVolo(Date dataOraVolo) {
        long time = dataOraVolo.getTime();
        time = ((int)time / (60*60*24))*(60*60*24);
        this.dataOraVolo = new Date(time);
    }

    public Date getDataFineSoggiorno() {
        return dataFineSoggiorno;
    }

    public void setDataFineSoggiorno(Date dataFineSoggiorno) {
        long time = dataOraVolo.getTime();
        time = ((int)time / (60*60*24))*(60*60*24);
        this.dataFineSoggiorno = new Date(time);
    }

    
    public void setDataInizioSoggiorno(Date data) {
        long time = data.getTime();
        time = ((int)time / (60*60*24))*(60*60*24);
        this.dataInizioSoggiorno = new Date(time);
    }
    
    public boolean isDisabilitatiInclusi() {
        return disabilitatiInclusi;
    }

    public void setDisabilitatiInclusi(boolean disabilitatiInclusi) {
        this.disabilitatiInclusi = disabilitatiInclusi;
    }
}
