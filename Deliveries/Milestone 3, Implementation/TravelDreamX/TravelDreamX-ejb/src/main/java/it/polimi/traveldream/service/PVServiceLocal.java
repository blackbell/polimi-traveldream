/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Voce;
import java.util.List;
import javax.ejb.Local;

/**
 * EJB che offre i servizi per trattare oggetti di tipo Pacchetto
 * @author Dario
 */
@Local
public interface PVServiceLocal {
    
    public Pacchetto addPBtoPV(Voce pb, Pacchetto pv);

    /**
     * Trova i PV memorizzati sulla base dei parametri in ingresso.
     * @param params è l'oggetto che racchiude tutti i parametri di ricerca. 
     * Nel caso in cui un parametro non sia da considerare è necessario che 
     * il relativo campo abbia valore null, altrimenti viene incluso nella ricerca.
     * Se il campo ID è impostato allora ogni altro parametro viene ignorato e 
     * viene eseguito il recupero di uno specifico PV.
     * @return Una lista contenente i pacchetti conformi ai parametri specificati
     * o <i>null</i> nel caso in cui non esista alcun pacchetto conforme 
     * ai criteri di ricerca o nel caso in cui non esista il pacchetto con l'ID indicato.
     */
    public List<Pacchetto> trovaPV(ParametriRicercaPV params);

    /**
     * Restituisce un PV basandosi sul suo ID.
     * @param idPV
     * @return il PV nel caso esista o <i>null</i> altrimenti
     */
    @Deprecated
    public Pacchetto recuperaPV(Integer idPV);
    
    /**
     * Memorizza un PV
     * @param pv
     * @return 
     * <i>null</i> nel caso in cui la collection di voci di pv.voci sia null, 
     * nel caso in cui pv.voci sia vuota o 
     * nel caso in cui pv.voci contenga più di Pacchetto.MAX_NRO_VOCI elementi.
     * <br />
     * Il pacchetto salvato altrimenti
     * 
     */
    public Pacchetto salvaPV(Pacchetto pv);
    
    /**
     * Imposta un PV come "attivo"
     * @param idPV
     * @return un Boolean che rappresenta il valore dello stato del PV 
     * dopo l'operazione o null nel caso in cui non esista 
     * un pacchetto salvato con l'ID specificato
     */
    public Boolean attivaPV(Integer idPV);
    
    /**
     * Imposta un PV come "disattivo"
     * @param idPV
     * @return un Boolean che rappresenta il valore dello stato del PV 
     * dopo l'operazione o null nel caso in cui non esista 
     * un pacchetto salvato con l'ID specificato
     */
    public Boolean disattivaPV(Integer idPV);
}
