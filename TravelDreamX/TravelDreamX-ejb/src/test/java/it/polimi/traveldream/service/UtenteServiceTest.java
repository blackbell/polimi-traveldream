/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.data.UtenteDAO;
import it.polimi.traveldream.model.Utente;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Dario
 */
@Ignore
public class UtenteServiceTest {
    
    public UtenteServiceTest() {
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

    /**
     * Test of getUtenteDAO method, of class UtenteService.
     */
    @Test
    public void testGetUtenteDAO() throws Exception {
        System.out.println("getUtenteDAO");
        UtenteService instance = new UtenteService();
        UtenteDAO expResult = null;
        UtenteDAO result = instance.getUtenteDAO();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUtenteDAO method, of class UtenteService.
     */
    @Test
    public void testSetUtenteDAO() throws Exception {
        System.out.println("setUtenteDAO");
        UtenteDAO utenteDAO = null;
        UtenteService instance = new UtenteService();
        instance.setUtenteDAO(utenteDAO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrazione method, of class UtenteService.
     */
    @Test
    public void testRegistrazione() throws Exception {
        System.out.println("registrazione");
        Utente utente = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        UtenteServiceLocal instance = (UtenteServiceLocal)container.getContext().lookup("java:global/classes/UtenteService");
        Utente expResult = null;    
        Utente result = instance.registrazione(utente);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
