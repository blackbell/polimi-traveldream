/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.controller;

import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.EDB;
import it.polimi.traveldream.model.Esito;
import it.polimi.traveldream.model.Museo;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.service.EDBServiceLocal;
import it.polimi.traveldream.service.PBServiceLocal;
import it.polimi.traveldream.service.PVServiceLocal;
import it.polimi.traveldream.service.ParametriRicercaEDB;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Dario
 */
@Controller
public class GestioneOffertaController {
    
    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/EDBService")
    EDBServiceLocal edbService;
    
    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/PBService")
    PBServiceLocal pbService;
    
    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/PVService")
    PVServiceLocal pvService;
    
    @RequestMapping(value = "trovaEntita", method = RequestMethod.POST)
    public @ResponseBody Esito trovaEntità(@RequestBody ParametriRicercaEDB param) {
        Esito e = new Esito();
        try{
            List<EDB> ret = edbService.trovaEntità(param);
            e.setResult(true);
            e.setMessage(null);
            e.setReturnedObj(ret);
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value = "attivaPB", method = RequestMethod.POST)
    public @ResponseBody Esito attivaPB(@RequestBody Integer idPB, HttpServletRequest request) {
        Esito e = new Esito();
        try{
            Utente utenteLoggato = (Utente) request.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utenteLoggato != null && utenteLoggato.getLivello() >= Utente.LIVELLO_IMPIEGATO) {
                Boolean newState = pbService.attivaPB(idPB);
                e.setResult(newState == Boolean.TRUE);
                e.setMessage(newState != null ? null : Esito.OPERATION_FAILED);
                e.setReturnedObj(newState);
            }else{
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
                e.setReturnedObj(null);
            }
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value = "disattivaPB", method = RequestMethod.POST)
    public @ResponseBody Esito disattivaPB(@RequestBody Integer idPB, HttpServletRequest request) {
        Esito e = new Esito();
        try{
            Utente utenteLoggato = (Utente) request.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utenteLoggato != null && utenteLoggato.getLivello() == Utente.LIVELLO_IMPIEGATO) {
                Boolean newState = pbService.disattivaPB(idPB);
                e.setResult(newState == Boolean.TRUE);
                e.setMessage(newState != null ? null : Esito.OPERATION_FAILED);
                e.setReturnedObj(newState);
            }else{
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
                e.setReturnedObj(null);
            }
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value="salvaRotta", method = RequestMethod.POST)
    public @ResponseBody Esito salvaRotta(@RequestBody Rotta r, HttpServletRequest request) {
        Esito e = new Esito();
        try{
            Utente utenteLoggato = (Utente) request.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utenteLoggato != null && utenteLoggato.getLivello() == Utente.LIVELLO_IMPIEGATO) {
                r = edbService.salvaRotta(r);
                e.setResult(r != null);
                e.setMessage(null);
                e.setReturnedObj(r);
            }else{
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
                e.setReturnedObj(null);
            }
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value="salvaAlbergo", method = RequestMethod.POST)
    public @ResponseBody Esito salvaAlbergo(@RequestBody Albergo a, HttpServletRequest request) {
        Esito e = new Esito();
        try{
            Utente utenteLoggato = (Utente) request.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utenteLoggato != null && utenteLoggato.getLivello() == Utente.LIVELLO_IMPIEGATO) {
                a = edbService.salvaAlbergo(a);
                e.setResult(a != null);
                e.setMessage(null);
                e.setReturnedObj(a);
            }else{
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
                e.setReturnedObj(null);
            }
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value="salvaMuseo", method = RequestMethod.POST)
    public @ResponseBody Esito salvaMuseo(@RequestBody Museo m, HttpServletRequest request) {
        Esito e = new Esito();
        try{
            Utente utenteLoggato = (Utente) request.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utenteLoggato != null && utenteLoggato.getLivello() == Utente.LIVELLO_IMPIEGATO) {
                m = edbService.salvaMuseo(m);
                e.setResult(m != null);
                e.setMessage(null);
                e.setReturnedObj(m);
            }else{
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
                e.setReturnedObj(null);
            }
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value = "attivaPV", method = RequestMethod.POST)
    public @ResponseBody Esito attivaPV(@RequestBody Integer idPV, HttpServletRequest request) {
        Esito e = new Esito();
        try{
            Utente utenteLoggato = (Utente) request.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utenteLoggato != null && utenteLoggato.getLivello() == Utente.LIVELLO_IMPIEGATO) {
                Boolean newState = pvService.attivaPV(idPV);
                e.setResult(newState == Boolean.TRUE);
                e.setMessage(newState != null ? null : Esito.OPERATION_FAILED);
                e.setReturnedObj(newState);
            }else{
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
                e.setReturnedObj(null);
            }
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value = "disattivaPV", method = RequestMethod.POST)
    public @ResponseBody Esito disattivaPV(@RequestBody Integer idPV, HttpServletRequest request) {
        Esito e = new Esito();
        try{
            Utente utenteLoggato = (Utente) request.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utenteLoggato != null && utenteLoggato.getLivello() == Utente.LIVELLO_IMPIEGATO) {
                Boolean newState = pvService.disattivaPV(idPV);
                e.setResult(newState == Boolean.TRUE);
                e.setMessage(newState != null ? null : Esito.OPERATION_FAILED);
                e.setReturnedObj(newState);
            }else{
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
                e.setReturnedObj(null);
            }
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }

    public PBServiceLocal getPbService() {
        return pbService;
    }

    public void setPbService(PBServiceLocal pbService) {
        this.pbService = pbService;
    }
    
    public EDBServiceLocal getEdbService() {
        return edbService;
    }

    public void setEdbService(EDBServiceLocal edbService) {
        this.edbService = edbService;
    }
    
    
}
