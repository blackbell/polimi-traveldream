/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.controller;

import it.polimi.traveldream.ejb.UtenteServiceLocal;
import it.polimi.traveldream.model.Utente;
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
    
    @EJB(mappedName = "java:module/UtenteService" )
    private UtenteServiceLocal bean;

    public void setBean(UtenteServiceLocal bean) {
        this.bean = bean;
    }
    
    
    
    @RequestMapping(value = "registrazione")
    public @ResponseBody Utente registrazione() {
        Utente utente = new Utente("leo", "culo");
        utente.setStato("abilitato");
        return bean.registrazione( utente );
    }

    
    
    
}
