/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.service;

import it.polimi.traveldream.data.RottaDAO;
import it.polimi.traveldream.data.VoceDAO;
import it.polimi.traveldream.model.Soggiorno;
import it.polimi.traveldream.model.Visita;
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
public class PBService implements PBServiceLocal {

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
        return (Volo) voceDAO.findOne(id);
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
    public Voce salvaPB(ParametriRicercaPB params) {
        Voce ret = null;
        System.out.println("PBService.salvaPB");
        System.out.println("PBService.salvaPB -> params.tipo: " + params.getTipo());
        switch(params.getTipo()){
            case Volo:
                Volo v = new Volo();
                v.setAbilitato(true);
                v.setRotta(params.getRotta());
                v.setCosto(params.getCosto());
                v.setNumPasseggeri(params.getNumPersone());
                v.setDataOra(params.getDataOra());
                ret = voceDAO.saveAndFlush(v);
                break;
            case Soggiorno:
                Soggiorno s = new Soggiorno();
                s.setAbilitato(true);
                s.setAlbergo(params.getAlbergo());
                s.setCosto(params.getCosto());
                s.setGiornoInizio(params.getData());
                s.setGiornoFine(params.getDataOraFine());
                s.setNumeroPersone(params.getNumPersone());
                ret = voceDAO.saveAndFlush(s);
                break;
            case Visita:
                Visita m = new Visita();
                m.setAbilitato(true);
                m.setMuseo(params.getMuseo());
                m.setNumeroPersone(params.getNumPersone());
                m.setDataOra(params.getDataOra());
                m.setCosto(params.getCosto());
                ret = voceDAO.saveAndFlush(m);
                break;
        }
        return ret;
    }
    
    @Override
    public List<Voce> trovaPB(ParametriRicercaPB params) {
        System.out.println("PBService.trovaPB");
        List<Voce> ret = null;
        switch (params.getTipo()) {
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
                for (Volo v : voli) {
                    ret.add(v);
                    System.out.println("PBService.trovaPB -> v: " + v);
                }

                break;

            case Soggiorno:
                System.out.println("PBService.trovaPB -> params.tipo = soggiorno");
                System.out.println("PBService.trovaPB -> params.albergo:" + params.getAlbergo());
                System.out.println("PBService.trovaPB -> params.:" + params.getNumPersone());
                Collection<Soggiorno> soggiorni;
                soggiorni = voceDAO.findByParams(params.getAlbergo(), params.getData(), params.getCitta(), params.getNumPersone());
                System.out.println("PBService.trovaPB -> soggiorni.size: " + soggiorni.size());
                ret = new ArrayList<>();
                for (Soggiorno s : soggiorni) {
                    ret.add(s);
                    System.out.println("PBService.trovaPB -> s: " + s);
                }
                break;
            case Visita:
                System.out.println("PBService.trovaPB -> params.tipo = visita");
                System.out.println("PBService.trovaPB -> params.museo:" + params.getMuseo());
                System.out.println("PBService.trovaPB -> params.numeroPersone:" + params.getNumPersone());
                Collection<Visita> visite;
                visite = voceDAO.findByParams(params.getMuseo(), params.getData(), params.getCitta(), params.getNumPersone());
                System.out.println("PBService.trovaPB -> visite.size: " + visite.size());
                ret = new ArrayList<>();
                for (Visita v : visite) {
                    ret.add(v);
                    System.out.println("PBService.trovaPB -> v: " + v);
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

    @Override
    public Visita saveVisita(Visita v) {
        return (Visita) voceDAO.saveAndFlush(v);
    }

    @Override
    public Visita getVisitaByID(int id) {
        return (Visita) voceDAO.findOne(id);
    }

    @Override
    public boolean attivaPB(Integer idPB) {
        Voce v = voceDAO.findOne(idPB);
        v.setAbilitato(true);
        v = voceDAO.save(v);
        return v.isAbilitato();
    }
    
    @Override
    public boolean disattivaPB(Integer idPB) {
        Voce v = voceDAO.findOne(idPB);
        v.setAbilitato(false);
        v = voceDAO.save(v);
        return v.isAbilitato();
    }
}
