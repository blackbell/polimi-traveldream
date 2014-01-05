/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "composizione")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Composizione.findAll", query = "SELECT c FROM Composizione c"),
    @NamedQuery(name = "Composizione.findByIdPacchetto", query = "SELECT c FROM Composizione c WHERE c.composizionePK.idPacchetto = :idPacchetto"),
    @NamedQuery(name = "Composizione.findByIdVoce", query = "SELECT c FROM Composizione c WHERE c.composizionePK.idVoce = :idVoce")})
public class Composizione implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ComposizionePK composizionePK;
    @JoinColumn(name = "idPacchetto", referencedColumnName = "idPacchetto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pacchetto pacchetti;
    @JoinColumn(name = "idVoce", referencedColumnName = "idVoce", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Voce voci;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "composizione")
    private Collection<Pagamento> pagamentiCollection;

    public Composizione() {
    }

    public Composizione(ComposizionePK composizionePK) {
        this.composizionePK = composizionePK;
    }

    public Composizione(int idPacchetto, int idVoce) {
        this.composizionePK = new ComposizionePK(idPacchetto, idVoce);
    }

    public ComposizionePK getComposizionePK() {
        return composizionePK;
    }

    public void setComposizionePK(ComposizionePK composizionePK) {
        this.composizionePK = composizionePK;
    }

    public Pacchetto getPacchetti() {
        return pacchetti;
    }

    public void setPacchetti(Pacchetto pacchetti) {
        this.pacchetti = pacchetti;
    }

    public Voce getVoci() {
        return voci;
    }

    public void setVoci(Voce voci) {
        this.voci = voci;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Pagamento> getPagamentiCollection() {
        return pagamentiCollection;
    }

    public void setPagamentiCollection(Collection<Pagamento> pagamentiCollection) {
        this.pagamentiCollection = pagamentiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (composizionePK != null ? composizionePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Composizione)) {
            return false;
        }
        Composizione other = (Composizione) object;
        if ((this.composizionePK == null && other.composizionePK != null) || (this.composizionePK != null && !this.composizionePK.equals(other.composizionePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.polimi.traveldream.model.Composizione[ composizionePK=" + composizionePK + " ]";
    }
    
}
