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
import org.junit.Assert;
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
        params.setCittaPartenzaVolo(r.getCittaPartenza());
        params.setCittaArrivoVolo(r.getCittaArrivo());
        params.setDataOraVolo(v.getDataOra());
        params.setDisabilitatiInclusi(true);
        
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
    public void testRetrieveVoloByCittàPartenzaEArrivo(){
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
        v.setAbilitato(true);
        pbService.saveVolo(v);
        
        ParametriRicercaPB params = new ParametriRicercaPB();
        params.setTipo(TipoPB.Volo);
        params.setCittaPartenzaVolo(r.getCittaPartenza());
        params.setCittaArrivoVolo(r.getCittaArrivo());
        
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(v));
        Assert.assertEquals(((Volo)pbs.get(pbs.indexOf(v))).getDataOra().getTime(),v.getDataOra().getTime());
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
        v.setAbilitato(true);
        pbService.saveVolo(v);
        
        ParametriRicercaPB params = new ParametriRicercaPB();
        params.setTipo(TipoPB.Volo);
        params.setDataOraVolo(v.getDataOra());
        
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
        params.setNomeAlbergo(a.getNome());
        params.setCittaAlbergo(a.getCitta());
        params.setDataInizioSoggiorno(s.getGiornoInizio());
        params.setDataFineSoggiorno(s.getGiornoFine());
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(s));
    }
    
    @Test  
    public void testRetrieveSoggiornoByNomeAlbergo(){
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
        params.setNomeAlbergo(a.getNome());
        
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(s));
        Assert.assertEquals(((Soggiorno)pbs.get(pbs.indexOf(s))).getGiornoInizio().getTime(),s.getGiornoInizio().getTime());
    }
    
    @Test 
    public void testRetrieveSoggiornoByGiornoInizio(){
        System.out.println("testRetrieveSoggiornoByGiornoInizio()");
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
        s.setGiornoFine(new Date(s.getGiornoInizio().getTime() + 3*24*60*60*1000));
        s.setNumeroPersone(5);
        pbService.saveSoggiorno(s);
        
        ParametriRicercaPB params = new ParametriRicercaPB();
        params.setTipo(TipoPB.Soggiorno);
        params.setDataInizioSoggiorno(s.getGiornoInizio());
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(s));
    }
    
    @Test @Ignore
    public void testRetrieveSoggiornoByCosto(){
        
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
        params.setCittaAlbergo(a.getCitta());
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(s));
    }
    
    @Test
    public void testRetrieveVisitaByNomeMuseo(){
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
        params.setNomeMuseo(m.getNome());
        
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(v));
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
        params.setNomeMuseo(m.getNome());
        params.setCittaMuseo(m.getCitta());
        
        List<Voce> pbs = pbService.trovaPB(params);
        assertTrue(pbs.contains(v));
    }
    
    @Test @Ignore
    public void testRetrieveVisitaByGiorno(){
        
    }
    
    @Test
    public void testSalvaVolo(){
        System.out.println("testSalvaVolo()");
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Breda Air Deposit");
        r.setAeroportoArrivo("Atlandide");
        r.setNazionePartenza("Italia");
        r.setNazioneArrivo("Mondo sottomarino");
        r.setCompagniaAerea("Mille bolle blu");
        r.setCittaPartenza("testSalvaVolo");
        r.setCittaArrivo("Atlantide");
        edbService.salvaRotta(r);
        
        Volo v = new Volo();
        v.setDataOra(new Date());
        v.setRotta(r);
        v.setNumPasseggeri(3);
        v.setCosto(120.7f);
        v.setAbilitato(true);
        v = pbService.saveVolo(v);
        
        Assert.assertNotNull(v);
    }
    
    @Test
    public void testSalvaSoggiorno(){
        System.out.println("testSalvaSoggiorno()");
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
        s = pbService.saveSoggiorno(s);
        
        Assert.assertNotNull(s);
    }
    
    @Test
    public void testSalvaVisita(){
        System.out.println("testSalvaVisita()");
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
        v = pbService.saveVisita(v);
        Assert.assertNotNull(v);
    }
    
    @Test
    public void testDisattivaVolo(){
        System.out.println("testDisattivaVolo()");
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Breda Air Deposit");
        r.setAeroportoArrivo("Atlandide");
        r.setNazionePartenza("Italia");
        r.setNazioneArrivo("Mondo sottomarino");
        r.setCompagniaAerea("Mille bolle blu");
        r.setCittaPartenza("testDisattivaVolo");
        r.setCittaArrivo("Atlantide");
        edbService.salvaRotta(r);
        
        Volo v = new Volo();
        v.setDataOra(new Date());
        v.setRotta(r);
        v.setNumPasseggeri(3);
        v.setCosto(120.7f);
        v.setAbilitato(true);
        v = pbService.saveVolo(v);
        
        pbService.disattivaPB(v.getIdVoce());
        v = pbService.getVoloByID(v.getIdVoce());
        assertTrue(!v.isAbilitato());
    }
    
    @Test
    public void testDisattivaSoggiorno(){
        System.out.println("testDisattivaSoggiorno()");
        Albergo a = new Albergo();
        a.setCitta("testDisattivaSoggiorno");
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
        s = pbService.saveSoggiorno(s);
        
        pbService.disattivaPB(s.getIdVoce());
        s = pbService.getSoggiornoByID(s.getIdVoce());
        assertTrue(!s.isAbilitato());
    }
    
    @Test
    public void testDisattivaVisita(){
        System.out.println("testDisattivaVisita()");
        Museo m = new Museo();
        m.setNome("Louvre");
        m.setCitta("testDisattivaVisita");
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
        
        pbService.disattivaPB(v.getIdVoce());
        v = pbService.getVisitaByID(v.getIdVoce());
        assertTrue(!v.isAbilitato());
    }
}
