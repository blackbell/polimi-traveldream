/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Pagamento;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.model.Volo;
import static it.polimi.traveldream.service.EJBServiceTestSuite.container;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Random;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dario
 */
public class PagamentoServiceTest {
   
    private static final String PVServiceJdniName = "java:global/classes/PVService";
    private static final String utenteServiceJdniName = "java:global/classes/UtenteService";
    private static final String pbServiceJdniName = "java:global/classes/PBService";
    private static final String edbServiceJdniName = "java:global/classes/EDBService";
    private static final String pagamentoServiceJdniName = "java:global/classes/PagamentoService";
    private static PVServiceLocal pvService = null;
    private static UtenteServiceLocal utenteService = null;
    private static PBServiceLocal pbService = null;
    private static EDBServiceLocal edbService = null;
    private static PagamentoServiceLocal pagamentoService = null;
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
        pagamentoService = (PagamentoServiceLocal)container.getContext().lookup(pagamentoServiceJdniName);
        rnd = new Random(new Date().getTime());
    }
    
    @AfterClass
    public static void tearDown(){
        if (!testSuite) EJBServiceTestSuite.tearDown();
    }

    @Test
    public void testPagamentoPB(){
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Aeroporto di Partenza");
        r.setAeroportoArrivo("Aeroporto di Arrivo");
        r.setCittaPartenza("Citta di Partenza");
        r.setCittaArrivo("testPagamentoPB");
        r.setNazionePartenza("Nazione di Partenza");
        r.setNazioneArrivo("Nazione di Arrivo");
        r.setCompagniaAerea("Aereo in compagnia");
        r = edbService.salvaRotta(r);
        
        Volo v = new Volo();
        v.setCosto(100f);
        v.setRotta(r);
        v.setDataOra(new Date());
//        v.setNumPasseggeri(3);
        v.setAbilitato(true);
        v = pbService.saveVolo(v);
        v = pbService.getVoloByID(v.getIdVoce());
        
        Utente u = new Utente(rnd.nextInt() + "@ServiceTest.polimi.it","polimi");
        u = utenteService.registrazione(u);
        
        Pacchetto p = new Pacchetto();
        p.setAbilitato(true);
        p.setProprietario(u);
        p.setDataOraCreazione(new Date());
        p.setTipo(Pacchetto.PREDEFINITO);
        p.setVoci(new ArrayList<Voce>());
        p.setNumeroPersone(3);
        p.getVoci().add(v);
        Pacchetto p2 = pvService.salvaPV(p);
        
        Pagamento pagamento = pagamentoService.pagamentoPB(p2, v, u);
        Assert.assertNotNull(pagamento);
    }
    
    @Test
    public void testPagamentoPBnonNelPV(){
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Aeroporto di Partenza");
        r.setAeroportoArrivo("Aeroporto di Arrivo");
        r.setCittaPartenza("Citta di Partenza");
        r.setCittaArrivo("testPagamentoPB");
        r.setNazionePartenza("Nazione di Partenza");
        r.setNazioneArrivo("Nazione di Arrivo");
        r.setCompagniaAerea("Aereo in compagnia");
        r = edbService.salvaRotta(r);
        
        Volo v = new Volo();
        v.setCosto(100f);
        v.setRotta(r);
        v.setDataOra(new Date());
//        v.setNumPasseggeri(3);
        v.setAbilitato(true);
        v = pbService.saveVolo(v);
        v = pbService.getVoloByID(v.getIdVoce());
        Volo ritorno = new Volo();
        ritorno.setCosto(100f);
        ritorno.setRotta(r);
        ritorno.setDataOra(new Date(v.getDataOra().getTime() + 3 * 24 * 60 * 60 * 1000));
//        ritorno.setNumPasseggeri(3);
        ritorno.setAbilitato(true);
        ritorno = pbService.saveVolo(ritorno);
        ritorno = pbService.getVoloByID(ritorno.getIdVoce());
        
        Utente u = new Utente(rnd.nextInt() + "@ServiceTest.polimi.it","polimi");
        u = utenteService.registrazione(u);
        
        Pacchetto p = new Pacchetto();
        p.setAbilitato(true);
        p.setProprietario(u);
        p.setDataOraCreazione(new Date());
        p.setTipo(Pacchetto.PREDEFINITO);
        p.setVoci(new ArrayList<Voce>());
        p.setNumeroPersone(2);
//        p.getVoci().add(v);
        p.getVoci().add(ritorno);
        Pacchetto p2 = pvService.salvaPV(p);
        
        Pagamento pagamento = pagamentoService.pagamentoPB(p2, v, u);
        Assert.assertNull(pagamento);
    }
    
    @Test
    public void testPagamentoPV(){
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Aeroporto di Partenza");
        r.setAeroportoArrivo("Aeroporto di Arrivo");
        r.setCittaPartenza("Citta di Partenza");
        r.setCittaArrivo("testPagamentoPB");
        r.setNazionePartenza("Nazione di Partenza");
        r.setNazioneArrivo("Nazione di Arrivo");
        r.setCompagniaAerea("Aereo in compagnia");
        r = edbService.salvaRotta(r);
        
        Volo v = new Volo();
        v.setCosto(100f);
        v.setRotta(r);
        v.setDataOra(new Date());
//        v.setNumPasseggeri(3);
        v.setAbilitato(true);
        v = pbService.saveVolo(v);
        v = pbService.getVoloByID(v.getIdVoce());
        Volo ritorno = new Volo();
        ritorno.setCosto(100f);
        ritorno.setRotta(r);
        ritorno.setDataOra(new Date(v.getDataOra().getTime() + 3 * 24 * 60 * 60 * 1000));
//        ritorno.setNumPasseggeri(3);
        ritorno.setAbilitato(true);
        ritorno = pbService.saveVolo(ritorno);
        ritorno = pbService.getVoloByID(ritorno.getIdVoce());
        
        Utente u = new Utente(rnd.nextInt() + "@ServiceTest.polimi.it","polimi");
        u = utenteService.registrazione(u);
        
        Pacchetto p = new Pacchetto();
        p.setAbilitato(true);
        p.setProprietario(u);
        p.setDataOraCreazione(new Date());
        p.setNumeroPersone(2);
        p.setTipo(Pacchetto.PREDEFINITO);
        p.setVoci(new ArrayList<Voce>());
        p.getVoci().add(v);
        p.getVoci().add(ritorno);
        Pacchetto p2 = pvService.salvaPV(p);
        
        List<Pagamento> pagamenti = pagamentoService.pagamentoPV(p2, u);
        boolean trovato = false;
        for(Pagamento pp : pagamenti)
            trovato |= pp.getPacchetto().equals(p);
        Assert.assertTrue(trovato);
    }
    
    
    
}
