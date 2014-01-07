/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.data.UtenteDAO;
import it.polimi.traveldream.model.Utente;
import static it.polimi.traveldream.service.EJBServiceTestSuite.container;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.glassfish.internal.embedded.Server;
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
public class UtenteServiceTest {
    
    @BeforeClass
    public static void pippo(){
        EJBServiceTestSuite.setUp();
    }
    
    @AfterClass
    public static void tearDown(){
        EJBServiceTestSuite.tearDown();
    }
    
    private String jdniName = "java:global/classes/UtenteService";
    
    @Test
    public void testRetrievingService() throws NamingException{
       UtenteServiceLocal instance = (UtenteServiceLocal)container.getContext().lookup(jdniName);
       assertNotNull("Cannot retrieve service class!", instance);
    }
    
    @Test
    public void testRegistrazione() throws NamingException{
       UtenteServiceLocal instance = (UtenteServiceLocal)container.getContext().lookup(jdniName);
       assertNotNull("Cannot retrieve service class!", instance);
       Utente utente = new Utente("testUser@testDomain.polimi.it", "testPsw");
       Utente result = instance.registrazione(utente);
       assertEquals(utente, result);
    }
}
