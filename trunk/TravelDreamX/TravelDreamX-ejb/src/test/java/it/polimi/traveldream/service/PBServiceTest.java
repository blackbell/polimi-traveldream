/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.data.TestUtilities;
import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.Museo;
import it.polimi.traveldream.model.Visita;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Soggiorno;
import it.polimi.traveldream.model.TipoPB;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.model.Volo;
import static it.polimi.traveldream.service.EJBServiceTestSuite.container;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 *
 * @author Dario
 */
public class PBServiceTest {
   
    private static final String pbServiceJdniName = "java:global/classes/PBService";
    private static final String edbServiceJdniName = "java:global/classes/EDBService";
    private static PBServiceLocal pbService = null;
    private static EDBServiceLocal edbService = null;
    private static Random rnd;
    
    private static boolean testSuite = false;
    @BeforeClass
    public static void setUp() throws NamingException{
        if (container != null) testSuite = true;
        else EJBServiceTestSuite.setUp();
        pbService = (PBServiceLocal)container.getContext().lookup(pbServiceJdniName);
        edbService = (EDBServiceLocal)container.getContext().lookup(edbServiceJdniName);
    }
    @AfterClass
    public static void tearDown(){
        if (!testSuite) EJBServiceTestSuite.tearDown();
    }

    @Test
    public void testRetrieveVoloByParams(){
        System.out.println("testRetrieveVoloByParams()");
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Breda Air Deposit");
        r.setAeroportoArrivo("Atlandide");
        r.setNazionePartenza("Italia");
        r.setNazioneArrivo("Mondo sottomarino");
        r.setCompagniaAerea("Mille bolle blu");
        r.setCittaPartenza("Pistoia");
        r.setCittaArrivo("Atlantide");
        edbService.salvaRotta(r);
        
        Volo v = new Volo();
        v.setDataOra(new Date());
        v.setRotta(r);
        v.setNumPasseggeri(3);
        v.setCosto(120.7f);
        v.setAbilitato(false);
        pbService.saveVolo(v);
        
        ParametriRicercaPB params = new ParametriRicercaPB();
        params.setTipo(TipoPB.Volo);
        params.setRotta(r);
        params.setData(v.getDataOra());
        params.setNumPersone(v.getNumPasseggeri());
//        params.setCosto(v.getCosto());
        
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(v));
    }
    
    @Test @Ignore
    public void testRetrieveVoloByID(){
        
    }
    
    @Test @Ignore
    public void testRetrieveSoggiornoByID(){
        
    }
    
    @Test @Ignore
    public void testRetrieveVisitaByID(){
        
    }
    
    @Test
    public void testRetrieveVoloByRotta(){
        System.out.println("testRetrieveVoloByRotta()");
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Breda Air Deposit");
        r.setAeroportoArrivo("Atlandide");
        r.setNazionePartenza("Italia");
        r.setNazioneArrivo("Mondo sottomarino");
        r.setCompagniaAerea("Mille bolle blu");
        r.setCittaPartenza("Pistoia");
        r.setCittaArrivo("Atlantide");
        edbService.salvaRotta(r);
        
        Volo v = new Volo();
        v.setDataOra(new Date());
        v.setRotta(r);
        v.setNumPasseggeri(3);
        v.setCosto(120.7f);
        v.setAbilitato(false);
        pbService.saveVolo(v);
        
        ParametriRicercaPB params = new ParametriRicercaPB();
        params.setTipo(TipoPB.Volo);
        params.setRotta(r);
        
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(v));
    }
    
    @Test 
    public void testRetrieveVoloByDataOra(){
        System.out.println("testRetrieveVoloByDataOra()");
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Breda Air Deposit");
        r.setAeroportoArrivo("Atlandide");
        r.setNazionePartenza("Italia");
        r.setNazioneArrivo("Mondo sottomarino");
        r.setCompagniaAerea("Mille bolle blu");
        r.setCittaPartenza("Pistoia");
        r.setCittaArrivo("Atlantide");
        edbService.salvaRotta(r);
        
        Volo v = new Volo();
        v.setDataOra(new Date());
        v.setRotta(r);
        v.setNumPasseggeri(3);
        v.setCosto(120.7f);
        v.setAbilitato(false);
        pbService.saveVolo(v);
        
        ParametriRicercaPB params = new ParametriRicercaPB();
        params.setTipo(TipoPB.Volo);
        params.setData(v.getDataOra());
        
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(v));
    }
    
    @Test @Ignore
    public void testRetrieveVoloByCosto(){
        
    }
    
    @Test
    public void testRetrieveSoggiornoByParams(){
        System.out.println("testRetrieveSoggiornoByParams()");
        Albergo a = new Albergo();
        a.setCitta("Paperopoli");
        a.setNome("Pensione deposito");
        a.setUrlFoto(TestUtilities.getRandomImageLink());
        a.setStelle(4);
        edbService.salvaAlbergo(a);
        
        Soggiorno s = new Soggiorno();
        s.setAbilitato(true);
        s.setAlbergo(a);
        s.setCosto(240);
        s.setGiornoInizio(new Date());
        s.setGiornoFine(new Date(s.getGiornoInizio().getTime() + 3 * 24 * 60 * 60 * 1000));
        s.setNumeroPersone(5);
        pbService.saveSoggiorno(s);
        
        ParametriRicercaPB params = new ParametriRicercaPB();
        params.setTipo(TipoPB.Soggiorno);
        params.setAlbergo(a);
        params.setData(s.getGiornoInizio());
        params.setNumPersone(s.getNumeroPersone());
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(s));
    }
    
    @Test 
    public void testRetrieveSoggiornoByAlbergo(){
        System.out.println("testRetrieveSoggiornoByParams()");
        Albergo a = new Albergo();
        a.setCitta("Paperopoli");
        a.setNome("Pensione deposito");
        a.setUrlFoto(TestUtilities.getRandomImageLink());
        a.setStelle(4);
        edbService.salvaAlbergo(a);
        
        Soggiorno s = new Soggiorno();
        s.setAbilitato(true);
        s.setAlbergo(a);
        s.setCosto(240);
        s.setGiornoInizio(new Date());
        s.setGiornoFine(new Date(s.getGiornoInizio().getTime() + 3 * 24 * 60 * 60 * 1000));
        s.setNumeroPersone(5);
        pbService.saveSoggiorno(s);
        
        ParametriRicercaPB params = new ParametriRicercaPB();
        params.setTipo(TipoPB.Soggiorno);
        params.setAlbergo(a);
        
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(s));
    }
    
    @Test 
    public void testRetrieveSoggiornoByGiornoInizio(){
        System.out.println("testRetrieveSoggiornoByParams()");
        Albergo a = new Albergo();
        a.setCitta("Paperopoli");
        a.setNome("Pensione deposito");
        a.setUrlFoto(TestUtilities.getRandomImageLink());
        a.setStelle(4);
        edbService.salvaAlbergo(a);
        
        Soggiorno s = new Soggiorno();
        s.setAbilitato(true);
        s.setAlbergo(a);
        s.setCosto(240);
        s.setGiornoInizio(new Date());
        s.setGiornoFine(new Date(s.getGiornoInizio().getTime() + 3 * 24 * 60 * 60 * 1000));
        s.setNumeroPersone(5);
        pbService.saveSoggiorno(s);
        
        ParametriRicercaPB params = new ParametriRicercaPB();
        params.setTipo(TipoPB.Soggiorno);
        params.setData(s.getGiornoInizio());
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(s));
    }
    
    @Test @Ignore
    public void testRetrieveSoggiornoByCosto(){
        
    }
     
    @Test
    public void testRetrieveSoggiornoByNumeroPersoneEcitta(){
        System.out.println("testRetrieveSoggiornoByParams()");
        Albergo a = new Albergo();
        a.setCitta("Paperopoli");
        a.setNome("Pensione deposito");
        a.setUrlFoto(TestUtilities.getRandomImageLink());
        a.setStelle(4);
        edbService.salvaAlbergo(a);
        
        Soggiorno s = new Soggiorno();
        s.setAbilitato(true);
        s.setAlbergo(a);
        s.setCosto(240);
        s.setGiornoInizio(new Date());
        s.setGiornoFine(new Date(s.getGiornoInizio().getTime() + 3 * 24 * 60 * 60 * 1000));
        s.setNumeroPersone(5);
        pbService.saveSoggiorno(s);
        
        ParametriRicercaPB params = new ParametriRicercaPB();
        params.setTipo(TipoPB.Soggiorno);
        params.setNumPersone(s.getNumeroPersone());
        params.setCitta(a.getCitta());
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(s));
    }
    
    @Test
    public void testRetrieveSoggiornoByCitta(){
        System.out.println("testRetrieveSoggiornoByParams()");
        Albergo a = new Albergo();
        a.setCitta("Paperopoli");
        a.setNome("Pensione deposito");
        a.setUrlFoto(TestUtilities.getRandomImageLink());
        a.setStelle(4);
        edbService.salvaAlbergo(a);
        
        Soggiorno s = new Soggiorno();
        s.setAbilitato(true);
        s.setAlbergo(a);
        s.setCosto(240);
        s.setGiornoInizio(new Date());
        s.setGiornoFine(new Date(s.getGiornoInizio().getTime() + 3 * 24 * 60 * 60 * 1000));
        s.setNumeroPersone(5);
        pbService.saveSoggiorno(s);
        
        ParametriRicercaPB params = new ParametriRicercaPB();
        params.setTipo(TipoPB.Soggiorno);
        params.setCitta(a.getCitta());
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(s));
    }
    
    @Test @Ignore
    public void testRetrieveVisitaByMuseo(){
        
    }
    
    @Test
    public void testRetrieveVisitaByParams(){
        System.out.println("testRetrieveVisitaByParams()");
        Museo m = new Museo();
        m.setNome("Louvre");
        m.setCitta("Parigi");
        m.setDescrizione("Museo bello. Molto bello.");
        m.setUrlFoto(TestUtilities.getRandomImageLink());
        edbService.salvaMuseo(m);
        
        Visita v = new Visita();
        v.setAbilitato(true);
        v.setMuseo(m);
        v.setDataOra(new Date());
        v.setNumeroPersone(4);
        v.setCosto(15);
        pbService.saveVisita(v);
        
        ParametriRicercaPB params = new ParametriRicercaPB();
        params.setTipo(TipoPB.Visita);
        params.setMuseo(m);
        params.setData(v.getDataOra());
        params.setNumPersone(v.getNumeroPersone());
//        params.setCosto(v.getCosto());
        
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(v));
    }
    
    @Test @Ignore
    public void testRetrieveVisitaByGiorno(){
        
    }
    
    @Test @Ignore
    public void testSalvaVolo(){
        
    }
    
    @Test @Ignore
    public void testSalvaSoggiorno(){
        
    }
    
    @Test @Ignore
    public void testSalvaVisita(){
        
    }
    
    @Test @Ignore
    public void testDisattivaVolo(){
        
    }
    
    @Test @Ignore
    public void testDisattivaSoggiorno(){
        
    }
    
    @Test @Ignore
    public void testDisattivaVisita(){
        
    }
}
