/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Museo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
    
    @Test
    public void retrieveMuseo(){
        Museo m = new Museo();
        m.setDescrizione("Il Museo Marino Marini attualmente è sede sia del Centro di Documentazione, inaugurato il 23 giugno 1979 nelle sale del Palazzo Comunale di Pistoia, che della Fondazione Marino Marini costituita a Pistoia in data 29 Novembre 1983 e riconosciuta con decreto prefettizio in data 27 Agosto 1985.");
        m.setUrlFoto("http://www.fondazionemarinomarini.it/immagini/museo/museo-min.jpg");
        m.setNome("Museo Marino Marini");
        m.setCitta("Pistoia");
        museoDAO.saveAndFlush(m);
        assertNotNull("Cannot inject museoDAO!",museoDAO);
        Museo m2 = museoDAO.findOne(m.getIdMuseo());
        assertNotNull("Cannot retrieve museo with ID=" + m.getIdMuseo() + "!", m2);    
    }
    
    @Test
    public void retrieveMuseoByParams(){
        Museo m = new Museo();
        m.setDescrizione("Il Museo Marino Marini attualmente è sede sia del Centro di Documentazione, inaugurato il 23 giugno 1979 nelle sale del Palazzo Comunale di Pistoia, che della Fondazione Marino Marini costituita a Pistoia in data 29 Novembre 1983 e riconosciuta con decreto prefettizio in data 27 Agosto 1985.");
        m.setUrlFoto("http://www.fondazionemarinomarini.it/immagini/museo/museo-min.jpg");
        m.setNome("Museo Marino Marini");
        m.setCitta("Pistoia");
        museoDAO.saveAndFlush(m);
        assertNotNull("Cannot inject museoDAO!",museoDAO);
        assertTrue(museoDAO.findByParams(m.getNome(), m.getCitta()).contains(m));
    }
    
    @Test
    public void saveMuseo(){
        assertNotNull("Cannot inject museoDAO!",museoDAO);
        Museo m = new Museo();
        m.setDescrizione("Il Museo Marino Marini attualmente è sede sia del Centro di Documentazione, inaugurato il 23 giugno 1979 nelle sale del Palazzo Comunale di Pistoia, che della Fondazione Marino Marini costituita a Pistoia in data 29 Novembre 1983 e riconosciuta con decreto prefettizio in data 27 Agosto 1985.");
        m.setUrlFoto("http://www.fondazionemarinomarini.it/immagini/museo/museo-min.jpg");
        m.setNome("Museo Marino Marini");
        m.setCitta("Pistoia");
        Museo m2 = museoDAO.saveAndFlush(m);
        assertNotNull(m2);
    }
    
    @Test
    public void wiringMuseoDAO(){   
        assertNotNull("Cannot inject museoDAO!",museoDAO);
    }
}
