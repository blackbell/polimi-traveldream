/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.TipoPacchetto;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.model.Volo;
import static it.polimi.traveldream.service.EJBServiceTestSuite.container;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Dario
 */
public class PVServiceTest {
    private static final String PVServiceJdniName = "java:global/classes/PVService";
    private static final String utenteServiceJdniName = "java:global/classes/UtenteService";
    private static final String pbServiceJdniName = "java:global/classes/PBService";
    private static final String edbServiceJdniName = "java:global/classes/EDBService";
    private static PVServiceLocal pvService = null;
    private static UtenteServiceLocal utenteService = null;
    private static PBServiceLocal pbService = null;
    private static EDBServiceLocal edbService = null;
    private static Random rnd;
    private static boolean testSuite = false;
    
    @BeforeClass
    public static void setUpClass() throws NamingException {
        if (container != null) testSuite = true;
        else EJBServiceTestSuite.setUp();
        pvService = (PVServiceLocal)container.getContext().lookup(PVServiceJdniName);
        utenteService = (UtenteServiceLocal)container.getContext().lookup(utenteServiceJdniName);
        pbService = (PBServiceLocal)container.getContext().lookup(pbServiceJdniName);
        edbService = (EDBServiceLocal)container.getContext().lookup(edbServiceJdniName);
        rnd = new Random(new Date().getTime());
    }
    
    @AfterClass
    public static void tearDownClass() {
        if (!testSuite) EJBServiceTestSuite.tearDown();
    }
    
    @Test
    public void testAddVoloToPV(){
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Aeroporto di Partenza");
        r.setAeroportoArrivo("Aeroporto di Arrivo");
        r.setCittaPartenza("Citta di Partenza");
        r.setCittaArrivo("Citta di Arrivo");
        r.setNazionePartenza("Nazione di Partenza");
        r.setNazioneArrivo("Nazione di Arrivo");
        r.setCompagniaAerea("Aereo in compagnia");
        r = edbService.salvaRotta(r);
        
        Volo v = new Volo();
        v.setCosto(100);
        v.setRotta(r);
        v.setDataOra(new Date());
        v.setNumPasseggeri(3);
        v.setAbilitato(true);
        v = pbService.saveVolo(v);
        v = pbService.getVoloByID(v.getIdVoce());
        
        Utente u = new Utente(rnd.nextInt() + "@ServiceTest.polimi.it","polimi");
        u = utenteService.registrazione(u);
        
        Pacchetto p = new Pacchetto();
        p.setProprietario(u);
        p.setDataOraCreazione(new Date());
        p.setTipo(TipoPacchetto.PREDEFINITO);
        p.setVoci(new ArrayList<Voce>());
        pvService.addPBtoPV(v, p);
    }
    
    @Test @Ignore
    public void testAddVoloESoggiornoToPV(){
        
    }
    
    @Test @Ignore
    public void testAddVoloESoggiornoEVisitaToPV(){
        
    }
    
    @Test @Ignore
    public void testAddVoloEVisitaToPV(){
        
    }
    
    @Test @Ignore
    public void testAddSoggiornoToPV(){
        
    }
    
    @Test @Ignore
    public void testAddVisitaToPV(){
        
    }
    
    @Test @Ignore
    public void testAddVoloDisabilitatoESoggiornoToPV(){
        
    }
    
    @Test @Ignore
    public void testAddVoloESoggiornoDisabilitatoToPV(){
        
    }
    
    @Test @Ignore
    public void testAddVoloESoggiornoEVisitaDiabilitataToPV(){
        
    }
    
    @Test @Ignore
    public void testAddVoloEVisitaDisabilitataToPV(){
        
    }
    
    @Test @Ignore
    public void testAddVoloDisasbilitatoEVisitaToPV(){
        
    }
    
    @Test @Ignore
    public void testAddSoggiornoDisabilitatoToPV(){
        
    }
    
    @Test @Ignore
    public void testAddVisitaDisabilitataToPV(){
        
    }
}
