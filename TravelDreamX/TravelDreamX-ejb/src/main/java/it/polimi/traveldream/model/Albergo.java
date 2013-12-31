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
@Table(name = "ALBERGHI")
@NamedQueries({
    @NamedQuery(name = "Albergo.findAll", query = "SELECT a FROM Albergo a"),
    @NamedQuery(name = "Albergo.findByIdAlbergo", query = "SELECT a FROM Albergo a WHERE a.idAlbergo = :idAlbergo"),
    @NamedQuery(name = "Albergo.findByNome", query = "SELECT a FROM Albergo a WHERE a.nome = :nome"),
    @NamedQuery(name = "Albergo.findByCitta", query = "SELECT a FROM Albergo a WHERE a.citta = :citta"),
    @NamedQuery(name = "Albergo.findByStelle", query = "SELECT a FROM Albergo a WHERE a.stelle = :stelle"),
    @NamedQuery(name = "Albergo.findByUrlFoto", query = "SELECT a FROM Albergo a WHERE a.urlFoto = :urlFoto")})
public class Albergo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAlbergo")
    private Integer idAlbergo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "citta")
    private String citta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stelle")
    private int stelle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "urlFoto")
    private String urlFoto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlbergo")
    private Collection<Soggiorno> soggiornoCollection;

    public Albergo() {
    }

    public Albergo(Integer idAlbergo) {
        this.idAlbergo = idAlbergo;
    }

    public Albergo(Integer idAlbergo, String nome, String citta, int stelle, String urlFoto) {
        this.idAlbergo = idAlbergo;
        this.nome = nome;
        this.citta = citta;
        this.stelle = stelle;
        this.urlFoto = urlFoto;
    }

    public Integer getIdAlbergo() {
        return idAlbergo;
    }

    public void setIdAlbergo(Integer idAlbergo) {
        this.idAlbergo = idAlbergo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public int getStelle() {
        return stelle;
    }

    public void setStelle(int stelle) {
        this.stelle = stelle;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public Collection<Soggiorno> getSoggiornoCollection() {
        return soggiornoCollection;
    }

    public void setSoggiornoCollection(Collection<Soggiorno> soggiornoCollection) {
        this.soggiornoCollection = soggiornoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlbergo != null ? idAlbergo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Albergo)) {
            return false;
        }
        Albergo other = (Albergo) object;
        if ((this.idAlbergo == null && other.idAlbergo != null) || (this.idAlbergo != null && !this.idAlbergo.equals(other.idAlbergo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.polimi.traveldream.model.prova.Albergo[ idAlbergo=" + idAlbergo + " ]";
    }
    
}
