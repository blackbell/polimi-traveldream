/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Soggiorno;
import it.polimi.traveldream.model.Visita;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.model.Volo;
import java.util.List;
import javax.ejb.Local;

/**
 * EJB che offre i servizi per trattare gli oggetti di tipo Voce
 * @author Dario
 */
@Local
public interface PBServiceLocal {
    
    /**
     * Trova i PB memorizzati sulla base dei parametri in ingresso.
     * @param param è l'oggetto che racchiude tutti i parametri di ricerca. 
     * Nel caso in cui un parametro non sia da considerare è necessario che 
     * il relativo campo abbia valore null, altrimenti viene incluso nella ricerca.
     * @return Una lista contenente i PB conformi ai parametri specificati
     * o <i>null</i> nel caso in cui non esista alcun PB conforme 
     * ai criteri di ricerca o nel caso in cui non sia stato specificato il Tipo
     * di PB da cercare.
     */
    public List<Voce> trovaPB(ParametriRicercaPB param);
    
    /**
     * Memorizza un PB
     * @param voce
     * @return la voce salvata nel caso l'operazione vada a buon fine 
     * o <i>null</i> altrimenti.
     */
    public Voce salvaPB(Voce voce) ;
    
    /**
     * Memorizza un Volo nel DB
     * @param v il volo da memorizzare
     * @return l'oggetto Volo memorizzato o <i>null</i> nel caso l'operazione
     * non vada a buon fine.
     */
    @Deprecated
    public Volo saveVolo(Volo v);
    
    /**
     * Resituisce un Volo sulla base dell'ID specificato
     * @param id
     * @return un oggetto di tipo Volo o <i>null</i> nel caso in cui non esista
     * un volo con l'ID specificato.
     */
    public Volo getVoloByID(int id);

    /**
     * Memorizza un Soggiorno nel DB
     * @param s il soggiorno da memorizzare
     * @return l'oggetto Soggiorno memorizzato o <i>null</i> nel caso l'operazione
     * non vada a buon fine.
     */
    @Deprecated
    public Soggiorno saveSoggiorno(Soggiorno s);
    
    /**
     * Resituisce un Soggiorno sulla base dell'ID specificato
     * @param id
     * @return un oggetto di tipo Soggiorno o <i>null</i> nel caso in cui non esista
     * un Soggiorno con l'ID specificato.
     */
    public Soggiorno getSoggiornoByID(int id);
    
    /**
     * Memorizza una Visita nel DB
     * @param v la visita da memorizzare
     * @return l'oggetto Visita memorizzato o <i>null</i> nel caso l'operazione
     * non vada a buon fine.
     */
    @Deprecated
    public Visita saveVisita(Visita v);
    
    /**
     * Resituisce una Visita sulla base dell'ID specificato
     * @param id
     * @return un oggetto di tipo Visita o <i>null</i> nel caso in cui non esista
     * una Visita con l'ID specificato.
     */
    public Visita getVisitaByID(int id);
    
    /**
     * Imposta un PV come "attivo"
     * @param idPB
     * @return un Boolean che rappresenta il valore dello stato del PB 
     * dopo l'operazione o null nel caso in cui non esista 
     * un PB salvato con l'ID specificato
     */
    public Boolean attivaPB(Integer idPB);

    /**
     * Imposta un PV come "disattivo"
     * @param idPB
     * @return un Boolean che rappresenta il valore dello stato del PB 
     * dopo l'operazione o null nel caso in cui non esista 
     * un PB salvato con l'ID specificato
     */
    public Boolean disattivaPB(Integer idPB);
}
