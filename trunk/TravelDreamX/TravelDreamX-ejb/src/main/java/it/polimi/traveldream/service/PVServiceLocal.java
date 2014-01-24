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
 *
 * @author Dario
 */
@Local
public interface PVServiceLocal {
    
    public Pacchetto addPBtoPV(Voce pb, Pacchetto pv);

    public List<Pacchetto> trovaPV(ParametriRicercaPV params);
    
    public Pacchetto recuperaPV(Integer idPV);
    public Pacchetto salvaPV(Pacchetto pv);
    
    public Boolean attivaPV(Integer idPV);
    public Boolean disattivaPV(Integer idPV);
}
