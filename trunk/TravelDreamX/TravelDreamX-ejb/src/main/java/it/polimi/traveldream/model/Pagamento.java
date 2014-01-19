/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "pagamenti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p"),
    @NamedQuery(name = "Pagamento.findByIdPagamento", query = "SELECT p FROM Pagamento p WHERE p.idPagamento = :idPagamento"),
    @NamedQuery(name = "Pagamento.findByDataOraPagamento", query = "SELECT p FROM Pagamento p WHERE p.dataOraPagamento = :dataOraPagamento")})
public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPagamento")
    private Integer idPagamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_ora_pagamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataOraPagamento;
    /*@JoinColumns({
        @JoinColumn(name = "idVoce", referencedColumnName = "idVoce", insertable = false, updatable = false),//),
        @JoinColumn(name = "idPagamento", referencedColumnName = "idPacchetto", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    */private Voce voce;
    @JoinColumn(name = "idUtente", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private Utente idUtente;

    public Pagamento() {
    }

    public Pagamento(Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Pagamento(Integer idPagamento, Date dataOraPagamento) {
        this.idPagamento = idPagamento;
        this.dataOraPagamento = dataOraPagamento;
    }

    public Integer getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Date getDataOraPagamento() {
        return dataOraPagamento;
    }

    public void setDataOraPagamento(Date dataOraPagamento) {
        this.dataOraPagamento = dataOraPagamento;
    }

    public Voce getVoce() {
        return voce;
    }

    public void setVoce(Voce voce) {
        this.voce = voce;
    }

    public Utente getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Utente idUtente) {
        this.idUtente = idUtente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPagamento != null ? idPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.idPagamento == null && other.idPagamento != null) || (this.idPagamento != null && !this.idPagamento.equals(other.idPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.polimi.traveldream.model.Pagamento[ idPagamento=" + idPagamento + " ]";
    }
    
}
