/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.controller;

import it.polimi.traveldream.model.Esito;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.service.UtenteServiceLocal;
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
public class AmministrazioneController {

    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/UtenteService")
    private UtenteServiceLocal utenteService;

    @RequestMapping(value = "recuperaUtenti", method = RequestMethod.POST)
    public @ResponseBody Esito recuperaUtenti(HttpServletRequest request) {
        Esito e = new Esito();
        try {
            Utente utenteLoggato = (Utente) request.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utenteLoggato != null && utenteLoggato.getLivello() == Utente.LIVELLO_AMMINISTRATORE) {
                List<Utente> ret = utenteService.recuperaUtenti();
                e.setResult(true);
                e.setMessage(null);
                e.setReturnedObj(ret);
            }else{
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
                e.setReturnedObj(null);
            }
        } catch (Exception ex) {
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value = "disattivaUtente", method = RequestMethod.POST)
    public @ResponseBody Esito disattivaUtente(@RequestBody Utente utente, HttpServletRequest request) {
        Esito e = new Esito();
        try {
            Utente utenteLoggato = (Utente) request.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utenteLoggato != null && utenteLoggato.getLivello() >= Utente.LIVELLO_AMMINISTRATORE && !utente.equals(utenteLoggato)) {
                Boolean newState = utenteService.disattivaUtente(utente);
                e.setResult(newState == Boolean.TRUE);
                e.setMessage(newState != null ? null : Esito.USER_NOT_FOUND);
                e.setReturnedObj(newState);
            }else{
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
                e.setReturnedObj(null);
            }
        } catch (Exception ex) {
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value = "attivaUtente", method = RequestMethod.POST)
    public @ResponseBody Esito attivaUtente(@RequestBody Utente utente, HttpServletRequest request) {
        Esito e = new Esito();
        try {
            Utente utenteLoggato = (Utente) request.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utenteLoggato != null && utenteLoggato.getLivello() >= Utente.LIVELLO_AMMINISTRATORE) {
                Boolean newState = utenteService.attivaUtente(utente);
                e.setResult(newState == Boolean.TRUE);
                e.setMessage(newState != null ? null : Esito.USER_NOT_FOUND);
                e.setReturnedObj(newState);
            }else{
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
                e.setReturnedObj(null);
            }
        } catch (Exception ex) {
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value = "modificaLivelloUtente", method = RequestMethod.POST)
    public @ResponseBody Esito modificaLivelloUtente(@RequestBody Utente utente, HttpServletRequest request) {
        Esito e = new Esito();
        try {
            Utente utenteLoggato = (Utente) request.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utenteLoggato != null && utenteLoggato.getLivello() >= Utente.LIVELLO_AMMINISTRATORE) {
                Integer newLevel = utenteService.modificaLivello(utente);
                e.setResult(newLevel != null);
                e.setMessage(newLevel != null ? null : Esito.USER_NOT_FOUND);
                e.setReturnedObj(newLevel);
            }else{
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
                e.setReturnedObj(null);
            }
        } catch (Exception ex) {
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
}
