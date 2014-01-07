/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Rotta;
import static org.junit.Assert.assertNotNull;
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
    
    private Rotta addRotta(){
        Rotta rotta = new Rotta();
        rotta.setCittàPartenza("Pistoia");
        rotta.setCittàArrivo("Milano");
        Rotta rotta2 = rottaDAO.saveAndFlush(rotta);
        return rotta2;
    }
}
