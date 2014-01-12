/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.controller;

import it.polimi.traveldream.model.EDB;
import it.polimi.traveldream.model.Esito;
import it.polimi.traveldream.service.EDBServiceLocal;
import it.polimi.traveldream.service.ParametriRicercaEDB;
import java.util.List;
import javax.ejb.EJB;
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
    
    public EDBServiceLocal getEdbService() {
        return edbService;
    }

    public void setEdbService(EDBServiceLocal edbService) {
        this.edbService = edbService;
    }
    
    
}
