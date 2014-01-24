/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.EDB;
import it.polimi.traveldream.model.Museo;
import it.polimi.traveldream.model.Rotta;
import java.util.List;
import javax.ejb.Local;

/**
 * EJB che offre i servizi per trattare gli oggetti di tipo "Entità Di Base"
 * @author Dario
 */
@Local
public interface EDBServiceLocal {
    
    /**
     * Trova gli EDB memorizzati sulla base dei parametri in ingresso.
     * @param param è l'oggetto che racchiude tutti i parametri di ricerca. 
     * Nel caso in cui un parametro non sia da considerare è necessario che 
     * il relativo campo abbia valore null, altrimenti viene incluso nella ricerca.
     * @return Una lista contenente le EDB conformi ai parametri specificati
     * o <i>null</i> nel caso in cui non esista alcuna EDB conforme 
     * ai criteri di ricerca o nel caso in cui non sia stato specificato il Tipo
     * di EDB da cercare.
     */
    public List<EDB> trovaEntità(ParametriRicercaEDB param);
    
    /**
     * Memorizza una rotta
     * @param r la rotta da salvare nel DB
     * @return la rotta salvata o <i>null</i> nel caso in cui l'operazione non 
     * sia andata a buon fine.
     */
    public Rotta salvaRotta(Rotta r);
    
    /**
     * Resituisce una rotta sulla base dell'ID specificato
     * @param id
     * @return un oggetto di tipo Rotta o <i>null</i> nel caso in cui non esista
     * una rotta con l'ID specificato.
     */
    public Rotta retrieveRottaByID(int id);
    
    /**
     * Memorizza un albergo
     * @param a l'albergo da salvare nel DB
     * @return l'albergo salvato o <i>null</i> nel caso in cui l'operazione non 
     * sia andata a buon fine.
     */
    public Albergo salvaAlbergo(Albergo a);
    
    /**
     * Resituisce un albergo sulla base dell'ID specificato
     * @param id
     * @return un oggetto di tipo Albergo o <i>null</null> nel caso in cui non esista
     * un albergo con l'ID specificato.
     */
    public Albergo retrieveAlbergoByID(int id);
    
    /**
     * Memorizza un museo
     * @param m il museo da salvare nel DB
     * @return il museo salvato o <i>null</i> nel caso in cui l'operazione non 
     * sia andata a buon fine.
     */
    public Museo salvaMuseo(Museo m);
    
    /**
     * Resituisce un museo sulla base dell'ID specificato
     * @param id
     * @return un oggetto di tipo Museo o <i>null</i> nel caso in cui non esista
     * un museo con l'ID specificato.
     */
    public Museo retrieveMuseoByID(int id);
}
