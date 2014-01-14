/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Museo;
import it.polimi.traveldream.model.Visita;
import java.util.Date;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Dario
 */
public class VisitaDAOTest extends DAOUnitTest{
    
    @Autowired
    private VoceDAO voceDAO;
    @Autowired
    private MuseoDAO museoDAO;
    
    @Test
    public void wiringVoceDAO(){
        assertNotNull("Cannot inject voceDAO!", voceDAO);
    }
        
    @Test 
    public void saveVisita(){
        Museo m = new Museo();
        m.setDescrizione("Il Museo Marino Marini attualmente è sede sia del Centro di Documentazione, inaugurato il 23 giugno 1979 nelle sale del Palazzo Comunale di Pistoia, che della Fondazione Marino Marini costituita a Pistoia in data 29 Novembre 1983 e riconosciuta con decreto prefettizio in data 27 Agosto 1985.");
        m.setUrlFoto("http://www.fondazionemarinomarini.it/immagini/museo/museo-min.jpg");
        m.setNome("Museo Marino Marini");
        m.setCitta("Pistoia");
        museoDAO.saveAndFlush(m);
                
        Visita v = new Visita();
        v.setAbilitato(true);
        v.setCosto(50);
        v.setDataOra(new Date());
        v.setMuseo(m);
        v.setNumeroPersone(5);
        Visita v2 = voceDAO.save(v);
        assertNotNull(v2);
    }
    
    @Test 
    public void retrieveVisita(){
        Museo m = new Museo();
        m.setDescrizione("Il Museo Marino Marini attualmente è sede sia del Centro di Documentazione, inaugurato il 23 giugno 1979 nelle sale del Palazzo Comunale di Pistoia, che della Fondazione Marino Marini costituita a Pistoia in data 29 Novembre 1983 e riconosciuta con decreto prefettizio in data 27 Agosto 1985.");
        m.setUrlFoto("http://www.fondazionemarinomarini.it/immagini/museo/museo-min.jpg");
        m.setNome("Museo Marino Marini");
        m.setCitta("Pistoia");
        m = museoDAO.saveAndFlush(m);
                
        Date d = new Date();

        Visita v = new Visita();
        v.setAbilitato(true);
        v.setCosto(50);
        v.setDataOra(d);
        v.setMuseo(m);
        v.setNumeroPersone(5);
        voceDAO.save(v);
        Visita v2 = (Visita) voceDAO.findOne(v.getIdVoce());
        System.out.println("d:" + d);
        System.out.println("v2.date:" + v2.getDataOra());
        assertNotNull(v2);
        assertTrue(v.getMuseo().equals(v2.getMuseo()));
        assertTrue(v.getCosto() == v2.getCosto());
        assertEquals(Math.round(v.getDataOra().getTime()/1000.0),Math.round(v2.getDataOra().getTime()/1000.0));
        assertTrue(v.isAbilitato()== v2.isAbilitato());
        assertTrue(v.getNumeroPersone() == v2.getNumeroPersone());
    }
        
    public void setVoceDAO(VoceDAO voceDAO) {
        this.voceDAO = voceDAO;
    }
}
