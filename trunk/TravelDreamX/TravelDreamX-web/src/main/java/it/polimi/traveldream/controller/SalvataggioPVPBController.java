/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.controller;

import it.polimi.traveldream.model.Esito;
import it.polimi.traveldream.model.Pacchetto;
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
                Boolean statoVecchioPV = Boolean.TRUE;
                Pacchetto nuovoPV = null;
                if (pv.getIdPacchetto() != null && utenteLoggato.getLivello() == Utente.LIVELLO_IMPIEGATO) {
                    statoVecchioPV = pvService.disattivaPV(pv.getIdPacchetto());
                }
                if (pv.getIdPacchetto() != null && statoVecchioPV == Boolean.FALSE && utenteLoggato.getLivello() == Utente.LIVELLO_IMPIEGATO) {
                    pv.setIdPacchetto(null);
                }
                if (pv.getIdPacchetto() != null && statoVecchioPV != Boolean.FALSE && utenteLoggato.getLivello() == Utente.LIVELLO_IMPIEGATO) {
                    e.setMessage(Esito.OPERATION_FAILED);
                }
                if (pv.getIdPacchetto() == null || statoVecchioPV == Boolean.TRUE) {
                    pv.setIdPacchetto(null);
                    pv.setProprietario(utenteLoggato);
                    pv.setAbilitato(Boolean.TRUE);
                    pv.setDataOraCreazione(new Date());
                    if (utenteLoggato.getLivello() == Utente.LIVELLO_IMPIEGATO) {
                        pv.setTipo(Pacchetto.PREDEFINITO);
                    }
                    nuovoPV = pvService.salvaPV(pv);
                }
                e.setResult(nuovoPV != null);
                e.setMessage(nuovoPV != null ? null : Esito.OPERATION_FAILED);
                if (nuovoPV == null && Pacchetto.nroVociNonConfome(pv)) {
                    e.setMessage(Esito.TOO_MANY_PBS);
                }
                e.setReturnedObj(nuovoPV);
            } else {
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
    Esito salvaPB(@RequestBody Voce pb, HttpServletRequest request) {
        Esito e = new Esito();
        try {
            Utente utenteLoggato = (Utente) request.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utenteLoggato.getLivello() >= Utente.LIVELLO_IMPIEGATO) {
                Voce nuovoPB = null;
                Boolean statoVecchioPB;
                if (pb.getIdVoce() != null)
                    statoVecchioPB = pbService.disattivaPB(pb.getIdVoce());
                if (statoVecchioPB == Boolean.FALSE || statoVecchioPB == null) {
                    pb.setIdVoce(null);
                    nuovoPB = pbService.salvaPB(pb);
                }
                e.setResult(nuovoPB != null);
                e.setMessage(nuovoPB != null ? null : Esito.OPERATION_FAILED);
                e.setReturnedObj(nuovoPB);
            } else {
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
            }
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
