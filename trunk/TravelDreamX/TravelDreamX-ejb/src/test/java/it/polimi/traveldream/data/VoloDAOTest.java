package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Volo;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rh0x
 */
public class VoloDAOTest extends DAOUnitTest{
    
    @Autowired
    private VoceDAO voceDAO;
    @Autowired
    private RottaDAO rottaDAO;
    
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
    
    @Test
    public void retrieveVolo(){
        
    }
    
    private Volo addVolo(){
        Rotta r = addRotta();
        Volo volo = new Volo();
        volo.setDataOra(new Date());
        volo.setIdRotta(r);
        volo.setNumPasseggeri(3);
        volo.setCosto(120.7f);
        Volo volo2 = voceDAO.save(volo);
        return volo2;
    }
    
    
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
