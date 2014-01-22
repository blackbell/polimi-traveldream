/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.controller;

import it.polimi.traveldream.model.Esito;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.service.UtenteServiceLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Dario
 */
@Controller
public class AmministrazioneController {

    @EJB(mappedName = "java:global/TravelDreamX-ear/TravelDreamX-web-1.0/UtenteService")
    private UtenteServiceLocal utenteService;

    @RequestMapping(value = "recuperaUtenti", method = RequestMethod.POST)
    public @ResponseBody Esito recuperaUtenti(HttpServletRequest request) {
        Esito e = new Esito();
        try {
            Utente utenteLoggato = (Utente) request.getSession().getAttribute("TDX_CurrentUser");
            if (utenteLoggato != null && utenteLoggato.getLivello() >= 0) {
                List<Utente> ret = utenteService.recuperaUtenti();
                e.setResult(true);
                e.setMessage(null);
                e.setReturnedObj(ret);
            }else{
                e.setResult(false);
                e.setMessage(Esito.USER_NOT_AUTHORIZED);
                e.setReturnedObj(null);
            }
        } catch (Exception ex) {
            e.setResult(false);
            e.setMessage(Esito.EXCEPTION_RAISED);
            e.setReturnedObj(ex.getMessage());
        }
        return e;
    }
}
