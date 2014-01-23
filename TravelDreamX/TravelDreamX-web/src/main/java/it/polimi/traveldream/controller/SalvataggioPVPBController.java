/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.controller;

import it.polimi.traveldream.model.Esito;
import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.TipoPacchetto;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.service.EDBServiceLocal;
import it.polimi.traveldream.service.PBServiceLocal;
import it.polimi.traveldream.service.PVServiceLocal;
import java.util.Date;
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
public class SalvataggioPVPBController {

    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/EDBService")
    EDBServiceLocal edbService;

    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/PBService")
    PBServiceLocal pbService;

    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/PVService")
    PVServiceLocal pvService;

    @RequestMapping(value = "salvaPV", method = RequestMethod.POST)
    public @ResponseBody
    Esito salvaPV(@RequestBody Pacchetto pv, HttpServletRequest request) {
        Esito e = new Esito();
        try {
            Utente utenteLoggato = (Utente) request.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utenteLoggato != null && utenteLoggato.equals(pv.getProprietario())) {
                pv.setProprietario(utenteLoggato);
                pv.setAbilitato(Boolean.TRUE);
                pv.setDataOraCreazione(new Date());
                if (utenteLoggato.getLivello() == Utente.LIVELLO_IMPIEGATO)
                    pv.setTipo(Pacchetto.PREDEFINITO);
                Pacchetto result  = pvService.salvaPV(pv);
                e.setResult(result != null);
                e.setMessage(result != null ? null : Esito.OPERATION_FAILED);
                if (result == null && Pacchetto.nroVociNonConfome(pv))
                    e.setMessage(Esito.TOO_MANY_PBS);
                e.setReturnedObj(pv);
            }else{
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_LOGGED_IN);
            }
        } catch (Exception ex) {
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex);
        }
        return e;
    }

    @RequestMapping(value = "salvaPB", method = RequestMethod.POST)
    public @ResponseBody
    Esito salvaPB(@RequestBody Voce pb) {
        Esito e = new Esito();
        try {
            pbService.salvaPB(pb);
            e.setResult(true);
            e.setMessage(null);
            //e.setReturnedObj(ret);
        } catch (Exception ex) {
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex);
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
