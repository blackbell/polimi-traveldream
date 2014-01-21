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
@Entity
@DiscriminatorValue("visita")
@Table(name = "visite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visita.findAll", query = "SELECT v FROM Visita v"),
    @NamedQuery(name = "Visita.findByDataOra", query = "SELECT v FROM Visita v WHERE v.dataOra = :dataOra"),
    @NamedQuery(name = "Visita.findByNumeroPersone", query = "SELECT v FROM Visita v WHERE v.numeroPersone = :numeroPersone"),
    @NamedQuery(name = "Visita.findByCosto", query = "SELECT v FROM Visita v WHERE v.costo = :costo"),
    @NamedQuery(name = "Visita.findByParams", query = ""
            + "SELECT v "
            + "FROM "
            + "Museo m JOIN m.visiteCollection v "
            + "WHERE "
            + "(:nomeMuseo IS NULL OR m.nome = :nomeMuseo) AND "
            + "(:cittaMuseo IS NULL OR m.citta = :cittaMuseo) AND "
            + "(:giornoVisita IS NULL OR v.giornoVisita = :giornoVisita) AND "
            + "(:disabilitatiInclusi = True OR v.abilitato = True)")
})
@JsonTypeName("Visita")
public class Visita extends Voce implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private Museo museo;
    @Temporal(TemporalType.DATE)
    @Column(name = "giornoVisita")
    private Date giornoVisita;

    public Visita() {
        this.setTipo("Visita");
    }

    public Visita(Date dataOra, int numeroPersone, float costo) {
        this();
        this.dataOra = dataOra;
        this.numeroPersone = numeroPersone;
        this.costo = costo;
    }

    public Date getDataOra() {
        return dataOra;
    }
    
    public Date getGiornoVisita(){
        long time = getDataOra().getTime();
        time = ((int)time / (60*60*24))*(60*60*24);
        return new Date(time);
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

    public Museo getMuseo() {
        return museo;
    }

    public void setMuseo(Museo museo) {
        this.museo = museo;
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
        if (!(object instanceof Visita)) {
            return false;
        }
        Visita other = (Visita) object;
        if ((this.getIdVoce() == null && other.getIdVoce() != null) || (this.getIdVoce() != null && !this.getIdVoce().equals(other.getIdVoce()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.polimi.traveldream.model.Visita[ idVoce = " + getIdVoce() + " ]";
    }

}
