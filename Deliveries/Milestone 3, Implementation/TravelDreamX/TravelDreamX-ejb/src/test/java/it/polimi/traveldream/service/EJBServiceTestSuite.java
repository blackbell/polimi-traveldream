/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Dario
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({EJBServiceTest.class, 
                    UtenteServiceTest.class, 
                    EDBServiceTest.class,
                    PBServiceTest.class,
                    PVServiceTest.class,
})
public class EJBServiceTestSuite {

    public static EJBContainer container;

    @BeforeClass
    public static void setUp() {
        if (container == null){
            System.out.println("Setting up the EJBServiceTestSuite. Creating the EJB Container...");
            Map<String, Object> myMap = new HashMap<>();
            myMap.put("org.glassfish.ejb.embedded.glassfish.installation.root", "./src/test/glassfish");
            myMap.put(EJBContainer.MODULES, new File("target/classes/"));
            container = javax.ejb.embeddable.EJBContainer.createEJBContainer(myMap);
            assertNotNull("Cannot instantiate EJB containter", container);
            System.out.println("EJBContainer created successfully!");
        }
    }

    @AfterClass
    public static void tearDown() {
        if (container != null)
            container.close();
        container = null;
    }
}
