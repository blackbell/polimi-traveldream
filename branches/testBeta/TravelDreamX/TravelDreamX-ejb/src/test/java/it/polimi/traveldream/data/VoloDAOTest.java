package it.polimi.traveldream.data;

import it.polimi.traveldream.data.DAOUnitTest;
import it.polimi.traveldream.data.VoceDAO;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Volo;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rh0x
 */
public class VoloDAOTest extends DAOUnitTest{
    
    @Autowired
    private VoceDAO voceDAO;
    
    public VoloDAOTest() {
        
    }
    
    @Test
    public void wiringVoceDAO(){
        assertNotNull("Cannot inject voceDAO!", voceDAO);
    }
        
    @Test
    public void saveVolo(){
        Rotta r = addRotta();
        assertNotNull("Cannot inject voceDAO!", voceDAO);
        Volo volo = new Volo();
        volo.setDataOra(new Date());
        volo.setIdRotta(r);
        volo.setNumPasseggeri(3);
        volo.setCosto(120.7f);
        Volo volo2 = voceDAO.save(volo);
        assertEquals("The retrieved flight is not as expected!", volo2, volo);
    }

    @Autowired
    RottaDAO rottaDAO;
    private Rotta addRotta(){
        Rotta rotta = new Rotta();
        rotta.setCittàPartenza("Pistoia");
        rotta.setCittàArrivo("Milano");
        Rotta rotta2 = rottaDAO.saveAndFlush(rotta);
        return rotta2;
    }
    
    public void setVoceDAO(VoceDAO voceDAO) {
        this.voceDAO = voceDAO;
    }
}
