/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.data.DAOUnitTest;
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
public class RottaDAOTest extends DAOUnitTest {
    
    public RottaDAOTest() {
        
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
    
    @Autowired
    public RottaDAO rottaDAO;
    
    public void setRottaDAO(RottaDAO rottaDAO) {
        this.rottaDAO = rottaDAO;
    }

    @Test
    public void wiringRottaDAO(){
        assertNotNull("Cannot inject utenteDAO!",rottaDAO);
    }
    
    
    @Test
    public void retrieveRotta(){
        assertNotNull("Cannot inject rottaDAO!",rottaDAO);
        Rotta rotta = rottaDAO.findOne(1);
        assertNotNull("Cannot retrieve route with ID=1!", rotta);
    }
}
