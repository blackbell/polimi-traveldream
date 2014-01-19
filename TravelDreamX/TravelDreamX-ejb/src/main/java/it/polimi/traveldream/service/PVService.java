/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.service;

import it.polimi.traveldream.data.PVDAO;
import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Voce;
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
    PVDAO pvDAO;

    @Override
    public Pacchetto addPBtoPV(Voce pb, Pacchetto pv) {
        pv.getVoci().add(pb);
        return pvDAO.saveAndFlush(pv);
    }

    @Override
    public Pacchetto salvaPV(Pacchetto pv) {
        return pvDAO.saveAndFlush(pv);
    }

    @Override
    public Pacchetto recuperaPB(Integer idPV) {
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

}
