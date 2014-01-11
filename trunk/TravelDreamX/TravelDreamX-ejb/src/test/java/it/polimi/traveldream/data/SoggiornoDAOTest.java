/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.Soggiorno;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Dario
 */
public class SoggiornoDAOTest extends DAOUnitTest{
    
    @Autowired
    VoceDAO voceDAO;
    @Autowired
    AlbergoDAO albergoDAO;
    
    @Test
    public void wiringVoceDAO(){
        assertNotNull("Cannot inject voceDAO!", voceDAO);
    }
        
    @Test
    public void saveSoggiorno(){
        Albergo a2 = addHotel();
        assertNotNull("Cannot inject voceDAO!", voceDAO);
        Soggiorno s = new Soggiorno();
        s.setAlbergo(a2);
        s.setCosto(200f);
        s.setGiornoInizio(new Date());
        s.setGiornoFine(new Date());
        s.setNumeroPersone(2);
        s.setAbilitato(true);
        Soggiorno s2 = voceDAO.save(s);
        assertEquals("The retrieved stay is not as expected!", s2, s);
    }
    
    
    public Soggiorno addSoggiorno(){
        Albergo a2 = addHotel();
        Soggiorno s = new Soggiorno();
        s.setAlbergo(a2);
        s.setCosto(200f);
        s.setGiornoInizio(new Date());
        s.setGiornoFine(new Date());
        s.setNumeroPersone(2);
        s.setAbilitato(false);
        Soggiorno s2 = voceDAO.save(s);
        return s2;
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
