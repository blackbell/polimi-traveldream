/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.TipoPB;
import java.util.Date;

/**
 *
 * @author Dario
 */
public class ParametriRicercaPB {
    private TipoPB tipo;
    private Rotta rotta;
    private Float costo;
    private Integer numPasseggeri;
    private Integer idAlbergo;
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

    public Integer getNumPasseggeri() {
        return numPasseggeri;
    }

    public void setNumPasseggeri(Integer numPasseggeri) {
        this.numPasseggeri = numPasseggeri;
    }

    public TipoPB getTipo() {
        return tipo;
    }

    public void setTipo(TipoPB tipo) {
        this.tipo = tipo;
    }

    public Integer getIdAlbergo() {
        return idAlbergo;
    }

    public void setIdAlbergo(Integer idAlbergo) {
        this.idAlbergo = idAlbergo;
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
    
    
}
