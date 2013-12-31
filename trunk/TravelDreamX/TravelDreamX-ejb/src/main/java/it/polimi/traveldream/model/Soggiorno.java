/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author rh0x
 */
@Entity
@Table(name = "SOGGIORNI")
@NamedQueries({
    @NamedQuery(name = "Soggiorno.findAll", query = "SELECT s FROM Soggiorno s"),
    @NamedQuery(name = "Soggiorno.findByIdSoggiorno", query = "SELECT s FROM Soggiorno s WHERE s.idSoggiorno = :idSoggiorno"),
    @NamedQuery(name = "Soggiorno.findByGiornoInizio", query = "SELECT s FROM Soggiorno s WHERE s.giornoInizio = :giornoInizio"),
    @NamedQuery(name = "Soggiorno.findByGiornoFine", query = "SELECT s FROM Soggiorno s WHERE s.giornoFine = :giornoFine"),
    @NamedQuery(name = "Soggiorno.findByNumeroPersone", query = "SELECT s FROM Soggiorno s WHERE s.numeroPersone = :numeroPersone"),
    @NamedQuery(name = "Soggiorno.findByCosto", query = "SELECT s FROM Soggiorno s WHERE s.costo = :costo")})
public class Soggiorno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSoggiorno")
    private Integer idSoggiorno;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRelativo2")
    private Collection<Voce> voceCollection;
    @JoinColumn(name = "idAlbergo", referencedColumnName = "idAlbergo")
    @ManyToOne(optional = false)
    private Albergo idAlbergo;

    public Soggiorno() {
    }

    public Soggiorno(Integer idSoggiorno) {
        this.idSoggiorno = idSoggiorno;
    }

    public Soggiorno(Integer idSoggiorno, Date giornoInizio, Date giornoFine, int numeroPersone, float costo) {
        this.idSoggiorno = idSoggiorno;
        this.giornoInizio = giornoInizio;
        this.giornoFine = giornoFine;
        this.numeroPersone = numeroPersone;
        this.costo = costo;
    }

    public Integer getIdSoggiorno() {
        return idSoggiorno;
    }

    public void setIdSoggiorno(Integer idSoggiorno) {
        this.idSoggiorno = idSoggiorno;
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

    public Collection<Voce> getVoceCollection() {
        return voceCollection;
    }

    public void setVoceCollection(Collection<Voce> voceCollection) {
        this.voceCollection = voceCollection;
    }

    public Albergo getIdAlbergo() {
        return idAlbergo;
    }

    public void setIdAlbergo(Albergo idAlbergo) {
        this.idAlbergo = idAlbergo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSoggiorno != null ? idSoggiorno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Soggiorno)) {
            return false;
        }
        Soggiorno other = (Soggiorno) object;
        if ((this.idSoggiorno == null && other.idSoggiorno != null) || (this.idSoggiorno != null && !this.idSoggiorno.equals(other.idSoggiorno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.polimi.traveldream.model.prova.Soggiorno[ idSoggiorno=" + idSoggiorno + " ]";
    }
    
}
