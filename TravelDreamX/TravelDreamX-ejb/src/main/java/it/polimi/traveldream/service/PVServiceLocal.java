/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Voce;
import javax.ejb.Local;

/**
 *
 * @author Dario
 */
@Local
public interface PVServiceLocal {
    
    public Pacchetto addPBtoPV(Voce pb, Pacchetto pv);
}
