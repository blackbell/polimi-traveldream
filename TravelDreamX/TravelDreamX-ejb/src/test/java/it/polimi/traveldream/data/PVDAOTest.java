/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.TipoPacchetto;
import it.polimi.traveldream.model.Utente;
import java.util.Date;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author Dario
 */
public class PVDAOTest extends DAOUnitTest {
    
    @Autowired
    private PVDAO pvDAO;
    @Autowired
    private UtenteDAO utenteDAO;
    
    @Test
    public void autowiringPVDAO(){  
        assertNotNull(pvDAO);
    }
    
    @Test
    public void savePVpredefinito(){
        Pacchetto p = new Pacchetto();
        p.setAbilitato(true);
        p.setTipo(Pacchetto.PREDEFINITO);
        p.setProprietario(addUser());
        p.setDataOraCreazione(new Date());
        Pacchetto p2 = pvDAO.save(p);
        assertNotNull("Cannot save a PV!", p2);
    }
    
     public Utente addUser(){
        Utente user = new Utente("testUser@testDomain.polimi.it", "testPsw");
        return utenteDAO.saveAndFlush(user);
    }
}
