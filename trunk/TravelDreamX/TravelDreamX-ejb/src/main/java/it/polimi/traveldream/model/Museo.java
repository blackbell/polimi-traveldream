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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Dario
 */
@Entity
@Table(name = "musei")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Museo.findAll", query = "SELECT m FROM Museo m"),
    @NamedQuery(name = "Museo.findByIdMuseo", query = "SELECT m FROM Museo m WHERE m.idMuseo = :idMuseo"),
    @NamedQuery(name = "Museo.findByNome", query = "SELECT m FROM Museo m WHERE m.nome = :nome"),
    @NamedQuery(name = "Museo.findByCitta", query = "SELECT m FROM Museo m WHERE m.citta = :citta"),
    @NamedQuery(name = "Museo.findByDescrizione", query = "SELECT m FROM Museo m WHERE m.descrizione = :descrizione"),
    @NamedQuery(name = "Museo.findByUrlFoto", query = "SELECT m FROM Museo m WHERE m.urlFoto = :urlFoto")})
public class Museo extends EDB implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMuseo")
    private Integer idMuseo;
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
    @Size(min = 1, max = 250)
    @Column(name = "descrizione")
    private String descrizione;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "urlFoto")
    private String urlFoto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMuseo")
    private Collection<Visita> visiteCollection;

    public Museo() {
    }

    public Museo(Integer idMuseo) {
        this.idMuseo = idMuseo;
    }

    public Museo(Integer idMuseo, String nome, String citta, String descrizione, String urlFoto) {
        this.idMuseo = idMuseo;
        this.nome = nome;
        this.citta = citta;
        this.descrizione = descrizione;
        this.urlFoto = urlFoto;
    }

    public Integer getIdMuseo() {
        return idMuseo;
    }

    public void setIdMuseo(Integer idMuseo) {
        this.idMuseo = idMuseo;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Visita> getVisiteCollection() {
        return visiteCollection;
    }

    public void setVisiteCollection(Collection<Visita> visiteCollection) {
        this.visiteCollection = visiteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMuseo != null ? idMuseo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Museo)) {
            return false;
        }
        Museo other = (Museo) object;
        if ((this.idMuseo == null && other.idMuseo != null) || (this.idMuseo != null && !this.idMuseo.equals(other.idMuseo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.polimi.traveldream.model.Museo[ idMuseo=" + idMuseo + " ]";
    }
    
}
