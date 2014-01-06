/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Soggiorno;
import it.polimi.traveldream.model.Volo;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Dario
 */
public class VoceDAOTest extends DAOUnitTest{
    
    @Autowired
    VoceDAO voceDAO;
    
    public VoceDAOTest() {
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

    @Test
    public void wiringVoceDAO(){
        assertNotNull("Cannot inject voceDAO!", voceDAO);
    }
        
    @Test
    public void saveSoggiornoEVolo(){
        Albergo a2 = addHotel();
        assertNotNull("Cannot inject voceDAO!", voceDAO);
        Soggiorno s = new Soggiorno();
        s.setIdAlbergo(a2);
        s.setCosto(200f);
        s.setGiornoInizio(new Date());
        s.setGiornoFine(new Date());
        s.setNumeroPersone(2);
        Rotta r = addRotta();
        Volo volo = new Volo();
        volo.setDataOra(new Date());
        volo.setIdRotta(r);
        volo.setNumPasseggeri(3);
        volo.setCosto(120.7f);
        voceDAO.save(volo);
        voceDAO.saveAndFlush(s);
        Volo volo2 = (Volo) voceDAO.findOne(volo.getIdVoce().intValue());
        Soggiorno s2 = (Soggiorno) voceDAO.findOne(s.getIdVoce().intValue());
        assertEquals("The retrieved user is not as expected!", s2, s);
        assertEquals("The retrieved user is not as expected!", volo2, volo);
    }
    
    @Autowired
    AlbergoDAO albergoDAO;
    private Albergo addHotel(){
        Albergo albergo = new Albergo();
        albergo.setNome("HOTEL PIPPO");
        albergo.setCitta("Milano");
        albergo.setStelle(3);
        albergo.setUrlFoto("/");
        Albergo a2 = albergoDAO.saveAndFlush(albergo);
        return a2;
    }
    
    @Autowired
    RottaDAO rottaDAO;
    private Rotta addRotta(){
        Rotta rotta = new Rotta();
        rotta.setCittàPartenza("Pistoia");
        rotta.setCittàArrivo("Milano");
        Rotta rotta2 = rottaDAO.saveAndFlush(rotta);
        return rotta2;
    }
}
