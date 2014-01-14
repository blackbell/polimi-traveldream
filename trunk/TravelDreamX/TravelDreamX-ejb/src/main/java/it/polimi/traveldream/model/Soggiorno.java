/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dario
 */
@Entity
@DiscriminatorValue("soggiorno")
@Table(name = "soggiorni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Soggiorno.findAll", query = "SELECT s FROM Soggiorno s"),
    @NamedQuery(name = "Soggiorno.findByGiornoInizio", query = "SELECT s FROM Soggiorno s WHERE s.giornoInizio = :giornoInizio"),
    @NamedQuery(name = "Soggiorno.findByGiornoFine", query = "SELECT s FROM Soggiorno s WHERE s.giornoFine = :giornoFine"),
    @NamedQuery(name = "Soggiorno.findByNumeroPersone", query = "SELECT s FROM Soggiorno s WHERE s.numeroPersone = :numeroPersone"),
    @NamedQuery(name = "Soggiorno.findByCosto", query = "SELECT s FROM Soggiorno s WHERE s.costo = :costo")})
public class Soggiorno extends Voce implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "giornoInizio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date giornoInizio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "giornoFine")
    @Temporal(TemporalType.TIMESTAMP)
    private Date giornoFine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroPersone")
    private int numeroPersone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private float costo;
    @JoinColumn(name = "idAlbergo", referencedColumnName = "idAlbergo")
    @ManyToOne(optional = false)
    private Albergo albergo;

    public Soggiorno() {
        this.setTipo("Soggiorno");
    }

    public Soggiorno(Date giornoInizio, Date giornoFine, int numeroPersone, float costo) {
        this();
        this.giornoInizio = giornoInizio;
        this.giornoFine = giornoFine;
        this.numeroPersone = numeroPersone;
        this.costo = costo;
    }

    public Date getGiornoInizio() {
        return giornoInizio;
    }

    public void setGiornoInizio(Date giornoInizio) {
        this.giornoInizio = giornoInizio;
    }
	
    public Date getGiornoFine() {
        return giornoFine;
    }

    public void setGiornoFine(Date giornoFine) {
        this.giornoFine = giornoFine;
    }

    public int getNumeroPersone() {
        return numeroPersone;
    }

    public void setNumeroPersone(int numeroPersone) {
        this.numeroPersone = numeroPersone;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public Albergo getAlbergo() {
        return albergo;
    }

    public void setAlbergo(Albergo albergo) {
        this.albergo = albergo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdVoce() != null ? getIdVoce().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Soggiorno)) {
            return false;
        }
        Soggiorno other = (Soggiorno) object;
        if ((this.getIdVoce() == null && other.getIdVoce() != null) || (this.getIdVoce() != null && !this.getIdVoce().equals(other.getIdVoce()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.polimi.traveldream.model.Soggiorno[ idVoce = " + getIdVoce() + " ]";
    }
    
}