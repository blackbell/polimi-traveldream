/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.EDB;
import it.polimi.traveldream.model.Rotta;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Dario
 */
public class RottaDAOTest extends DAOUnitTest {
    
    @Autowired
    public RottaDAO rottaDAO;
    
    public void setRottaDAO(RottaDAO rottaDAO) {
        this.rottaDAO = rottaDAO;
    }

    @Test
    public void wiringRottaDAO(){
        assertNotNull("Cannot inject rottaDAO!",rottaDAO);
    }
    
    @Test
    public void saveRotta(){
        assertNotNull("Cannot inject rottaDAO!",rottaDAO);
        Rotta rotta = new Rotta();
        rotta.setAeroportoPartenza("San Giorgio International Airport");
        rotta.setAeroportoArrivo("Malpensa");
        rotta.setNazionePartenza("Italia");
        rotta.setNazioneArrivo("Italia");
        rotta.setCompagniaAerea("PoliMI airways");
        rotta.setCittàPartenza("Pistoia");
        rotta.setCittàArrivo("Milano");
        Rotta rotta2 = rottaDAO.saveAndFlush(rotta);
        assertNotNull(rotta2);
    }
    
    @Test
    public void retrieveRotta(){
        Rotta r = addRotta();
        assertNotNull("Cannot inject rottaDAO!",rottaDAO);
        Rotta rotta = rottaDAO.findOne(r.getIdRotta());
        assertNotNull("Cannot retrieve route with ID=" + r.getIdRotta() + "!", rotta);
    }
    
    @Test
    public void retrieveRottaByParams(){
        Rotta r = addRotta();
        assertNotNull("Cannot inject rottaDAO!",rottaDAO);
        List<EDB> rotte = rottaDAO.findByParams(r.getAeroportoPartenza(), 
                                            r.getAeroportoArrivo(), 
                                            r.getCittàPartenza(), 
                                            r.getCittàArrivo(),
                                            r.getNazionePartenza(), 
                                            r.getNazioneArrivo(),
                                            r.getCompagniaAerea());
        assertTrue(rotte.contains(r));
    }
    
    private Rotta addRotta(){
        Rotta rotta = new Rotta();
        rotta.setAeroportoPartenza("San Giorgio International Airport");
        rotta.setAeroportoArrivo("Malpensa");
        rotta.setNazionePartenza("Italia");
        rotta.setNazioneArrivo("Italia");
        rotta.setCompagniaAerea("PoliMI airways");
        rotta.setCittàPartenza("Pistoia");
        rotta.setCittàArrivo("Milano");
        Rotta rotta2 = rottaDAO.saveAndFlush(rotta);
        return rotta2;
    }
}
