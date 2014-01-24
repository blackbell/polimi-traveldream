/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.data.PagamentoDAO;
import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Pagamento;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.model.Voce;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dario
 */
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Stateless
public class PagamentoService implements PagamentoServiceLocal {

    @Autowired
    PagamentoDAO pagamentoDAO;
    
    @Transactional        
    @Override
    public List<Pagamento> pagamentoPV(Pacchetto pv, Utente u) {
        List<Pagamento> pagamenti = new ArrayList<>();
        for(Voce v : pv.getVoci()){
            Pagamento p = new Pagamento();
            p.setVoce(v);
            p.setUtente(u);
            p.setPacchetto(pv);
            p.setDataOraPagamento(new Date());
            p = pagamentoDAO.save(p);
            if (p != null)
                pagamenti.add(p);
        }
        pagamentoDAO.flush();
        return pagamenti;
    }

    @Transactional
    @Override
    public Pagamento pagamentoPB(Pacchetto pv, Voce pb, Utente u) {
        System.out.println("PagamentoService.pagamentoPB -> pv: " + pv);
        System.out.println("PagamentoService.pagamentoPB -> pb: " + pb);
        System.out.println("PagamentoService.pagamentoPB -> u: " + u);
        if (pv.getVoci() == null || !pv.getVoci().contains(pb)) return null;
        Pagamento p = new Pagamento();
        p.setVoce(pb);
        p.setUtente(u);
        p.setPacchetto(pv);
        p.setDataOraPagamento(new Date());
        return pagamentoDAO.saveAndFlush(p);
    }

    @Override
    public List<Pagamento> recuperaPagamenti(Date da, Date a, Utente u, Pacchetto pv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
