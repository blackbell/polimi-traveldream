/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.controller;

import it.polimi.traveldream.model.Esito;
import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Pagamento;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.service.PagamentoService;
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
public class PagamentoController {

    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/PagamentoService")
    PagamentoService pagamentoService;

    @RequestMapping(value = "acquistaPB", method = RequestMethod.POST)
    public @ResponseBody Esito acquistaPB(@RequestBody Pacchetto pv, @RequestBody Voce pb, HttpServletRequest request) {
        Esito e = new Esito();  
        try{
            Utente utenteLoggato = (Utente) request.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utenteLoggato != null && utenteLoggato.getLivello() >= Utente.LIVELLO_REGISTRATO) {
                Pagamento p = pagamentoService.pagamentoPB(pv, pb, utenteLoggato);
                e.setResult(p != null);
                e.setMessage(p != null ? null : Esito.OPERATION_FAILED);
                e.setReturnedObj(p);
            }else{
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
                e.setReturnedObj(null);
            }
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
    
    @RequestMapping(value = "acquistaPV", method = RequestMethod.POST)
    public @ResponseBody Esito acquistaPV(@RequestBody Pacchetto pv, HttpServletRequest request) {
        Esito e = new Esito();  
        try{
            Utente utenteLoggato = (Utente) request.getSession().getAttribute(AutenticazioneController.TAG_UTENTE_SESSIONE);
            if (utenteLoggato != null && utenteLoggato.getLivello() >= Utente.LIVELLO_REGISTRATO) {
                Pagamento p = pagamentoService.pagamentoPV(pv, utenteLoggato);
                e.setResult(p != null);
                e.setMessage(p != null ? null : Esito.OPERATION_FAILED);
                e.setReturnedObj(p);
            }else{
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
                e.setReturnedObj(null);
            }
        }catch(Exception ex){
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
}
