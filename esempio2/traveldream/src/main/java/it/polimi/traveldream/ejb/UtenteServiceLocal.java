/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.ejb;

import it.polimi.traveldream.model.Utente;
import javax.ejb.Local;

/**
 *
 * @author rh0x
 */
@Local
public interface UtenteServiceLocal {
    
    public Utente registrazione( Utente utente );
}
