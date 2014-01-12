/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.data.VoceDAO;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.model.Volo;
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
public class PBService implements PBServiceLocal{

    @Autowired
    private VoceDAO voceDAO;
    
    @Override
    public Volo saveVolo(Volo v) {
        return (Volo) voceDAO.saveAndFlush(v);
    }

    @Override
    public Volo getVoloByID(int id) {
        return (Volo)voceDAO.findOne(id);
    }

    @Override
    public Voce trovaPB(ParametriRicercaPB params) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
