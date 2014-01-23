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
import javax.ejb.Stateless;

/**
 *
 * @author Dario
 */
@Stateless
public class PagamentoService implements PagamentoServiceLocal {

    @Override
    public Pagamento pagamentoPV(Pacchetto pv, Utente u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pagamento pagamentoPB(Pacchetto pv, Voce pb, Utente u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public List<Pagamento> recuperaPagamenti(Date da, Date a, Utente u, Pacchetto pv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
