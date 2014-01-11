/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.data;

import static org.junit.Assert.assertNotNull;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Dario
 */
public class VisitaDAOTest extends DAOTestSuite{
    
    @Autowired
    private VoceDAO voceDAO;
    @Autowired
    private MuseoDAO museoDAO;
    
    @Test
    public void wiringVoceDAO(){
        assertNotNull("Cannot inject voceDAO!", voceDAO);
    }
        
    @Test @Ignore
    public void saveVisita(){

    }
    
    @Test @Ignore
    public void retrieveVisita(){

    }
        
    public void setVoceDAO(VoceDAO voceDAO) {
        this.voceDAO = voceDAO;
    }
}
