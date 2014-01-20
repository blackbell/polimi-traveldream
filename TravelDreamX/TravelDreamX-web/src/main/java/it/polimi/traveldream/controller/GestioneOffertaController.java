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
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.service.EDBServiceLocal;
import it.polimi.traveldream.service.PBServiceLocal;
import it.polimi.traveldream.service.PVServiceLocal;
import it.polimi.traveldream.service.ParametriRicercaEDB;
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
    
    @RequestMapping(value = "attivaPB", method = RequestMethod.POST)
    public @ResponseBody Esito attivaPB(@RequestBody Integer idPB) {
        Esito e = new Esito();
        try{
            boolean newState = pbService.attivaPB(idPB);
            e.setResult(true);
            e.setMessage(null);
            e.setReturnedObj(newState);
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value = "disattivaPB", method = RequestMethod.POST)
    public @ResponseBody Esito disattivaPB(@RequestBody Integer idPB) {
        Esito e = new Esito();
        try{
            boolean newState = pbService.disattivaPB(idPB);
            e.setResult(true);
            e.setMessage(null);
            e.setReturnedObj(newState);
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value="salvaRotta", method = RequestMethod.POST)
    public @ResponseBody Esito salvaRotta(@RequestBody Rotta r) {
        Esito e = new Esito();
        try{
            r = edbService.salvaRotta(r);
            e.setResult(r != null);
            e.setMessage(null);
            e.setReturnedObj(r);
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value="salvaAlbergo", method = RequestMethod.POST)
    public @ResponseBody Esito salvaAlbergo(@RequestBody Albergo a) {
        Esito e = new Esito();
        try{
            a = edbService.salvaAlbergo(a);
            e.setResult(a != null);
            e.setMessage(null);
            e.setReturnedObj(a);
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value="salvaMuseo", method = RequestMethod.POST)
    public @ResponseBody Esito salvaMuseo(@RequestBody Museo m) {
        Esito e = new Esito();
        try{
            m = edbService.salvaMuseo(m);
            e.setResult(m != null);
            e.setMessage(null);
            e.setReturnedObj(m);
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value = "attivaPV", method = RequestMethod.POST)
    public @ResponseBody Esito attivaPV(@RequestBody Integer idPV) {
        Esito e = new Esito();
        try{
            boolean newState = pvService.attivaPV(idPV);
            e.setResult(true);
            e.setMessage(null);
            e.setReturnedObj(newState);
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value = "disattivaPV", method = RequestMethod.POST)
    public @ResponseBody Esito disattivaPV(@RequestBody Integer idPV) {
        Esito e = new Esito();
        try{
            boolean newState = pvService.disattivaPV(idPV);
            e.setResult(true);
            e.setMessage(null);
            e.setReturnedObj(newState);
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
