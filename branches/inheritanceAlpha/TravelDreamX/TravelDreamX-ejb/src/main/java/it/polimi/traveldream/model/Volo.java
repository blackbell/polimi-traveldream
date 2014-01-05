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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Entity(name = "Volo")
@DiscriminatorValue("volo")
@Table(name = "voli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Volo.findAll", query = "SELECT v FROM Volo v"),
    @NamedQuery(name = "Volo.findByIdVolo", query = "SELECT v FROM Volo v WHERE v.idVolo = :idVolo"),
    @NamedQuery(name = "Volo.findByDataOra", query = "SELECT v FROM Volo v WHERE v.dataOra = :dataOra"),
    @NamedQuery(name = "Volo.findByNumPasseggeri", query = "SELECT v FROM Volo v WHERE v.numPasseggeri = :numPasseggeri"),
    @NamedQuery(name = "Volo.findByCosto", query = "SELECT v FROM Volo v WHERE v.costo = :costo")})
public class Volo extends Voce implements Serializable {
    private static final long serialVersionUID = 1L;
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
    @Basic(optional = true)
    @Column(name = "idVolo")
    private Integer idVolo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataOra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataOra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numPasseggeri")
    private int numPasseggeri;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private float costo;
    @JoinColumn(name = "idRotta", referencedColumnName = "idRotta")
    @ManyToOne(optional = false)
    private Rotta idRotta;

    public Volo() {
    }

    public Volo(Integer idVolo) {
        this.idVolo = idVolo;
    }

    public Volo(Integer idVolo, Date dataOra, int numPasseggeri, float costo) {
        this.idVolo = idVolo;
        this.dataOra = dataOra;
        this.numPasseggeri = numPasseggeri;
        this.costo = costo;
    }

    public Integer getIdVolo() {
        return idVolo;
    }

    public void setIdVolo(Integer idVolo) {
        this.idVolo = idVolo;
    }

    public Date getDataOra() {
        return dataOra;
    }

    public void setDataOra(Date dataOra) {
        this.dataOra = dataOra;
    }

    public int getNumPasseggeri() {
        return numPasseggeri;
    }

    public void setNumPasseggeri(int numPasseggeri) {
        this.numPasseggeri = numPasseggeri;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public Rotta getIdRotta() {
        return idRotta;
    }

    public void setIdRotta(Rotta idRotta) {
        this.idRotta = idRotta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVolo != null ? idVolo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Volo)) {
            return false;
        }
        Volo other = (Volo) object;
        if ((this.idVolo == null && other.idVolo != null) || (this.idVolo != null && !this.idVolo.equals(other.idVolo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.polimi.traveldream.model.Volo[ idVolo=" + idVolo + " ]";
    }
    
}
