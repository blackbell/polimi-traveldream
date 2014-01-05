package it.polimi.traveldream.test;

import it.polimi.traveldream.data.VoceDAO;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Volo;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author rh0x
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-appContext.xml"})
public class VoloDAOTest {
    
    @Autowired
    private VoceDAO voceDAO;
    
    public VoloDAOTest() {
    }
    
    @Test
    public void emptyTest(){}
    
    @Test
    public void wiringVoloDAO(){
        assertNotNull(voceDAO);
    }
    
    
    @Test
    public void saveVolo(){
        assertNotNull(voceDAO);
        Volo volo = new Volo();
        volo.setDataOra( new Date());
        volo.setIdRotta(new Rotta(1));
        volo.setNumPasseggeri(3);
        volo.setCosto(120.7f);
        volo.setTipo("3");
        Volo volo2 = voceDAO.save(volo);
        assertNotNull( volo2);
    }

    public void setVoceDAO(VoceDAO voceDAO) {
        this.voceDAO = voceDAO;
    }
    
    
    
}
