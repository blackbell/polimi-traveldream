/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.controller;

import it.polimi.traveldream.model.Esito;
import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.service.EDBServiceLocal;
import it.polimi.traveldream.service.PBServiceLocal;
import it.polimi.traveldream.service.PVServiceLocal;
import it.polimi.traveldream.service.ParametriRicercaPB;
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
public class SalvaPVPBController {
    
    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/EDBService")
    EDBServiceLocal edbService;
    
    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/PBService")
    PBServiceLocal pbService;
    
    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/PVService")
    PVServiceLocal pvService;
    
    @RequestMapping(value = "salvaPV", method = RequestMethod.POST)
    public @ResponseBody Esito salvaPV(@RequestBody Pacchetto pv) {
        Esito e = new Esito();
        try{
            pv = pvService.salvaPV(pv);
            e.setResult(true);
            e.setMessage(null);
            //e.setReturnedObj(ret);
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex);
        }
        return e;
    }
    
    @RequestMapping(value = "trovaPB", method = RequestMethod.POST)
    
    public @ResponseBody Esito trovaPB(@RequestBody ParametriRicercaPB params) {
    //public @ResponseBody Esito trovaPB() {
        Esito e = new Esito();
        try{
            //ParametriRicercaPB params = new ParametriRicercaPB();
            //params.setTipo(TipoPB.Soggiorno);
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
