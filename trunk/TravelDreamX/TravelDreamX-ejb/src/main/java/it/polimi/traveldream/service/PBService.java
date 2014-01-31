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
public class PBService implements PBServiceLocal {

    @Autowired
    private RottaDAO rottaDAO;

    @Autowired
    private VoceDAO voceDAO;

    @Transactional
    @Override
    public Volo saveVolo(Volo v) {
        return (Volo) voceDAO.saveAndFlush(v);
    }

    @Transactional
    @Override
    public Volo getVoloByID(int id) {
        return (Volo) voceDAO.findOne(id);
    }

    @Transactional
    @Override
    public Soggiorno saveSoggiorno(Soggiorno s) {
        return (Soggiorno) voceDAO.saveAndFlush(s);
    }

    @Transactional
    @Override
    public Soggiorno getSoggiornoByID(int id) {
        return (Soggiorno) voceDAO.findOne(id);
    }

    @Transactional
    @Override
    public Voce salvaPB(Voce voce) {
        Voce ret = null;
        System.out.println("PBService.salvaPB");
        System.out.println("PBService.salvaPB -> id: " + voce.getIdVoce());
        System.out.println("PBService.salvaPB -> tipo: " + voce.getTipo());
        switch (voce.getTipo()) {
            case "Volo":
                Volo v = (Volo)voce;
//                Volo v = new Volo();
//                v.setAbilitato(true);
//                v.setRotta(params.getRotta());
//                v.setCosto(params.getCosto());
//                v.setNumPasseggeri(params.getNumPersone());
//                v.setDataOra(params.getDataOra());
                ret = voceDAO.saveAndFlush(v);
                break;
            case "Soggiorno":
                Soggiorno s = (Soggiorno) voce;
//                s.setAbilitato(true);
//                s.setAlbergo(params.getAlbergo());
//                s.setCosto(params.getCosto());
//                s.setGiornoInizio(params.getData());
//                s.setGiornoFine(params.getDataOraFine());
//                s.setNumeroPersone(params.getNumPersone());
                ret = voceDAO.saveAndFlush(s);
                break;
            case "Visita":
                Visita m = (Visita) voce;
//                m.setAbilitato(true);
//                m.setMuseo(params.getMuseo());
//                m.setNumeroPersone(params.getNumPersone());
//                m.setDataOra(params.getDataOra());
//                m.setCosto(params.getCosto());
                ret = voceDAO.saveAndFlush(m);
                break;
        }
        return ret;
    }

    @Override
    public List<Voce> trovaPB(ParametriRicercaPB param) {
        System.out.println("PBService.trovaPB");
        List<Voce> ret = null;
        switch (param.getTipo()) {
            case Volo:
                String cittàPartenzaVolo = param.getCittaPartenzaVolo();
                String cittàArrivoVolo = param.getCittaArrivoVolo();
                System.out.println("PBService.trovaPB -> params.tipo = volo");
                System.out.println("PBService.trovaPB -> params.dataOraVolo :" + param.getDataOraVolo());
                System.out.println("PBService.trovaPB -> params.cittàPartenzaVolo :" + param.getCittaPartenzaVolo());
                System.out.println("PBService.trovaPB -> params.cittàArrivoVolo :" + param.getCittaArrivoVolo());
                Collection<Volo> voli;
                voli = voceDAO.findByParams(
                        cittàPartenzaVolo != null ? "%" + cittàPartenzaVolo + "%" : null,
                        cittàArrivoVolo != null ? "%" + cittàArrivoVolo + "%" : null,
                        param.getDataOraVolo(),
                        param.isDisabilitatiInclusi());
                System.out.println("PBService.trovaPB -> voli.size: " + voli.size());
                if (!voli.isEmpty()){
                    ret = new ArrayList<>();
                    for (Volo v : voli) {
                        ret.add(v);
                        System.out.println("PBService.trovaPB -> v: " + v);
                    }
                }
                break;

            case Soggiorno:
                String nomeAlbergo = param.getNomeAlbergo();
                String cittàAlbergo = param.getCittaAlbergo();
                Date d2 = (param.getDataInizioSoggiorno()!=null)?new Date(param.getDataInizioSoggiorno().getTime()+ 24*60*60*1000):null;
                System.out.println("PBService.trovaPB -> params.tipo = soggiorno");
                System.out.println("PBService.trovaPB -> params.nomeAlbergo:" + nomeAlbergo);
                System.out.println("PBService.trovaPB -> params.cittàAlbergo:" + cittàAlbergo);
                System.out.println("PBService.trovaPB -> params.dataInizioSoggiorno:" + param.getDataInizioSoggiorno());
                System.out.println("PBService.trovaPB -> params.dataInizioSoggiorno2:" + d2);
                System.out.println("PBService.trovaPB -> params.dataFineSoggiorno:" + param.getDataFineSoggiorno());
                Collection<Soggiorno> soggiorni;
                soggiorni = voceDAO.findByParams(
                        nomeAlbergo != null ? "%" + nomeAlbergo + "%" : null,
                        cittàAlbergo != null ? "%" + cittàAlbergo + "%" : null,
                        param.getDataInizioSoggiorno(), 
                        d2, 
                        param.getDataFineSoggiorno(), 
                        param.isDisabilitatiInclusi());
                System.out.println("PBService.trovaPB -> soggiorni.size: " + soggiorni.size());
                if (!soggiorni.isEmpty()){
                    ret = new ArrayList<>();
                    for (Soggiorno s : soggiorni) {
                        ret.add(s);
                        System.out.println("PBService.trovaPB -> s: " + s);
                    }
                }
                break;
            case Visita:
                String nomeMuseo = param.getNomeMuseo();
                String cittàMuseo = param.getCittaMuseo();
                Date d3 = (param.getGiornoVisita()!=null)?new Date(param.getGiornoVisita().getTime()+ 24*60*60*1000):null;
                System.out.println("PBService.trovaPB -> params.tipo = visita");
                System.out.println("PBService.trovaPB -> params.nomeMuseo:" + param.getNomeMuseo());
                System.out.println("PBService.trovaPB -> params.cittàMuseo:" + param.getCittaMuseo());
                System.out.println("PBService.trovaPB -> params.giornoVisita:" + param.getGiornoVisita());
                Collection<Visita> visite;
                visite = voceDAO.findByParams(
                        param.getGiornoVisita(),
                        d3,
                        nomeMuseo != null ? "%" + nomeMuseo + "%" : null,
                        cittàMuseo != null ? "%" + cittàMuseo  + "%" : null,
                        param.isDisabilitatiInclusi());
                System.out.println("PBService.trovaPB -> visite.size: " + visite.size());
                if (!visite.isEmpty()){
                    ret = new ArrayList<>();
                    for (Visita v : visite) {
                        ret.add(v);
                        System.out.println("PBService.trovaPB -> v: " + v);
                    }
                }
                
                break;
            default:
                break;
        }
        return ret;

    }

    @Transactional
    @Override
    public Visita saveVisita(Visita v) {
        return (Visita) voceDAO.saveAndFlush(v);
    }

    @Override
    public Visita getVisitaByID(int id) {
        return (Visita) voceDAO.findOne(id);
    }

    @Transactional
    @Override
    public Boolean attivaPB(Integer idPB) {
        Voce v = voceDAO.findOne(idPB);
        if (v == null) return null;
        v.setAbilitato(true);
        v = voceDAO.save(v);
        return v.isAbilitato();
    }

    @Transactional
    @Override
    public Boolean disattivaPB(Integer idPB) {
        Voce v = voceDAO.findOne(idPB);
        if (v == null) return null;
        v.setAbilitato(false);
        v = voceDAO.save(v);
        return v.isAbilitato();
    }

    public RottaDAO getRottaDAO() {
        return rottaDAO;
    }

    public void setRottaDAO(RottaDAO rottaDAO) {
        this.rottaDAO = rottaDAO;
    }
}
