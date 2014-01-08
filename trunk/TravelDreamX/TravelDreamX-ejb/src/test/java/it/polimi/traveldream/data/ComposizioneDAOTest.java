/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.Composizione;
import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Soggiorno;
import it.polimi.traveldream.model.TipoPacchetto;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.model.Volo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Dario
 */
public class ComposizioneDAOTest extends DAOUnitTest{
    
    @Autowired
    ComposizioneDAO composizioneDAO;
    @Autowired
    private PVDAO pvDAO;
    @Autowired
    private UtenteDAO utenteDAO;
    @Autowired
    private VoceDAO voceDAO;
    @Autowired
    private RottaDAO rottaDAO;
    @Autowired
    AlbergoDAO albergoDAO;
    
    @Test
    public void wiringComposizioneDAO(){
        assertNotNull("Cannot inject composizioneDAO!", composizioneDAO);
    }
    
    @Test
    public void saveComposizionePVPredVolo(){
        Pacchetto pv = addPacchetto();
        Volo v = addVolo();
        Composizione c = new Composizione(pv.getIdPacchetto(), v.getIdVoce());
        Composizione c2 = composizioneDAO.saveAndFlush(c);
        assertNotNull(c2);
    }
    
    @Test
    public void retrieveComposizioniPerPV(){
        Pacchetto pv = addPacchetto();
        Volo v = addVolo();
        Soggiorno s = addSoggiorno();
        Composizione cVolo = new Composizione(pv.getIdPacchetto(), v.getIdVoce());
        Composizione cSoggiorno = new Composizione(pv.getIdPacchetto(), s.getIdVoce());
        composizioneDAO.save(cVolo);
        composizioneDAO.save(cSoggiorno);
        List<Composizione> pbsExpected = new ArrayList<>();
        pbsExpected.add(cVolo);
        pbsExpected.add(cSoggiorno);
        List<Composizione> pbs = composizioneDAO.findByIdPacchetto(pv.getIdPacchetto());
        assertEquals(pbs, pbsExpected);
    }
    
    public Pacchetto addPacchetto(){
        Pacchetto p = new Pacchetto();
        p.setTipo(TipoPacchetto.PREDEFINITO);
        p.setProprietario(addUser());
        p.setDataOraCreazione(new Date());
        Pacchetto p2 = pvDAO.save(p);
        return p2;
    }
    
     public Utente addUser(){
        Utente user = new Utente("testUser@testDomain.polimi.it", "testPsw");
        return utenteDAO.saveAndFlush(user);
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
    
    public Soggiorno addSoggiorno(){
        Albergo a2 = addHotel();
        Soggiorno s = new Soggiorno();
        s.setIdAlbergo(a2);
        s.setCosto(200f);
        s.setGiornoInizio(new Date());
        s.setGiornoFine(new Date());
        s.setNumeroPersone(2);
        Soggiorno s2 = voceDAO.save(s);
        return s2;
    }
   
    private Albergo addHotel(){
        Albergo albergo = new Albergo();
        albergo.setNome("HOTEL PIPPO");
        albergo.setCitta("Milano");
        albergo.setStelle(3);
        albergo.setUrlFoto("/");
        Albergo a2 = albergoDAO.saveAndFlush(albergo);
        return a2;
    }
}
