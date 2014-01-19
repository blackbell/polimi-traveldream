/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
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
   
    private static boolean testSuite = false;
    @BeforeClass
    public static void setUp(){
        if (container != null) testSuite = true;
        else EJBServiceTestSuite.setUp();
    }
    @AfterClass
    public static void tearDown(){
        if (!testSuite) EJBServiceTestSuite.tearDown();
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
