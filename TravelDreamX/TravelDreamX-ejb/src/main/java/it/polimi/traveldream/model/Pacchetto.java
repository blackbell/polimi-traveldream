/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;


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
    @NamedQuery(name = "Pacchetto.findByTipo", query = "SELECT p FROM Pacchetto p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Pacchetto.findByParams", query = ""
            + "SELECT p "
            + "FROM "
            + "Pacchetto p "
            + "WHERE "
            + "(:nome IS NULL OR p.nome LIKE :nome) AND "
            + "(:cittaAlbergo IS NULL OR p.cittaAlbergo LIKE :cittaAlbergo) AND "
            + "(:dataInizio IS NULL OR (p.giornoInizio BETWEEN :dataInizio AND :dataInizio2)) AND "
            + "(:dataFine IS NULL OR p.giornoFine >= :dataFine) AND "
            + "(:nazionePartenza IS NULL OR p.nazionePartenza LIKE :nazionePartenza) AND "
            + "(:nazioneArrivo IS NULL OR p.nazioneArrivo LIKE :nazioneArrivo) AND "
            + "(:disabilitatiInclusi = True OR p.abilitato = True) "
            + ""),
})
public class Pacchetto implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int MAX_NRO_VOCI = 10;
    public static final int GIFT_LIST = 2;
    public static final int PREDEFINITO = 0;
    public static final int PERSONALIZZATO = 1;
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
    private Integer tipo;
    @NotNull
    private String nome = "Pacchetto senza nome";   
    @NotNull
    private Boolean abilitato;
    @NotNull
    private Integer numeroPersone;
    
    @ManyToMany(fetch = FetchType.EAGER)//, cascade = CascadeType.PERSIST)
    @JoinTable(name = "composizione", joinColumns = {
        @JoinColumn(name = "idPacchetto", nullable = false, updatable = false) 
    },inverseJoinColumns = {
        @JoinColumn(name = "idVoce", nullable = false, updatable = false)
    })
    private Collection<Voce> voci;
    
    @JoinColumn(name = "proprietario", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Utente proprietario;

    public Pacchetto() {
    }

    public Pacchetto(Integer idPacchetto) {
        this.idPacchetto = idPacchetto;
    }

    public Pacchetto(Integer idPacchetto, Date dataOraCreazione, Integer tipo) {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataOraCreazione() {
        return dataOraCreazione;
    }

    public void setDataOraCreazione(Date dataOraCreazione) {
        this.dataOraCreazione = dataOraCreazione;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Utente getProprietario() {
        return proprietario;
    }

    public void setProprietario(Utente proprietario) {
        this.proprietario = proprietario;
    }

    public Collection<Voce> getVoci() {
        return voci;
    }

    public void setVoci(Collection<Voce> voci) {
        this.voci = voci;
    }

    public Boolean isAbilitato() {
        return abilitato;
    }

    public void setAbilitato(Boolean abilitato) {
        this.abilitato = abilitato;
    }

    public Integer getNumeroPersone() {
        return numeroPersone;
    }

    public void setNumeroPersone(Integer numeroPersone) {
        this.numeroPersone = numeroPersone;
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
 
    public static boolean nroVociNonConfome(Pacchetto pv){
        return (pv.getVoci().size() > 0 && pv.getVoci().size() <= Pacchetto.MAX_NRO_VOCI);
    }
    
    private String nazionePartenza;
    private String nazioneArrivo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date giornoInizio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date giornoFine;
    private String cittaAlbergo;

    public String getNazionePartenza() {
        return nazionePartenza;
    }

    public void setNazionePartenza(String nazionePartenza) {
        this.nazionePartenza = nazionePartenza;
    }

    public String getNazioneArrivo() {
        return nazioneArrivo;
    }

    public void setNazioneArrivo(String nazioneArrivo) {
        this.nazioneArrivo = nazioneArrivo;
    }

    public Date getGiornoInizio() {
        return giornoInizio;
    }

    public void setGiornoInizio(Date giornoInizio) {
        long time = giornoInizio.getTime();
        time = ((long)((long)time / (long)(1000)))*(1000); 
        this.giornoInizio = new Date(time);
    }

    public Date getGiornoFine() {
        return giornoFine;
    }

    public void setGiornoFine(Date giornoFine) {
        this.giornoFine = giornoFine;
    }

    public String getCittaAlbergo() {
        return cittaAlbergo;
    }

    public void setCittaAlbergo(String nomeAlbergo) {
        this.cittaAlbergo = nomeAlbergo;
    }
    
}
