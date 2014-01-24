/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.service;

import it.polimi.traveldream.data.PVDAO;
import it.polimi.traveldream.data.VoceDAO;
import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Soggiorno;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.model.Volo;
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
public class PVService implements PVServiceLocal {

    @Autowired
    VoceDAO voceDAO;

    @Autowired
    PVDAO pvDAO;

    @Transactional
    @Override
    public Pacchetto addPBtoPV(Voce pb, Pacchetto pv) {
        pv.getVoci().add(pb);
        return pvDAO.saveAndFlush(pv);
    }

    @Transactional
    @Override
    public Pacchetto salvaPV(Pacchetto pv) {
        if (pv.getVoci() == null
                || pv.getVoci().isEmpty()
                || pv.getVoci().size() >= Pacchetto.MAX_NRO_VOCI) {
            return null;
        }

        Volo andata = trovaVoloAndata(pv);
        Soggiorno soggiorno = trovaSoggiorno(pv);
        for (Voce v : pv.getVoci()) {
            System.out.println("PVService.salvaPV -> v: " + v + " [tipo: " + v.getTipo() + "]");
        }
        System.out.println("PVService.salvaPV -> volo di andata: " + andata + " [tipo: " + andata.getTipo() + "]");
        System.out.println("PVService.salvaPV -> nome: " + pv.getNome());
        pv.setNazionePartenza(andata.getRotta().getNazionePartenza());
        pv.setNazioneArrivo(andata.getRotta().getNazioneArrivo());
        if (soggiorno != null) pv.setGiornoInizio(soggiorno.getGiornoInizio());
        if (soggiorno != null) pv.setGiornoFine(soggiorno.getGiornoFine());
        if (soggiorno != null) pv.setCittaAlbergo(soggiorno.getAlbergo().getCitta());
        System.out.println("PVService.salvaPV -> pv.giornoInizio: " + pv.getGiornoInizio());
        
        return pvDAO.saveAndFlush(pv);
    }

    private Soggiorno trovaSoggiorno(Pacchetto pv){
        Soggiorno soggiorno = null;
        for (Voce v : pv.getVoci())
            if (v instanceof Soggiorno)
                soggiorno = (Soggiorno)v;
        return soggiorno;
    }
    
    private Volo trovaVoloAndata(Pacchetto pv) {
        Volo andata = null;
        for (Voce v : pv.getVoci()) {
            if (v instanceof Volo) {
                if (andata == null || ((Volo) v).getDataOra().before(andata.getDataOra())) {
                    andata = (Volo) v;
                }
            }
        }
        return andata;
    }

    @Override
    public Pacchetto recuperaPV(Integer idPV) {
        Pacchetto p = pvDAO.findOne(idPV);
        return p;
    }

    @Transactional
    @Override
    public Boolean attivaPV(Integer idPV) {
        Pacchetto pv = pvDAO.findOne(idPV);
        if (pv == null) {
            return null;
        }
        pv.setAbilitato(true);
        pv = pvDAO.save(pv);
        return pv.isAbilitato();
    }

    @Transactional
    @Override
    public Boolean disattivaPV(Integer idPV) {
        Pacchetto pv = pvDAO.findOne(idPV);
        if (pv == null) {
            return null;
        }
        pv.setAbilitato(false);
        pv = pvDAO.save(pv);
        return pv.isAbilitato();
    }

    @Override
    public List<Pacchetto> trovaPV(ParametriRicercaPV params) {
        System.out.println("PVService.trovaPV()");
        Integer idPV = params.getIdPacchetto();
        List<Pacchetto> ret = null;
        if (idPV != null) {
            System.out.println("PVService.trovaPV -> idPV : " + idPV);
        } else {
            String nome = params.getNome();
            String cittàAlbergo = params.getCittaAlbergo();
            String nazionePartenza = params.getNazionePartenza();
            String nazioneArrivo = params.getNazioneArrivo();
            Date dataInizio = params.getDataInizio();
            Date dataFine = params.getDataFine();
            Date d2 = (params.getDataInizio() != null) ? new Date(params.getDataInizio().getTime() + 24 * 60 * 60 * 1000) : null;
            System.out.println("PVService.trovaPV -> nome : " + nome);
            System.out.println("PVService.trovaPV -> cittàAlbergo : " + cittàAlbergo);
            System.out.println("PVService.trovaPV -> nazionePartenza : " + nazionePartenza);
            System.out.println("PVService.trovaPV -> nazioneArrivo : " + nazioneArrivo);
            System.out.println("PVService.trovaPV -> dataInizio : " + dataInizio);
            System.out.println("PVService.trovaPV -> d2 : " + d2);
            System.out.println("PVService.trovaPV -> dataFine : " + dataFine);
            System.out.println("PVService.trovaPV -> diabilitatiInclusi : " + params.isDisabilitatiInclusi());
            ret = pvDAO.findByParams(
                    nome != null ? "%" + nome + "%" : null,
                    cittàAlbergo != null ? "%" + cittàAlbergo + "%" : null,
                    nazionePartenza != null ? "%" + nazionePartenza + "%" : null,
                    nazioneArrivo != null ? "%" + nazioneArrivo + "%" : null,
                    dataInizio,
                    d2,
                    dataFine,
                    params.isDisabilitatiInclusi()
            );
            System.out.println("PVService.trovaPV -> ret.size : " + ret.size());
        }
        return ret;
    }

}
