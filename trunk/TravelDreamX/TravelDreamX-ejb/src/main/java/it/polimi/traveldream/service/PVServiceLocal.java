/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Voce;
import javax.ejb.Local;

/**
 *
 * @author Dario
 */
@Local
public interface PVServiceLocal {
    
    public Pacchetto addPBtoPV(Voce pb, Pacchetto pv);

    public Pacchetto recuperaPB(Integer idPV);
    public Pacchetto salvaPV(Pacchetto pv);
    
    public boolean attivaPV(Integer idPV);
    public boolean disattivaPV(Integer idPV);
}