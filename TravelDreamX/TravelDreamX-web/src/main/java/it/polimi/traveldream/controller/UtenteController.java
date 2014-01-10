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
 * @author rh0x
 */
@Controller
public class UtenteController {
   
    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/UtenteService")
    private UtenteServiceLocal utenteService;
        
    @RequestMapping(value = "registrazione", method = RequestMethod.POST)
    public @ResponseBody Esito registrazione(@RequestBody Utente utente) {
        Esito e = new Esito();
        try{
            utente.setAbilitato(true);
            utente = utenteService.registrazione( utente );
            if (utente == null){
                e.setResult(false);
                e.setMessage(Esito.USER_ALREADY_EXISTNG);
                e.setReturnedObj(null);
            }else {
                e.setResult(true);
                e.setMessage(Esito.USER_SIGNIN_SUCCESSFUL);
                e.setReturnedObj(utente);
            }
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }    

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody Esito login(@RequestBody Utente utente, HttpServletRequest req) {
        System.out.println("login() -> utente: " + utente);
        Esito e = new Esito();
        {
            Utente u = (Utente)req.getSession().getAttribute("TDX_CurrentUser");
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
                req.getSession().setAttribute("TDX_CurrentUser", utente);
            }
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getCause());            
        }
        System.out.println("login() -> e:" + e);
        return e;
    }
    
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public @ResponseBody Esito logout(HttpServletRequest req) {
        Esito e = new Esito();
        Utente utente = (Utente)req.getSession().getAttribute("TDX_CurrentUser");
        try{
            if (utente == null){
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_FOUND);
                e.setReturnedObj(null);
            }else {
                e.setResult(true);
                e.setMessage(Esito.USER_LOGOUT_SUCCESS);
                e.setReturnedObj(null);
                req.getSession().removeAttribute("TDX_CurrentUser");
            }
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());            
        }
        return e;
    }
}
