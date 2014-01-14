/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.data.RottaDAO;
import it.polimi.traveldream.data.VoceDAO;
import it.polimi.traveldream.model.Soggiorno;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.model.Volo;
import java.util.ArrayList;
import java.util.Collection;
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
    private RottaDAO rottaDAO;

    
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
    public Soggiorno saveSoggiorno(Soggiorno s) {
        return (Soggiorno) voceDAO.saveAndFlush(s);
    }

    @Override
    public Soggiorno getSoggiornoByID(int id) {
        return (Soggiorno) voceDAO.findOne(id);
    }

    
    
    @Override
    public List<Voce> trovaPB(ParametriRicercaPB params) {
        System.out.println("PBService.trovaPB");
        List<Voce> ret = null;
        switch(params.getTipo()){
            case Volo:
                System.out.println("PBService.trovaPB -> params.tipo = volo");
                System.out.println("PBService.trovaPB -> params.rotta :" + params.getRotta());
                System.out.println("PBService.trovaPB -> params.data :" + params.getData());
                System.out.println("PBService.trovaPB -> params.costo :" + params.getCosto());
                Collection<Volo> voli;
//                voli = voceDAO.findByParams(params.getRotta(), params.getData(), params.getCosto(), params.getNumPersone());
                voli = voceDAO.findByParams(params.getRotta(), params.getData(), params.getNumPersone());
                System.out.println("PBService.trovaPB -> voli.size: " + voli.size());
                ret = new ArrayList<>();
                for(Volo v : voli){
                    ret.add(v);
                    System.out.println("PBService.trovaPB -> v: " + v);
                }
                
                break;
                
            case Soggiorno:
                System.out.println("PBService.trovaPB -> params.tipo = soggiorno");
                System.out.println("PBService.trovaPB -> params.albergo:" + params.getAlbergo());
                System.out.println("PBService.trovaPB -> params.:" + params.getNumPersone());
                Collection<Soggiorno> soggiorni;
                soggiorni = voceDAO.findByParams(params.getAlbergo(), params.getData(), params.getCitta(),params.getNumPersone());
                System.out.println("PBService.trovaPB -> soggiorni.size: " + soggiorni.size());
                ret = new ArrayList<>();
                for(Soggiorno s : soggiorni){
                    ret.add(s);
                    System.out.println("PBService.trovaPB -> s: " + s);
                }
                 break;
            default:
                break;
        }
        return ret;
        
    }
    
    public RottaDAO getRottaDAO() {
        return rottaDAO;
    }

    public void setRottaDAO(RottaDAO rottaDAO) {
        this.rottaDAO = rottaDAO;
    }
}
