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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Dario
 */
@Entity
@Table(name = "visite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visite.findAll", query = "SELECT v FROM Visite v"),
    @NamedQuery(name = "Visite.findByIdVisita", query = "SELECT v FROM Visite v WHERE v.idVisita = :idVisita"),
    @NamedQuery(name = "Visite.findByDataOra", query = "SELECT v FROM Visite v WHERE v.dataOra = :dataOra"),
    @NamedQuery(name = "Visite.findByNumeroPersone", query = "SELECT v FROM Visite v WHERE v.numeroPersone = :numeroPersone"),
    @NamedQuery(name = "Visite.findByCosto", query = "SELECT v FROM Visite v WHERE v.costo = :costo")})
public class Visita implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVisita")
    private Integer idVisita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataOra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataOra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroPersone")
    private int numeroPersone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private float costo;
    @JoinColumn(name = "idMuseo", referencedColumnName = "idMuseo")
    @ManyToOne(optional = false)
    private Museo idMuseo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRelativo")
    private Collection<Voce> vociCollection;

    public Visita() {
    }

    public Visita(Integer idVisita) {
        this.idVisita = idVisita;
    }

    public Visita(Integer idVisita, Date dataOra, int numeroPersone, float costo) {
        this.idVisita = idVisita;
        this.dataOra = dataOra;
        this.numeroPersone = numeroPersone;
        this.costo = costo;
    }

    public Integer getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(Integer idVisita) {
        this.idVisita = idVisita;
    }

    public Date getDataOra() {
        return dataOra;
    }

    public void setDataOra(Date dataOra) {
        this.dataOra = dataOra;
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

    public Museo getIdMuseo() {
        return idMuseo;
    }

    public void setIdMuseo(Museo idMuseo) {
        this.idMuseo = idMuseo;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Voce> getVociCollection() {
        return vociCollection;
    }

    public void setVociCollection(Collection<Voce> vociCollection) {
        this.vociCollection = vociCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVisita != null ? idVisita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visita)) {
            return false;
        }
        Visita other = (Visita) object;
        if ((this.idVisita == null && other.idVisita != null) || (this.idVisita != null && !this.idVisita.equals(other.idVisita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.polimi.traveldream.model.Visite[ idVisita=" + idVisita + " ]";
    }
    
}