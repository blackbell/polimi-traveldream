/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.controller;


import it.polimi.traveldream.model.Esito;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.service.UtenteServiceLocal;
import javax.ejb.EJB;
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
public class RegistrazioneController {
   
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

}
