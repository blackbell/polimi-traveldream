/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Utente;
import static it.polimi.traveldream.service.EJBServiceTestSuite.container;
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
    
    @BeforeClass
    public static void setUp() throws NamingException{
        EJBServiceTestSuite.setUp();
        service = (UtenteServiceLocal)container.getContext().lookup(jdniName);
    }
    
    @AfterClass
    public static void tearDown(){
        EJBServiceTestSuite.tearDown();
    }
    
    @Test
    public void testRetrievingService(){
       assertNotNull("Cannot retrieve service class!", service);
    }
    
    @Test
    public void testRegistrazione() {
       Utente utente = new Utente("testUser@testDomain.polimi.it", "testPsw");
       Utente result = service.registrazione(utente);
       assertEquals(utente.getEmail(), result.getEmail());
       assertEquals(utente.getPassword(), result.getPassword());
       assertEquals(utente.getAbilitato(), result.getAbilitato());
       assertEquals(utente.getLivello(), result.getAbilitato());
    }
}
