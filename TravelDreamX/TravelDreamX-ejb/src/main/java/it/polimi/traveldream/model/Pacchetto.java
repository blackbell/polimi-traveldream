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
@Table(name = "pacchetti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pacchetto.findAll", query = "SELECT p FROM Pacchetto p"),
    @NamedQuery(name = "Pacchetto.findByIdPacchetto", query = "SELECT p FROM Pacchetto p WHERE p.idPacchetto = :idPacchetto"),
    @NamedQuery(name = "Pacchetto.findByDataOraCreazione", query = "SELECT p FROM Pacchetto p WHERE p.dataOraCreazione = :dataOraCreazione"),
    @NamedQuery(name = "Pacchetto.findByTipo", query = "SELECT p FROM Pacchetto p WHERE p.tipo = :tipo")})
public class Pacchetto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPacchetto")
    private Integer idPacchetto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_ora_creazione")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataOraCreazione;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private TipoPacchetto tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacchetti")
    private Collection<Composizione> composizioneCollection;
    @JoinColumn(name = "proprietario", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Utente proprietario;

    public Pacchetto() {
    }

    public Pacchetto(Integer idPacchetto) {
        this.idPacchetto = idPacchetto;
    }

    public Pacchetto(Integer idPacchetto, Date dataOraCreazione, TipoPacchetto tipo) {
        this.idPacchetto = idPacchetto;
        this.dataOraCreazione = dataOraCreazione;
        this.tipo = tipo;
    }

    public Integer getIdPacchetto() {
        return idPacchetto;
    }

    public void setIdPacchetto(Integer idPacchetto) {
        this.idPacchetto = idPacchetto;
    }

    public Date getDataOraCreazione() {
        return dataOraCreazione;
    }

    public void setDataOraCreazione(Date dataOraCreazione) {
        this.dataOraCreazione = dataOraCreazione;
    }

    public TipoPacchetto getTipo() {
        return tipo;
    }

    public void setTipo(TipoPacchetto tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Composizione> getComposizioneCollection() {
        return composizioneCollection;
    }

    public void setComposizioneCollection(Collection<Composizione> composizioneCollection) {
        this.composizioneCollection = composizioneCollection;
    }

    public Utente getProprietario() {
        return proprietario;
    }

    public void setProprietario(Utente proprietario) {
        this.proprietario = proprietario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPacchetto != null ? idPacchetto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacchetto)) {
            return false;
        }
        Pacchetto other = (Pacchetto) object;
        if ((this.idPacchetto == null && other.idPacchetto != null) || (this.idPacchetto != null && !this.idPacchetto.equals(other.idPacchetto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.polimi.traveldream.model.Pacchetto[ idPacchetto=" + idPacchetto + " ]";
    }
    
}
