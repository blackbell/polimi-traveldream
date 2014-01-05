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
    public void emptyTest(){}
    
    @Test
    public void wiringVoloDAO(){
        assertNotNull("Cannot inject rottaDAO!", voceDAO);
    }
        
    @Test
    public void saveVolo(){
        assertNotNull("Cannot inject rottaDAO!", voceDAO);
        Volo volo = new Volo();
        volo.setDataOra(new Date());
        volo.setIdRotta(new Rotta(1));
        volo.setNumPasseggeri(3);
        volo.setCosto(120.7f);
//        volo.setTipo("3");
        Volo volo2 = voceDAO.save(volo);
        assertEquals("The retrieved user is not as expected!", volo2, volo);
    }

    public void setVoceDAO(VoceDAO voceDAO) {
        this.voceDAO = voceDAO;
    }
}
