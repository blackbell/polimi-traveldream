/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.controller;

import it.polimi.traveldream.model.Esito;
import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.service.PBServiceLocal;
import it.polimi.traveldream.service.PVServiceLocal;
import it.polimi.traveldream.service.ParametriRicercaPB;
import it.polimi.traveldream.service.ParametriRicercaPV;
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
public class SearchController {
    
    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/PBService")
    PBServiceLocal pbService;    
    
    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/PVService")
    PVServiceLocal pvService;

    @RequestMapping(value = "pv", method = RequestMethod.POST)
    public @ResponseBody Esito pv(@RequestBody ParametriRicercaPV params) {
        Esito e = new Esito();
        try{
            List<Pacchetto> ret = pvService.trovaPV(params);
            e.setResult(ret != null);
            e.setMessage(null);
            e.setReturnedObj(ret);
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value = "trovaPB", method = RequestMethod.POST)
    public @ResponseBody Esito trovaPB(@RequestBody ParametriRicercaPB params) {
        Esito e = new Esito();
        try{
            List<Voce> ret = pbService.trovaPB(params);
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
}
