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
        System.out.println("PagamentoService.pagamentoPV -> pv.numeroPersone:" + pv.getNumeroPersone());

        for(Voce v : pv.getVoci()){
            Pagamento p = new Pagamento();
            p.setVoce(v);
            p.setUtente(u);
            p.setPacchetto(pv);
            p.setDataOraPagamento(new Date());
            p.setMolteplicitaVoce(pv.getNumeroPersone());
            p = pagamentoDAO.save(p);
            if (p != null){
                pagamenti.add(p);   
                System.out.println("PagamentoService.pagamentoPV -> pb:" + p.getVoce());
                System.out.println("PagamentoService.pagamentoPV -> pb.spesa:" + p.getVoce().getSpesa(1));
                System.out.println("PagamentoService.pagamentoPV -> p.spesa:" + p.getSpesa());
            }
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
        p.setMolteplicitaVoce(pv.getNumeroPersone());
        System.out.println("PagamentoService.pagamentoPB -> pv.numeroPersone:" + pv.getNumeroPersone());
        System.out.println("PagamentoService.pagamentoPB -> pb:" + p.getVoce());
        System.out.println("PagamentoService.pagamentoPB -> pb.spesa:" + p.getVoce().getSpesa(1));
        System.out.println("PagamentoService.pagamentoPB -> p.spesa:" + p.getSpesa());
        return pagamentoDAO.saveAndFlush(p);
    }

    @Override
    public List<Pagamento> recuperaPagamenti(ParametriRicercaPagamenti params) {
        Date da = params.getDataInizio() != null ? params.getDataInizio() : new Date(0);
        if (params.getDataFine() == null)
            params.setDataFine(new Date()); 
        Date a = params.getDataFine();
        Utente u = params.getUtente();
        Pacchetto pv = params.getPacchetto();
        System.out.println("PagamentoService.recuperaPagamenti -> da:"+da);
        System.out.println("PagamentoService.recuperaPagamenti -> a:"+a);
        System.out.println("PagamentoService.recuperaPagamenti -> utente:"+u);
        System.out.println("PagamentoService.recuperaPagamenti -> pv:"+pv);
        List<Pagamento> ret = pagamentoDAO.findByParams(da, a, u, pv);
        System.out.println("PagamentoService.recuperaPagamenti -> ret:"+ret + " size: " + ret.size());
        return ret.isEmpty() ? null : ret;
    }
}
