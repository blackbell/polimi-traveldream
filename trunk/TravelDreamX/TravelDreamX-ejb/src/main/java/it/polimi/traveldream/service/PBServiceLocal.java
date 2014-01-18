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
 *
 * @author Dario
 */
@Local
public interface PBServiceLocal {
    public List<Voce> trovaPB(ParametriRicercaPB params);
    
    public Volo saveVolo(Volo v);
    public Volo getVoloByID(int id);

    public Soggiorno saveSoggiorno(Soggiorno s);
    public Soggiorno getSoggiornoByID(int id);
    
    public Visita saveVisita(Visita v);
    public Visita getVisitaByID(int id);
}
