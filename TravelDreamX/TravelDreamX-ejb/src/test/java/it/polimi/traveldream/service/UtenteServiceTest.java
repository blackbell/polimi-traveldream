/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Utente;
import static it.polimi.traveldream.service.EJBServiceTestSuite.container;
import java.util.Date;
import java.util.Random;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dario
 */
public class UtenteServiceTest {
    
    private static final String jdniName = "java:global/classes/UtenteService";
    static UtenteServiceLocal service = null;
    static Random rnd;
    private static boolean testSuite = false;

    
    @BeforeClass
    public static void setUp() throws NamingException{
        if (container != null) testSuite = true;
        else EJBServiceTestSuite.setUp();
        service = (UtenteServiceLocal)container.getContext().lookup(jdniName);
        rnd = new Random(new Date().getTime());
    }
    
    @AfterClass
    public static void tearDown(){
        if (!testSuite) EJBServiceTestSuite.tearDown();
    }
    
    @Test
    public void testRetrievingService(){
       assertNotNull("Cannot retrieve service class!", service);
    }
    
    @Test
    public void testSignInSuccessful() {
       Utente utente = new Utente("testUserSIS" + rnd.nextInt() +  "@testDomain.polimi.it", "testPsw");
       Utente result = service.registrazione(utente);
       assertNotNull(result);
       assertEquals(utente.getEmail(), result.getEmail());
       assertEquals(utente.getPassword(), result.getPassword());
       assertEquals(utente.getAbilitato(), result.getAbilitato());
       assertEquals(utente.getLivello(), result.getLivello());
    }
    
    @Test
    public void testSignInUnsuccessful(){
        Utente u = new Utente("testUserSIU" + rnd.nextInt() +  "@testDomain.polimi.it", "testPsw");
        Utente u2 = service.registrazione(u);
        Utente u3 = service.registrazione(u);
        assertNotNull(u2);
        assertNull(u3);
    }
    
    @Test
    public void testLoginSuccessful(){
        Utente utente = new Utente("testUserLS" + rnd.nextInt() +  "@testDomain.polimi.it", "testPsw");
        utente.setAbilitato(true);
        service.registrazione(utente);
        
        Utente u2 = service.login(utente);
        assertNotNull(u2);
        assertTrue(u2.getAbilitato());
    }
    
    @Test
    public void testLoginNotAuth(){
        Utente utente = new Utente("testUserLNA" + rnd.nextInt() +  "@testDomain.polimi.it", "testPsw");
        utente.setAbilitato(false);
        service.registrazione(utente);
        
        Utente u2 = service.login(utente);
        assertNotNull(u2);
        assertTrue(!u2.getAbilitato());
    }
    
    @Test
    public void testLoginUserNotExisting(){
        Utente utente = new Utente("testUserLUNE" + rnd.nextInt() +  "@testDomain.polimi.it", "testPsw");
        Utente u2 = service.login(utente);
        assertNull(u2);
    }
    
    @Test
    public void testModificaLivelloUtente(){
        Utente utente = new Utente("testUserSIS" + rnd.nextInt() +  "@testDomain.polimi.it", "testPsw");
        Utente result = service.registrazione(utente);
        
        result.setLivello(2);
        Integer newLevel = service.modificaLivello(result);
        assertEquals(newLevel, new Integer(2));
    }
    
    @Test
    public void testModificaLivelloEAbilitazioneUtente(){
        Utente utente = new Utente("testUserSIS" + rnd.nextInt() +  "@testDomain.polimi.it", "testPsw");
        Utente result = service.registrazione(utente);
        
        result.setAbilitato(!result.getAbilitato());
        result.setLivello(2);
        Integer newLevel = service.modificaLivello(result);
        assertNull(newLevel);
    }
}
