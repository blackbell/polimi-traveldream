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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Dario
 */
@Entity
@Table(name = "rotte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rotta.findAll", query = "SELECT r FROM Rotta r"),
    @NamedQuery(name = "Rotta.findByIdRotta", query = "SELECT r FROM Rotta r WHERE r.idRotta = :idRotta"),
    @NamedQuery(name = "Rotta.findByAeroportoPartenza", query = "SELECT r FROM Rotta r WHERE r.aeroportoPartenza = :aeroportoPartenza"),
    @NamedQuery(name = "Rotta.findByCittaPartenza", query = "SELECT r FROM Rotta r WHERE r.cittaPartenza = :cittaPartenza"),
    @NamedQuery(name = "Rotta.findByNazionePartenza", query = "SELECT r FROM Rotta r WHERE r.nazionePartenza = :nazionePartenza"),
    @NamedQuery(name = "Rotta.findByAeroportoArrivo", query = "SELECT r FROM Rotta r WHERE r.aeroportoArrivo = :aeroportoArrivo"),
    @NamedQuery(name = "Rotta.findByCittaArrivo", query = "SELECT r FROM Rotta r WHERE r.cittaArrivo = :cittaArrivo"),
    @NamedQuery(name = "Rotta.findByNazioneArrivo", query = "SELECT r FROM Rotta r WHERE r.nazioneArrivo = :nazioneArrivo"),
    @NamedQuery(name = "Rotta.findByCompagniaAerea", query = "SELECT r FROM Rotta r WHERE r.compagniaAerea = :compagniaAerea"),
    @NamedQuery(name = "Rotta.findByParams", query = "SELECT r FROM Rotta r WHERE r.aeroportoPartenza LIKE :aeroportoPartenza AND "
                                                    + "r.aeroportoArrivo LIKE :aeroportoArrivo AND "
                                                    + "r.compagniaAerea LIKE :compagniaAerea AND "
                                                    + "r.cittaPartenza LIKE :cittaPartenza AND "
                                                    + "r.nazionePartenza LIKE :nazionePartenza AND "
                                                    + "r.cittaArrivo LIKE :cittaArrivo AND "
                                                    + "r.nazioneArrivo LIKE :nazioneArrivo")
})
public class Rotta extends EDB implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRotta")
    private Integer idRotta;
    @Size(max = 60)
    @Basic(optional = false)
    @Column(name = "aeroportoPartenza")
    private String aeroportoPartenza;
    @Size(max = 60)
    @Basic(optional = false)
    @Column(name = "cittaPartenza")
    private String cittaPartenza;
    @Size(max = 45)
    @Basic(optional = false)
    @Column(name = "nazionePartenza")
    private String nazionePartenza;
    @Size(max = 60)
    @Basic(optional = false)
    @Column(name = "aeroportoArrivo")
    private String aeroportoArrivo;
    @Size(max = 60)
    @Basic(optional = false)
    @Column(name = "cittaArrivo")
    private String cittaArrivo;
    @Size(max = 45)
    @Basic(optional = false)
    @Column(name = "nazioneArrivo")
    private String nazioneArrivo;
    @Size(max = 45)
    @Basic(optional = false)
    @Column(name = "compagniaAerea")
    private String compagniaAerea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rotta")
    private Collection<Volo> voliCollection;

    public Rotta() {
    }

    public Rotta(Integer idRotta) {
        this.idRotta = idRotta;
    }

    public Integer getIdRotta() {
        return idRotta;
    }

    public void setIdRotta(Integer idRotta) {
        this.idRotta = idRotta;
    }

    public String getAeroportoPartenza() {
        return aeroportoPartenza;
    }

    public void setAeroportoPartenza(String aeroportoPartenza) {
        this.aeroportoPartenza = aeroportoPartenza;
    }

    public String getCittaPartenza() {
        return cittaPartenza;
    }

    public void setCittaPartenza(String cittaPartenza) {
        this.cittaPartenza = cittaPartenza;
    }

    public String getNazionePartenza() {
        return nazionePartenza;
    }

    public void setNazionePartenza(String nazionePartenza) {
        this.nazionePartenza = nazionePartenza;
    }

    public String getAeroportoArrivo() {
        return aeroportoArrivo;
    }

    public void setAeroportoArrivo(String aeroportoArrivo) {
        this.aeroportoArrivo = aeroportoArrivo;
    }

    public String getCittaArrivo() {
        return cittaArrivo;
    }

    public void setCittaArrivo(String cittaArrivo) {
        this.cittaArrivo = cittaArrivo;
    }

    public String getNazioneArrivo() {
        return nazioneArrivo;
    }

    public void setNazioneArrivo(String nazioneArrivo) {
        this.nazioneArrivo = nazioneArrivo;
    }

    public String getCompagniaAerea() {
        return compagniaAerea;
    }

    public void setCompagniaAerea(String compagniaAerea) {
        this.compagniaAerea = compagniaAerea;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Volo> getVoliCollection() {
        return voliCollection;
    }

    public void setVoliCollection(Collection<Volo> voliCollection) {
        this.voliCollection = voliCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRotta != null ? idRotta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rotta)) {
            return false;
        }
        Rotta other = (Rotta) object;
        if ((this.idRotta == null && other.idRotta != null) || (this.idRotta != null && !this.idRotta.equals(other.idRotta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.polimi.traveldream.model.Rotta[ idRotta=" + idRotta + " ]";
    }
    
}
