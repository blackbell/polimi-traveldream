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
public class UtenteController {
   
    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0-SNAPSHOT/UtenteService")
    private UtenteServiceLocal utenteService;

    public void setBean(UtenteServiceLocal bean) {
        this.utenteService = utenteService;
    }
    
    
    
    @RequestMapping(value = "registrazione", method = RequestMethod.POST)
    public @ResponseBody Esito registrazione(@RequestBody Utente utente) {
        
        utente.setAbilitato(Boolean.TRUE);
        utente.setLivello(0);
        
        utente = utenteService.registrazione( utente );
        
        Esito esito = new Esito();
        if( utente != null ){
            esito.setResult(Boolean.TRUE);
            esito.setReturnedObj(utente);
        } else {
            esito.setResult(Boolean.FALSE);
        }
        
        return esito;
    }    

    
}
