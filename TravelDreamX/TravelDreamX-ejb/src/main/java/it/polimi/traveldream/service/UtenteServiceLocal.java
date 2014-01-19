/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Utente;
import javax.ejb.Local;

/**
 *
 * @author rh0x
 */
@Local
public interface UtenteServiceLocal {
    public Utente registrazione(Utente utente);
    public Utente login(Utente u);
}
