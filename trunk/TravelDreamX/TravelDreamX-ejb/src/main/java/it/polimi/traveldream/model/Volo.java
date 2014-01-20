/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
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
import org.codehaus.jackson.annotate.JsonTypeName;

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
    @NamedQuery(name = "Volo.findByRotta", query = "SELECT v FROM Volo v WHERE v.rotta = :rotta"),
    @NamedQuery(name = "Volo.findByDataOra", query = "SELECT v FROM Volo v WHERE v.dataOra = :dataOra"),
    @NamedQuery(name = "Volo.findByNumPasseggeri", query = "SELECT v FROM Volo v WHERE v.numPasseggeri = :numPasseggeri"),
    @NamedQuery(name = "Volo.findByCosto", query = "SELECT v FROM Volo v WHERE v.costo = :costo"),
    @NamedQuery(name = "Volo.findByParams", query = "SELECT v FROM Volo v WHERE "
                                                    + "(:rotta IS NULL OR v.rotta = :rotta) AND "
                                                    + "(:numPasseggeri IS NULL OR v.numPasseggeri = :numPasseggeri) AND "
                                                    + "(:dataOra IS NULL OR v.dataOra > :dataOra) "
                                                    + "")
})
@JsonTypeName("Volo")
public class Volo extends Voce implements Serializable {
  
    private static final long serialVersionUID = 1L;
    
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
    private Float costo;
    @JoinColumn(name = "idRotta", referencedColumnName = "idRotta")
    @ManyToOne(optional = false)
    private Rotta rotta;

    public Volo() {
        this.setTipo("Volo");
    }

    public Volo(Date dataOra, int numPasseggeri, float costo) {
        this();
        this.dataOra = dataOra;
        this.numPasseggeri = numPasseggeri;
        this.costo = costo;
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

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Rotta getRotta() {
        return rotta;
    }

    public void setRotta(Rotta rotta) {
        this.rotta = rotta;
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
        if (!(object instanceof Volo)) {
            return false;
        }
        Volo other = (Volo) object;
        if ((this.getIdVoce() == null && other.getIdVoce() != null) || (this.getIdVoce() != null && !this.getIdVoce().equals(other.getIdVoce()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.polimi.traveldream.model.Volo[ idVoce = " + getIdVoce() + " ]";
    }
    
}
