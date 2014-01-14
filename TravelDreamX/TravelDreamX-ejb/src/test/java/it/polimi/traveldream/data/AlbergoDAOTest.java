/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Albergo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Dario
 */
public class AlbergoDAOTest extends DAOUnitTest {
    
    @Autowired
    public AlbergoDAO albergoDAO;
    
    public void setAlbergoDAO(AlbergoDAO albergoDAO) {
        this.albergoDAO = albergoDAO;
    }

    @Test
    public void wiringAlbergoDAO(){
        assertNotNull("Cannot inject albergoDAO!",albergoDAO);
    }
    
    @Test
    public void saveHotel(){
        assertNotNull("Cannot inject albergoDAO!",albergoDAO);
        Albergo albergo = new Albergo();
        albergo.setNome("Hotel Iride");
        albergo.setCitta("Milano");
        albergo.setStelle(2);
        albergo.setUrlFoto("/");
        Albergo a2 = albergoDAO.saveAndFlush(albergo);
        assertNotNull(a2);
    }
    
    @Test 
    public void retrieveAlbergo(){
        Albergo a = addHotel();
        assertNotNull("Cannot inject albergoDAO!",albergoDAO);
        Albergo albergo = albergoDAO.findOne(a.getIdAlbergo());
        assertNotNull("Cannot retrieve albergo with ID=" + a.getIdAlbergo() + "!", albergo);
    }
    
    @Test
    public void retrieveAlbergoByParams(){
        System.out.println("retrieveAlbergoByParams()");
        Albergo a = addHotel();
        assertNotNull("Cannot inject albergoDAO!",albergoDAO);
        assertTrue(albergoDAO.findByParams(a.getNome(), a.getCitta(), 3).contains(a));
    }
    
    private Albergo addHotel(){
        Albergo albergo = new Albergo();
        albergo.setNome("HOTEL PIPPO");
        albergo.setCitta("Milano");
        albergo.setStelle(3);
        albergo.setUrlFoto("/");
        Albergo a2 = albergoDAO.saveAndFlush(albergo);
        return a2;
    }
}
