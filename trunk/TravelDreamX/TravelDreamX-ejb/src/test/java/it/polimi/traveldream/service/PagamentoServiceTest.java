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
public class PagamentoServiceTest {
   
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
    
    
}
