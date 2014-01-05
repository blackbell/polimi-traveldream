/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.test;

import it.polimi.traveldream.data.RottaDAO;
import it.polimi.traveldream.model.Rotta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Dario
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
public class RottaDAOTest {
    
    public RottaDAOTest() {
        
    }

    public void setRottaDAO(RottaDAO rottaDAO) {
        this.rottaDAO = rottaDAO;
    }

    public RottaDAO getRottaDAO() {
        return rottaDAO;
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void emptyTest(){}
    
    //@Autowired
    RottaDAO rottaDAO;
    
    @Test
    public void wiringRottaDAO(){
        assertNotNull(rottaDAO);
    }
    
    @Autowired
    private message.Message message;
    
    @Test
    public void messageTest(){
        assertNotNull(message);
        System.out.println(message.getMsg());
        assertEquals(message.getMsg(), "Spring is fun.");
    }
    
    @Test
    public void retrieveRotta(){
        assertNotNull(rottaDAO);
        Rotta rotta = rottaDAO.findOne(1);
        assertNotNull(rotta);
    }
}
