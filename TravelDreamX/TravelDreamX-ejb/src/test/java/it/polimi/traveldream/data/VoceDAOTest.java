/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Soggiorno;
import it.polimi.traveldream.model.Voce;
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
        s.setAlbergo(a2);
        s.setCosto(200f);
        s.setGiornoInizio(new Date());
        s.setGiornoFine(new Date());
        s.setNumeroPersone(2);
        s.setAbilitato(true);
        Rotta r = addRotta();
        Volo volo = new Volo();
        volo.setDataOra(new Date());
        volo.setRotta(r);
        volo.setNumPasseggeri(3);
        volo.setCosto(120.7f);
        volo.setAbilitato(true);
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

    @Test
    public void checkToRetrieveTheRightObjectType(){
        assertNotNull("Cannot inject voceDAO!", voceDAO);
        Rotta r = addRotta();
        Volo volo = addVolo();
        Voce v = voceDAO.findOne(volo.getIdVoce());        
        assertTrue("The retrieved object is not instance of Volo", v instanceof Volo);
        assertTrue("The retrieved object is instance of Soggiorno", ! (v instanceof Soggiorno));
    }
    
    @Autowired
    RottaDAO rottaDAO;
    private Rotta addRotta(){
        Rotta rotta = new Rotta();
        rotta.setAeroportoPartenza("San Giorgio International Airport");
        rotta.setAeroportoArrivo("Malpensa");
        rotta.setNazionePartenza("Italia");
        rotta.setNazioneArrivo("Italia");
        rotta.setCompagniaAerea("PoliMI airways");
        rotta.setCittaPartenza("Pistoia");
        rotta.setCittaArrivo("Milano");
        Rotta rotta2 = rottaDAO.saveAndFlush(rotta);
        return rotta2;
    }
    
    private Volo addVolo(){
        Rotta r = addRotta();
        Volo volo = new Volo();
        volo.setDataOra(new Date());
        volo.setRotta(r);
        volo.setNumPasseggeri(3);
        volo.setCosto(120.7f);
        volo.setAbilitato(true);
        Volo volo2 = voceDAO.save(volo);
        return volo2;
    }
}
