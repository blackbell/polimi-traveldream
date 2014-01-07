/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Albergo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Dario
 */
public class AlbergoDAOTest extends DAOUnitTest {
    
    public AlbergoDAOTest() {
        
    }

    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
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
