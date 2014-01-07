/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.service;

import static it.polimi.traveldream.service.EJBServiceTestSuite.container;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Dario
 */
public class EJBServiceTest {
   
    @BeforeClass
    public static void setUp(){
        EJBServiceTestSuite.setUp();
    }
    @AfterClass
    public static void tearDown(){
        EJBServiceTestSuite.tearDown();
    }

    @Test
    public void testEmbeddedContainerLoading(){
        assertNotNull("Cannot retrieve container.context", container.getContext());
    }
    
    /*
    @Test
    @Ignore
    public void testGlassfishLoading(){
        System.out.println("======== testGlassfishLoading ==============");
         Server.Builder builder = new Server.Builder("TravelDreamX.testServer");
         Server server = builder.build();
         assertNotNull("Cannot instantiate GF server!", server);
    }
    */
}
