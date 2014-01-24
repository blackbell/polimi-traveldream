/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.controller;

import it.polimi.traveldream.model.Esito;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.service.UtenteServiceLocal;
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
public class AutenticazioneController {
    public static final String TAG_UTENTE_SESSIONE = "TDX_CurrentUser"; 
    
    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/UtenteService")
    private UtenteServiceLocal utenteService;
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody Esito login(@RequestBody Utente utente, HttpServletRequest req) {
        System.out.println("login() -> utente: " + utente);
        Esito e = new Esito();
        {
            Utente u = (Utente)req.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (u != null)
                utente = u;
            else
                utente = utenteService.login(utente);
        }
        try{
            if (utente == null){
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_FOUND);
                e.setReturnedObj(null);
            }else if (!utente.getAbilitato()){
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
                e.setReturnedObj(null);
            }else {
                e.setResult(true);
                e.setMessage(Esito.USER_LOGIN_SUCCESS);
                e.setReturnedObj(utente);
                req.getSession().setAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE, utente);
            }
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getCause());            
        }
        System.out.println("login() -> e:" + e);
        return e;
    }
    
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public @ResponseBody Esito logout(HttpServletRequest req) {
        Esito e = new Esito();
        try{
            Utente utente = (Utente)req.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utente == null){
                e.setResult(false);
                e.setMessage(Esito.OPERATION_FAILED);
                e.setReturnedObj(null);
            }else {
                e.setResult(true);
                e.setMessage(Esito.USER_LOGOUT_SUCCESS);
                e.setReturnedObj(null);
                req.getSession().removeAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            }
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());            
        }
        return e;
    }
}
