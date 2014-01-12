/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.data.VoceDAO;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.model.Volo;
import java.util.ArrayList;
import java.util.Date;
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
    public List<Voce> trovaPB(ParametriRicercaPB params) {
        List<Voce> ret = null;
        switch(params.getTipo()){
            case Volo:
                ret = new ArrayList<>();
                Rotta rotta = new Rotta();
                rotta.setAeroportoPartenza("San Giorgio International Airport");
                rotta.setAeroportoArrivo("Malpensa");
                rotta.setNazionePartenza("Italia");
                rotta.setNazioneArrivo("Italia");
                rotta.setCompagniaAerea("PoliMI airways");
                rotta.setCittaPartenza("Pistoia");
                rotta.setCittaArrivo("Milano");
                Volo volo = new Volo();
                volo.setDataOra(new Date());
                volo.setRotta(rotta);
                volo.setNumPasseggeri(3);
                volo.setCosto(120.7f);
                volo.setAbilitato(true);
                ret.add(volo);
                break;
            default:
                break;
        }
        return ret;
        
    }
    
    
}
