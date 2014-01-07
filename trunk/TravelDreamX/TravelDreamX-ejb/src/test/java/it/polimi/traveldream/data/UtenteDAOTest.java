/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Utente;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Dario
 */
public class UtenteDAOTest extends DAOUnitTest{
    
    @Autowired
    UtenteDAO utenteDAO;
    
    @Test
    public void utenteDAOinjection(){
        assertNotNull("Cannot inject utenteDAO!",utenteDAO);
    }
    
    @Test
    public void savingUser(){
        try{
            Utente user = new Utente("testUser", "testPsw");
            user = utenteDAO.saveAndFlush(user);
            assertNotNull("Cannot register user!",user);
        }catch (Exception ex){
            fail("Cannot register user! Exception raised:" + ex.getMessage());
        }
    }
    
    @Test
    public void retrievingTestUser(){
        try{
            Utente u = addUser();
            Utente user2 = utenteDAO.findOne(u.getEmail());
            Assert.assertEquals("The retrieved user is not as expected!", u, user2);
        }catch(Exception ex){
            fail("Cannot retrieve test user! Exception raised: " + ex.getMessage());
        }
    }
    
    private Utente addUser(){
        Utente user = new Utente("testUser@testDomain.polimi.it", "testPsw");
        return utenteDAO.saveAndFlush(user);
    }
}
