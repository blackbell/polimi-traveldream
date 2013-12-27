package it.polimi.traveldream.controller;


import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.service.UtenteServiceLocal;
import javax.ejb.EJB;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rh0x
 */
@Controller
public class UtenteController {
   
    @EJB(mappedName = "java:global/TravelDream-ear/TravelDream-web-1.0-SNAPSHOT/UtenteService")
    private UtenteServiceLocal bean;

    public void setBean(UtenteServiceLocal bean) {
        this.bean = bean;
    }
    
    
    
    @RequestMapping(value = "registrazione")
    public @ResponseBody Utente registrazione() {
        Utente utente = new Utente("leo", "culo");
        utente.setStato("abilitato");
        return bean.registrazione( utente);
    }    

    
}
