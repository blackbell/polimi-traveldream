/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author rh0x
 */
@Embeddable
public class ComposizionePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPacchetto")
    private int idPacchetto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idVoce")
    private int idVoce;

    public ComposizionePK() {
    }

    public ComposizionePK(int idPacchetto, int idVoce) {
        this.idPacchetto = idPacchetto;
        this.idVoce = idVoce;
    }

    public int getIdPacchetto() {
        return idPacchetto;
    }

    public void setIdPacchetto(int idPacchetto) {
        this.idPacchetto = idPacchetto;
    }

    public int getIdVoce() {
        return idVoce;
    }

    public void setIdVoce(int idVoce) {
        this.idVoce = idVoce;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPacchetto;
        hash += (int) idVoce;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComposizionePK)) {
            return false;
        }
        ComposizionePK other = (ComposizionePK) object;
        if (this.idPacchetto != other.idPacchetto) {
            return false;
        }
        if (this.idVoce != other.idVoce) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.polimi.traveldream.model.prova.ComposizionePK[ idPacchetto=" + idPacchetto + ", idVoce=" + idVoce + " ]";
    }
    
}
