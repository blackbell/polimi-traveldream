/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Utente;
import java.util.List;
import javax.ejb.Local;

/**
 * EJB che offre i servizi per trattare gli oggetti di tipo Utente
 * @author rh0x
 */
@Local
public interface UtenteServiceLocal {
    
    /**
     * Crea l'utenza nel DB per l'utente specificato.
     * @param utente
     * @return l'oggetto Utente salvato oppure <i>null</i> nel caso in cui 
     * l'utente sia già presente.
     */
    public Utente registrazione(Utente utente);
    
    /**
     * Verifica se l'utente con l'email specificata esiste nel DB e lo restiuisce.
     * @param u
     * @return l'oggetto Utente recuperato dal DB o <i>null</i> nel caso in cui
     * l'utenza richiesta non esista.
     */
    public Utente login(Utente u);

    /**
     * Restiuisce l'elenco degli utenti del sistema.
     * @return una lista di oggetti Utente.
     */
    public List<Utente> recuperaUtenti();

    /**
     * Imposta un PV come "disattivo"
     * @param idPV
     * @return un Boolean che rappresenta il valore dello stato del PV 
     * dopo l'operazione o <i>null</i> nel caso in cui non esista 
     * un pacchetto salvato con l'ID specificato
     */
    
    /**
     * Imposta un utente come "disattivo"
     * @param utente l'utente da disattivare
     * @return un Boolean che rappresenta il valore dello stato dell'utente
     * dopo l'operazione o <i>null</i> nel caso in cui non esista l'utenza 
     * specificata.
     */
    public Boolean disattivaUtente(Utente utente);
    
    /**
     * Imposta un utente come "attivo"
     * @param utente l'utente da attivare
     * @return un Boolean che rappresenta il valore dello stato dell'utente
     * dopo l'operazione o <i>null</i> nel caso in cui non esista l'utenza 
     * specificata.
     */
    public Boolean attivaUtente(Utente utente);

    /**
     * Imposta il livello di un utente. 
     * @param utente l'utente con il nuovo livello impostato
     * @return un Integer che indica qual è il livello dell'utente dopo l'operazione
     * o <i>null</i> nel caso in cui non esista l'utenza specificata.
     */
    public Integer modificaLivello(Utente utente);
}
