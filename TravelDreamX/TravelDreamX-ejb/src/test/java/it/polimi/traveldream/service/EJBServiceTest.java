/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Utente;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.glassfish.internal.embedded.Server;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Dario
 */
public class EJBServiceTest {
    
   @Test
   @Ignore
   public void testGlassfishLoading(){
       System.out.println("======== testGlassfishLoading ==============");
        Server.Builder builder = new Server.Builder("TravelDreamX.testServer");
        Server server = builder.build();
        assertNotNull("Cannot instantiate GF server!", server);
   }
   
   @Test
   @Ignore
   public void testEmbeddedContainerLoading() throws InterruptedException{
       System.out.println("======== testEmbeddedContainerLoading ==============");
       Map<String, Object> myMap = new HashMap<String, Object>();
       myMap.put("org.glassfish.ejb.embedded.glassfish.installation.root", "./src/test/glassfish");
       myMap.put(EJBContainer.MODULES, new File("target/classes/"));
       EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(myMap);
       assertNotNull("Cannot instantiate EJB containter", container);
       assertNotNull("Cannot retrieve container.context", container.getContext());
       container.close();
   }
   
   @Test
   //@Ignore
   public void testEmbeddedContainerLoadingService() throws NamingException{
       System.out.println("======== testEmbeddedContainerLoadingService ==============");
       Map<String, Object> myMap = new HashMap<String, Object>();
       myMap.put("org.glassfish.ejb.embedded.glassfish.installation.root", "./src/test/glassfish");
       myMap.put(EJBContainer.MODULES, new File("target/classes"));
       EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(myMap);
       assertNotNull("Cannot instantiate EJB containter!", container);
       UtenteServiceLocal instance = (UtenteServiceLocal)container.getContext().lookup("java:global/classes/UtenteService");
       assertNotNull("Cannot retrieve service class!", instance);
       System.out.println("---------------- invoking service ----------------");
       Utente utente = new Utente("testUser", "testPsw");
       Utente result = instance.registrazione(utente);
       assertEquals(utente, result);
       container.close();
   }
}
