/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.Museo;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.TipoPB;
import java.util.Date;

/**
 *
 * @author Dario
 */
public class ParametriRicercaPB {
    private boolean disabilitatiInclusi = false;
    private TipoPB tipo;
    private Rotta rotta;
    private Float costo;
    private Integer numPersone;
    private Albergo albergo;
    private String citta;
    private Museo museo;
    private Date dataOra;
    private Date dataOraFine;

    public Date getDataOra() {
        return dataOra;
    }

    public void setDataOra(Date dataOra) {
        this.dataOra = dataOra;
    }

    public Date getDataOraFine() {
        return dataOraFine;
    }

    public void setDataOraFine(Date dataOraFine) {
        this.dataOraFine = dataOraFine;
    }

    public Museo getMuseo() {
        return museo;
    }

    public void setMuseo(Museo museo) {
        this.museo = museo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }
    private Integer idMuseo;
    private Date data;
    
    public Rotta getRotta() {
        return rotta;
    }

    public void setRotta(Rotta rotta) {
        this.rotta = rotta;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Integer getNumPersone() {
        return numPersone;
    }

    public void setNumPersone(Integer numPasseggeri) {
        this.numPersone = numPasseggeri;
    }

    public TipoPB getTipo() {
        return tipo;
    }

    public void setTipo(TipoPB tipo) {
        this.tipo = tipo;
    }

    public Albergo getAlbergo() {
        return albergo;
    }

    public void setAlbergo(Albergo albergo) {
        this.albergo = albergo;
    }

    public Integer getIdMuseo() {
        return idMuseo;
    }

    public void setIdMuseo(Integer idMuseo) {
        this.idMuseo = idMuseo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        long time = data.getTime();
        time = ((int)time / (60*60*24))*(60*60*24);
        this.data = new Date(time);
    }
    
    public boolean isDisabilitatiInclusi() {
        return disabilitatiInclusi;
    }

    public void setDisabilitatiInclusi(boolean disabilitatiInclusi) {
        this.disabilitatiInclusi = disabilitatiInclusi;
    }
}
