/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Dario
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({RottaDAOTest.class,
                     AlbergoDAOTest.class,
                     MuseoDAOTest.class,
                     UtenteDAOTest.class, 
                     VoceDAOTest.class,
                     VoloDAOTest.class,
                     VisitaDAOTest.class,
                     SoggiornoDAOTest.class,
                     PVDAOTest.class,
})
public class DAOTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
