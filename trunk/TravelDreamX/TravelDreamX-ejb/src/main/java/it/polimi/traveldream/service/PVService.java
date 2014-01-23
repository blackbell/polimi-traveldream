/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.service;

import it.polimi.traveldream.data.PVDAO;
import it.polimi.traveldream.data.VoceDAO;
import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Voce;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

/**
 *
 * @author Dario
 */
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Stateless
public class PVService implements PVServiceLocal {

    @Autowired 
    VoceDAO voceDAO;
    
    @Autowired
    PVDAO pvDAO;

    @Override
    public Pacchetto addPBtoPV(Voce pb, Pacchetto pv) {
        pv.getVoci().add(pb);
        return pvDAO.saveAndFlush(pv);
    }

    @Override
    public Pacchetto salvaPV(Pacchetto pv) {
        if (pv.getVoci() == null || pv.getVoci().size() == 0 || pv.getVoci().size() >= Pacchetto.MAX_NRO_VOCI) return null;
        for (Voce v : pv.getVoci())
            System.out.println("PVService.salvaPV -> v: " + v + " [tipo: " + v.getTipo() + "]");
        return pvDAO.saveAndFlush(pv);
    }

    @Override
    public Pacchetto recuperaPV(Integer idPV) {
        Pacchetto p = pvDAO.findOne(idPV);
        return p;
    }
    
    @Override
    public boolean attivaPV(Integer idPV) {
        Pacchetto pv = pvDAO.findOne(idPV);
        pv.setAbilitato(true);
        pv = pvDAO.save(pv);
        return pv.isAbilitato();
    }

    @Override
    public boolean disattivaPV(Integer idPV) {
        Pacchetto pv = pvDAO.findOne(idPV);
        pv.setAbilitato(false);
        pv = pvDAO.save(pv);
        return pv.isAbilitato();
    }

    @Override
    public List<Pacchetto> trovaPB(ParametriRicercaPV params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
