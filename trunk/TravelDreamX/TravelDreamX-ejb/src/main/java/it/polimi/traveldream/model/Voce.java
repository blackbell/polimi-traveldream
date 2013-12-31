/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author rh0x
 */
@Entity
@Table(name = "VOCI")
@NamedQueries({
    @NamedQuery(name = "Voce.findAll", query = "SELECT v FROM Voce v"),
    @NamedQuery(name = "Voce.findByIdVoce", query = "SELECT v FROM Voce v WHERE v.idVoce = :idVoce"),
    @NamedQuery(name = "Voce.findByTipo", query = "SELECT v FROM Voce v WHERE v.tipo = :tipo")})
public class Voce implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVoce")
    private Integer idVoce;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voce")
    private Collection<Composizione> composizioneCollection;
    @JoinColumn(name = "idRelativo", referencedColumnName = "idVisita")
    @ManyToOne(optional = false)
    private Visita idRelativo;
    @JoinColumn(name = "idRelativo", referencedColumnName = "idVolo")
    @ManyToOne(optional = false)
    private Volo idRelativo1;
    @JoinColumn(name = "idRelativo", referencedColumnName = "idSoggiorno")
    @ManyToOne(optional = false)
    private Soggiorno idRelativo2;

    public Voce() {
    }

    public Voce(Integer idVoce) {
        this.idVoce = idVoce;
    }

    public Voce(Integer idVoce, String tipo) {
        this.idVoce = idVoce;
        this.tipo = tipo;
    }

    public Integer getIdVoce() {
        return idVoce;
    }

    public void setIdVoce(Integer idVoce) {
        this.idVoce = idVoce;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Collection<Composizione> getComposizioneCollection() {
        return composizioneCollection;
    }

    public void setComposizioneCollection(Collection<Composizione> composizioneCollection) {
        this.composizioneCollection = composizioneCollection;
    }

    public Visita getIdRelativo() {
        return idRelativo;
    }

    public void setIdRelativo(Visita idRelativo) {
        this.idRelativo = idRelativo;
    }

    public Volo getIdRelativo1() {
        return idRelativo1;
    }

    public void setIdRelativo1(Volo idRelativo1) {
        this.idRelativo1 = idRelativo1;
    }

    public Soggiorno getIdRelativo2() {
        return idRelativo2;
    }

    public void setIdRelativo2(Soggiorno idRelativo2) {
        this.idRelativo2 = idRelativo2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVoce != null ? idVoce.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voce)) {
            return false;
        }
        Voce other = (Voce) object;
        if ((this.idVoce == null && other.idVoce != null) || (this.idVoce != null && !this.idVoce.equals(other.idVoce))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.polimi.traveldream.model.prova.Voce[ idVoce=" + idVoce + " ]";
    }
    
}
