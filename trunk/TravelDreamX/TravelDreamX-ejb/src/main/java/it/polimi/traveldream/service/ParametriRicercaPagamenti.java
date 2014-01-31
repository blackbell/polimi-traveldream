/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Utente;
import java.util.Date;

/**
 * Incapsula tutti i criteri che è possibile specificare per la ricerca di un Pagamento. 
 * Se i campi dataInizio e dataFine non vengono settati, il servizio di ricerca li imposta 
 * (risp.) alla data di inizio del sistema ed all'istante di tempo in cui si effettua la ricerca.
 * @author Dario
 */
public class ParametriRicercaPagamenti {
    private Utente utente;
    private Date dataInizio;
    private Date dataFine;
    private Pacchetto pacchetto;

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        long time = dataInizio.getTime();
        time = ((long)time / (24*60*60*1000))*24*60*60*1000;
        this.dataInizio = new Date(time);
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        long time = dataInizio.getTime();
        time = (((long)time / (24*60*60*1000))+1)*24*60*60*1000;
        this.dataFine = new Date(time);
    }

    public Pacchetto getPacchetto() {
        return pacchetto;
    }

    public void setPacchetto(Pacchetto pacchetto) {
        this.pacchetto = pacchetto;
    }
    
    
}
