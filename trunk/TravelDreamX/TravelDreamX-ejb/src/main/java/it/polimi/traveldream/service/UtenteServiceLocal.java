/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Utente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rh0x
 */
@Local
public interface UtenteServiceLocal {
    public Utente registrazione(Utente utente);
    public Utente login(Utente u);

    public List<Utente> recuperaUtenti();

    public Boolean disattivaUtente(Utente utente);
    public Boolean attivaUtente(Utente utente);

    public Integer modificaLivello(Utente utente);
}
