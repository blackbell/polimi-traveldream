package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Soggiorno;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.model.Volo;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
        assertNotNull("Cannot save flight!",volo2);
    }
    
    @Test
    public void retrieveVolo(){
        assertNotNull("Cannot inject voceDAO!", voceDAO);
        Volo volo = addVolo();
        Voce v = voceDAO.findOne(volo.getIdVoce());
        Volo volo2 = (Volo)volo;
        assertTrue("The retrieved object is not instance of Volo", v instanceof Volo);
        assertTrue("The retrieved object is not instance of Volo", ! (v instanceof Soggiorno));
        assertEquals("The retrieved flight is not as expected!", volo2.getDataOra(), volo.getDataOra());
        assertEquals("The retrieved flight is not as expected!", volo2.getIdRotta(), volo.getIdRotta());
        assertEquals("The retrieved flight is not as expected!", volo2.getNumPasseggeri(), volo.getNumPasseggeri());
        assertTrue("The retrieved flight is not as expected!", volo2.getCosto() == volo.getCosto());
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
