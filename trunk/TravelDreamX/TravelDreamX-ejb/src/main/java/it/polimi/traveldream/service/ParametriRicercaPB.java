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
    private Integer idRotta;
    private Integer idAlbergo;
    private Integer idMuseo;
    private Date data;

    public TipoPB getTipo() {
        return tipo;
    }

    public void setTipo(TipoPB tipo) {
        this.tipo = tipo;
    }

    public Integer getIdRotta() {
        return idRotta;
    }

    public void setIdRotta(Integer idRotta) {
        this.idRotta = idRotta;
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
        this.data = data;
    }
    
    
}
