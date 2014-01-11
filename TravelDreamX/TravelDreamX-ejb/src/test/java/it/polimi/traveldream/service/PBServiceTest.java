/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.service;

import static it.polimi.traveldream.service.EJBServiceTestSuite.container;
import java.util.Random;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 *
 * @author Dario
 */
public class PBServiceTest {
   
    private static final String pbServiceJdniName = "java:global/classes/PBService";
    private static final String edbServiceJdniName = "java:global/classes/EDBService";
    private static PBServiceLocal pbService = null;
    private static EDBServiceLocal edbService = null;
    private static Random rnd;
    
    private static boolean testSuite = false;
    @BeforeClass
    public static void setUp() throws NamingException{
        if (container != null) testSuite = true;
        else EJBServiceTestSuite.setUp();
        pbService = (PBServiceLocal)container.getContext().lookup(pbServiceJdniName);
        edbService = (EDBServiceLocal)container.getContext().lookup(edbServiceJdniName);
    }
    @AfterClass
    public static void tearDown(){
        if (!testSuite) EJBServiceTestSuite.tearDown();
    }

    @Test @Ignore
    public void testRetrieveVoloByID(){
        
    }
    
    @Test @Ignore
    public void testRetrieveSoggiornoByID(){
        
    }
    
    @Test @Ignore
    public void testRetrieveVisitaByID(){
        
    }
    
    @Test @Ignore
    public void testRetrieveVoloByRotta(){
        
    }
    
    @Test @Ignore
    public void testRetrieveVoloByDataOra(){
        
    }
    
    @Test @Ignore
    public void testRetrieveVoloByCosto(){
        
    }
    
    @Test @Ignore
    public void testRetrieveSoggiornoByAlbergo(){
        
    }
    
    @Test @Ignore
    public void testRetrieveSoggiornoByGiornoInizio(){
        
    }
    
    @Test @Ignore
    public void testRetrieveSoggiornoByCosto(){
        
    }
     
    @Test @Ignore
    public void testRetrieveSoggiornoByNumeroPersone(){
        
    }
    
    @Test @Ignore
    public void testRetrieveVisitaByMuseo(){
        
    }
    
    @Test @Ignore
    public void testRetrieveVisitaByGiorno(){
        
    }
    
    @Test @Ignore
    public void testSalvaVolo(){
        
    }
    
    @Test @Ignore
    public void testSalvaSoggiorno(){
        
    }
    
    @Test @Ignore
    public void testSalvaVisita(){
        
    }
    
    @Test @Ignore
    public void testDisattivaVolo(){
        
    }
    
    @Test @Ignore
    public void testDisattivaSoggiorno(){
        
    }
    
    @Test @Ignore
    public void testDisattivaVisita(){
        
    }
}
