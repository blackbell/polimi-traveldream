/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.data.DAOUnitTest;
import it.polimi.traveldream.data.UtenteDAO;
import it.polimi.traveldream.model.Utente;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Dario
 */
public class UtenteDAOTest extends DAOUnitTest{
    
    public UtenteDAOTest() {
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
    UtenteDAO utenteDao;
    
    @Test
    public void utenteDAOinjection(){
        assertNotNull("Cannot inject utenteDAO!",utenteDao);
    }
    
    @Test
    public void savingUser(){
        try{
            Utente user = new Utente("testUser", "testPsw");
            user = utenteDao.saveAndFlush(user);
            assertNotNull("Cannot register user!",user);
        }catch (Exception ex){
            fail("Cannot register user! Exception raised:" + ex.getMessage());
        }
    }
    
    @Test
    public void retrievingTestUser(){
        try{
            Utente user = new Utente("testUser@testDomain.polimi.it", "testPsw");
            user = utenteDao.saveAndFlush(user);
            Utente user2 = utenteDao.findOne(user.getEmail());
            Assert.assertEquals("The retrieved user is not as expected!", user, user2);
        }catch(Exception ex){
            fail("Cannot retrieve test user! Exception raised: " + ex.getMessage());
        }
    }
}
