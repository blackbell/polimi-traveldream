/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Pagamento;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.model.Voce;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Dario
 */
@Local
public interface PagamentoServiceLocal {
    
    public Pagamento pagamentoPV(Pacchetto pv, Utente u);
    public Pagamento pagamentoPB(Pacchetto pv, Voce pb, Utente u);
    
    public List<Pagamento> recuperaPagamenti(Date da, Date a, Utente u, Pacchetto pv);
    
}
