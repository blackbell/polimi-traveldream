/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Volo;
import javax.ejb.Local;

/**
 *
 * @author Dario
 */
@Local
public interface PBServiceLocal {
    public Volo saveVolo(Volo v);
    public Volo getVoloByID(int id);
}
