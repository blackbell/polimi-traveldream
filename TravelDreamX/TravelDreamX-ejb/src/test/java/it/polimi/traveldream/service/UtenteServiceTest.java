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
        String testUserName = "testUserLS" + rnd.nextInt() +  "@testDomain.polimi.it";
        System.out.println("UtenteServiceTest.testLoginSuccessful");
        Utente utente = new Utente(testUserName, "testPsw");
        utente.setAbilitato(true);
        service.registrazione(utente);
        utente = new Utente(testUserName, "testPsw");
        Utente u2 = service.login(utente);
        assertNotNull(u2);
        assertTrue(u2.getAbilitato());
    }
    
    @Test
    public void testLoginNotAuth(){
        String testUserName = "testUserLNA" + rnd.nextInt() +  "@testDomain.polimi.it";
        System.out.println("UtenteServiceTest.testLoginNotAuth");
        Utente utente = new Utente(testUserName, "testPsw");
        utente.setAbilitato(false);
        service.registrazione(utente);
        
        utente = new Utente(testUserName, "testPsw");
        Utente u2 = service.login(utente);
        assertNotNull(u2);
        assertTrue(!u2.getAbilitato());
    }
    
    @Test
    public void testLoginUserNotExisting(){
        System.out.println("UtenteServiceTest.testLoginUserNotExisting");
        Utente utente = new Utente("testUserLUNE" + rnd.nextInt() +  "@testDomain.polimi.it", "testPsw");
        Utente u2 = service.login(utente);
        assertNull(u2);
    }
    
    @Test
    public void testModificaLivelloUtente(){
        String testUserName = "testUserMLU" + rnd.nextInt() +  "@testDomain.polimi.it";
        System.out.println("UtenteServiceTest.testModificaLivelloUtente");
        Utente utente = new Utente(testUserName, "testPsw");
        service.registrazione(utente);
        
        utente = new Utente(testUserName, "testPsw");
        utente.setLivello(2);
        Integer newLevel = service.modificaLivello(utente);
        assertEquals(new Integer(2), newLevel);
    }
    
    @Test
    public void testModificaLivelloEAbilitazioneUtente(){
        String testUserName = "testUserMLAU" + rnd.nextInt() +  "@testDomain.polimi.it";
        System.out.println("UtenteServiceTest.testModificaLivelloEAbilitazioneUtente");
        Utente utente = new Utente(testUserName, "testPsw");
        utente.setAbilitato(true);
        service.registrazione(utente);

        utente = new Utente(testUserName, "testPsw");        
        utente.setAbilitato(false);
        utente.setLivello(2);
        Integer newLevel = service.modificaLivello(utente);
        assertEquals(new Integer(2), newLevel);
        assertTrue(service.login(utente).getAbilitato());
    }
}
