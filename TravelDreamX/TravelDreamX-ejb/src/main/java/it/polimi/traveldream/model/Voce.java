/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Dario
 */
@Entity
@Table(name = "voci")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo", discriminatorType = DiscriminatorType.STRING)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voce.findAll", query = "SELECT v FROM Voce v"),
    @NamedQuery(name = "Voce.findByIdVoce", query = "SELECT v FROM Voce v WHERE v.idVoce = :idVoce"),
//    @NamedQuery(name = "Voce.findByTipo", query = "SELECT v FROM Voce v WHERE v.tipo = :tipo")
    })
public class Voce implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voci")
    private Collection<Composizione> composizioneCollection;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVoce")
    private Integer idVoce;
    
    public Voce() {
    }

    public Voce(Integer idVoce) {
        this.idVoce = idVoce;
    }

    public Voce(Integer idVoce, String tipo) {
        this.idVoce = idVoce;
    }

    public Integer getIdVoce() {
        return idVoce;
    }

    public void setIdVoce(Integer idVoce) {
        this.idVoce = idVoce;
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
        return "it.polimi.traveldream.model.Voce[ idVoce=" + idVoce + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Composizione> getComposizioneCollection() {
        return composizioneCollection;
    }

    public void setComposizioneCollection(Collection<Composizione> composizioneCollection) {
        this.composizioneCollection = composizioneCollection;
    }
    
}
