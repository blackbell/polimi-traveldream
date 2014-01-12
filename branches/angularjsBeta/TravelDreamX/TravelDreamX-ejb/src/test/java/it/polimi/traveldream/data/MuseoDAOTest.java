/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.data;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Dario
 */
public class MuseoDAOTest extends DAOUnitTest {
    
    @Autowired
    private MuseoDAO museoDAO;

    public MuseoDAO getMuseoDAO() {
        return museoDAO;
    }

    public void setMuseoDAO(MuseoDAO museoDAO) {
        this.museoDAO = museoDAO;
    }
    
    @Test @Ignore
    public void retrieveMuseo(){
        
    }
    
    @Test @Ignore
    public void retrieveMuseoByParams(){
        
    }
    
    @Test @Ignore
    public void saveMuseo(){
        
    }
    
    @Test
    public void wiringMuseoDAO(){
        
    }
}
