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
 *
 * @author Dario
 */
@Local
public interface EDBServiceLocal {
    
    public List<EDB> trovaEntità(ParametriRicercaEDB param);
    public Rotta salvaRotta(Rotta r);
    public Rotta retrieveRottaByID(int id);
    public Albergo salvaAlbergo(Albergo a);
    public Albergo retrieveAlbergoByID(int id);
    public Museo salvaMuseo(Museo m);
    public Museo retrieveMuseoByID(int id);
}
