/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.ejb;

import it.polimi.traveldream.data.UtenteDAO;
import it.polimi.traveldream.model.Utente;
import javax.ejb.Stateless;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rh0x
 */
@Stateless
public class UtenteService implements UtenteServiceLocal {

    @Autowired
    private UtenteDAO utenteDAO;

    public void setUtenteDAO(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }

    public UtenteDAO getUtenteDAO() {
        return utenteDAO;
    }

    @Transactional
    @Override
    public String registrazione(Utente utente) {
        utenteDAO.save(utente);
        return "salvato";
    }
    
    
    
}
