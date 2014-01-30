/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.data.TestUtilities;
import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Soggiorno;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
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
    
//    @Test @Ignore
//    public void testAddVoloToPV(){
//        Rotta r = new Rotta();
//        r.setAeroportoPartenza("Aeroporto di Partenza");
//        r.setAeroportoArrivo("Aeroporto di Arrivo");
//        r.setCittaPartenza("Citta di Partenza");
//        r.setCittaArrivo("Citta di Arrivo");
//        r.setNazionePartenza("Nazione di Partenza");
//        r.setNazioneArrivo("Nazione di Arrivo");
//        r.setCompagniaAerea("Aereo in compagnia");
//        r = edbService.salvaRotta(r);
//        
//        Volo v = new Volo();
//        v.setCosto(100f);
//        v.setRotta(r);
//        v.setDataOra(new Date());
//        v.setNumPasseggeri(3);
//        v.setAbilitato(true);
//        v = pbService.saveVolo(v);
//        v = pbService.getVoloByID(v.getIdVoce());
//        
//        Utente u = new Utente(rnd.nextInt() + "@ServiceTest.polimi.it","polimi");
//        u.setLivello(Utente.LIVELLO_IMPIEGATO);
//        u = utenteService.registrazione(u);
//        
//        Pacchetto p = new Pacchetto();
//        p.setProprietario(u);
//        p.setDataOraCreazione(new Date());
//        p.setTipo(Pacchetto.PREDEFINITO);
//        p.setVoci(new ArrayList<Voce>());
//        pvService.addPBtoPV(v, p);
//    }
    
    @Test
    public void testSalvaPV(){
        Rotta r = new Rotta();
        r.setAeroportoPartenza("testSalvaPV");
        r.setAeroportoArrivo("Aeroporto di Arrivo");
        r.setCittaPartenza("Citta di Partenza");
        r.setCittaArrivo("Citta di Arrivo");
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
        p.setNumeroPersone(3);
        p.setVoci(new ArrayList<Voce>());
        p.getVoci().add(v);
        Pacchetto p2 = pvService.salvaPV(p);
        assertEquals(p2,p);
    }
    
    @Test
    public void testSalvaPVvuoto(){
        Utente u = new Utente(rnd.nextInt() + "@ServiceTest.polimi.it","polimi");
        u = utenteService.registrazione(u);
        
        Pacchetto p = new Pacchetto();
        p.setAbilitato(true);
        p.setProprietario(u);
        p.setDataOraCreazione(new Date());
        p.setTipo(Pacchetto.PREDEFINITO);
        p.setNumeroPersone(2);
        p.setVoci(new ArrayList<Voce>());
        Pacchetto p2 = pvService.salvaPV(p);
        Assert.assertNull(p2);
    }
    
    @Test
    public void testDisattivaPV(){
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Aeroporto di Partenza");
        r.setAeroportoArrivo("testDisattivaPV");
        r.setCittaPartenza("Citta di Partenza");
        r.setCittaArrivo("Citta di Arrivo");
        r.setNazionePartenza("Nazione di Partenza");
        r.setNazioneArrivo("Nazione di Arrivo");
        r.setCompagniaAerea("Aereo in compagnia" + rnd.nextInt(10000));
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
        p.setProprietario(u);
        p.setDataOraCreazione(new Date());
        p.setTipo(Pacchetto.PREDEFINITO);
        p.setVoci(new ArrayList<Voce>());
        p.setNumeroPersone(6);
        p.getVoci().add(v);
        p.setAbilitato(true);
        Pacchetto p2 = pvService.salvaPV(p);
        boolean x = pvService.disattivaPV(p.getIdPacchetto());
        assertTrue(!x);
    }
    
    @Test 
    public void trovaPVbyID(){
        Rotta r = new Rotta();
        r.setAeroportoPartenza("trovaPVbyID");
        r.setAeroportoArrivo("Aeroporto di Arrivo");
        r.setCittaPartenza("Citta di Partenza");
        r.setCittaArrivo("Citta di Arrivo");
        r.setNazionePartenza("Nazione di Partenza");
        r.setNazioneArrivo("Nazione di Arrivo");
        r.setCompagniaAerea("Aereo in compagnia");
        r = edbService.salvaRotta(r);
        System.out.println("PVServiceTest.trovaPVbyParams -> r: " + r);
        
        Volo v = new Volo();
        v.setCosto(100f);
        v.setRotta(r);
        v.setDataOra(new Date());
//        v.setNumPasseggeri(3);
        v.setAbilitato(true);
        v = pbService.saveVolo(v);
        v = pbService.getVoloByID(v.getIdVoce());
        System.out.println("PVServiceTest.trovaPVbyParams -> v: " + v);

        
        Albergo a = new Albergo();
        a.setCitta("Padova");
        a.setNome("Albergo a Padova");
        a.setStelle(5);
        a.setUrlFoto(TestUtilities.getRandomImageLink());
        edbService.salvaAlbergo(a);
        
        Soggiorno s = new Soggiorno();
        s.setAbilitato(true);
        s.setAlbergo(a);
        s.setCosto(20f);
        s.setGiornoInizio(new Date());
        s.setGiornoFine(new Date(s.getGiornoInizio().getTime() + 3 * 24 * 60 * 60 * 1000));
        s.setNumeroPersone(3);
        s = pbService.saveSoggiorno(s);
        s = pbService.getSoggiornoByID(s.getIdVoce());
        
        Utente u = new Utente(rnd.nextInt() + "@ServiceTest.polimi.it","polimi");
        u = utenteService.registrazione(u);
        
        Pacchetto p = new Pacchetto();
        p.setNome("trovaPVbyID");
        p.setAbilitato(true);
        p.setProprietario(u);
        p.setDataOraCreazione(new Date());
        p.setNumeroPersone(3);
        p.setTipo(Pacchetto.PREDEFINITO);
        p.setVoci(new ArrayList<Voce>());
        p.getVoci().add(v);
        p.getVoci().add(s);
        pvService.salvaPV(p);
        
        ParametriRicercaPV params = new ParametriRicercaPV();
        params.setIdPacchetto(p.getIdPacchetto());
        
        List<Pacchetto> pvs = pvService.trovaPV(params);
        assertTrue("PV non nell'elenco dei pv recuperati", pvs.contains(p));
    }
    
    @Test 
    public void trovaPVbyIDsbagliato(){
        Rotta r = new Rotta();
        r.setAeroportoPartenza("trovaPVbyID");
        r.setAeroportoArrivo("Aeroporto di Arrivo");
        r.setCittaPartenza("Citta di Partenza");
        r.setCittaArrivo("Citta di Arrivo");
        r.setNazionePartenza("Nazione di Partenza");
        r.setNazioneArrivo("Nazione di Arrivo");
        r.setCompagniaAerea("Aereo in compagnia");
        r = edbService.salvaRotta(r);
        System.out.println("PVServiceTest.trovaPVbyParams -> r: " + r);
        
        Volo v = new Volo();
        v.setCosto(100f);
        v.setRotta(r);
        v.setDataOra(new Date());
//        v.setNumPasseggeri(3);
        v.setAbilitato(true);
        v = (Volo)pbService.salvaPB(v);
        v = pbService.getVoloByID(v.getIdVoce());
        System.out.println("PVServiceTest.trovaPVbyParams -> v: " + v);

        
        Albergo a = new Albergo();
        a.setCitta("Padova");
        a.setNome("Albergo a Padova");
        a.setStelle(5);
        a.setUrlFoto(TestUtilities.getRandomImageLink());
        edbService.salvaAlbergo(a);
        
        Soggiorno s = new Soggiorno();
        s.setAbilitato(true);
        s.setAlbergo(a);
        s.setCosto(20f);
        s.setGiornoInizio(new Date());
        s.setGiornoFine(new Date(s.getGiornoInizio().getTime() + 3 * 24 * 60 * 60 * 1000));
        s.setNumeroPersone(3);
        s = (Soggiorno)pbService.salvaPB(s);
        s = pbService.getSoggiornoByID(s.getIdVoce());
        
        Utente u = new Utente(rnd.nextInt() + "@ServiceTest.polimi.it","polimi");
        u = utenteService.registrazione(u);
        
        Pacchetto p = new Pacchetto();
        p.setNome("trovaPVbyID");
        p.setAbilitato(true);
        p.setProprietario(u);
        p.setDataOraCreazione(new Date());
        p.setTipo(Pacchetto.PREDEFINITO);
        p.setNumeroPersone(3);
        p.setVoci(new ArrayList<Voce>());
        p.getVoci().add(v);
        p.getVoci().add(s);
        pvService.salvaPV(p);
        
        ParametriRicercaPV params = new ParametriRicercaPV();
        params.setIdPacchetto(Integer.MIN_VALUE);
        
        List<Pacchetto> pvs = pvService.trovaPV(params);
        Assert.assertNull("PV trovato nell'elenco dei recuperati (non dovrebbe esserci)", pvs);
    }
    
    @Test 
    public void trovaPVbyParams(){
        Rotta r = new Rotta();
        r.setAeroportoPartenza("trovaPBbyParams");
        r.setAeroportoArrivo("Aeroporto di Arrivo");
        r.setCittaPartenza("Citta di Partenza");
        r.setCittaArrivo("Citta di Arrivo");
        r.setNazionePartenza("Nazione di Partenza");
        r.setNazioneArrivo("Nazione di Arrivo");
        r.setCompagniaAerea("Aereo in compagnia");
        r = edbService.salvaRotta(r);
        System.out.println("PVServiceTest.trovaPVbyParams -> r: " + r);
        
        Volo v = new Volo();
        v.setCosto(100f);
        v.setRotta(r);
        v.setDataOra(new Date());
//        v.setNumPasseggeri(3);
        v.setAbilitato(true);
        v = pbService.saveVolo(v);
        v = pbService.getVoloByID(v.getIdVoce());
        System.out.println("PVServiceTest.trovaPVbyParams -> v: " + v);

        
        Albergo a = new Albergo();
        a.setCitta("Padova");
        a.setNome("Albergo a Padova");
        a.setStelle(5);
        a.setUrlFoto(TestUtilities.getRandomImageLink());
        edbService.salvaAlbergo(a);
        
        Soggiorno s = new Soggiorno();
        s.setAbilitato(true);
        s.setAlbergo(a);
        s.setCosto(20f);
        s.setGiornoInizio(new Date());
        s.setGiornoFine(new Date(s.getGiornoInizio().getTime() + 3 * 24 * 60 * 60 * 1000));
        s.setNumeroPersone(3);
        s = pbService.saveSoggiorno(s);
        s = pbService.getSoggiornoByID(s.getIdVoce());
        
        Utente u = new Utente(rnd.nextInt() + "@ServiceTest.polimi.it","polimi");
        u = utenteService.registrazione(u);
        
        Pacchetto p = new Pacchetto();
        p.setNome("trovaPVbyParams");
        p.setAbilitato(true);
        p.setProprietario(u);
        p.setDataOraCreazione(new Date());
        p.setTipo(Pacchetto.PREDEFINITO);
        p.setNumeroPersone(3);
        p.setVoci(new ArrayList<Voce>());
        p.getVoci().add(v);
        p.getVoci().add(s);
        pvService.salvaPV(p);
        
        ParametriRicercaPV params = new ParametriRicercaPV();
        params.setNome(p.getNome());
        params.setCittaAlbergo(a.getCitta());
        params.setNazionePartenza(r.getNazionePartenza());
        params.setNazioneArrivo(r.getNazioneArrivo());
        params.setGiornoInizio(s.getGiornoInizio());
        params.setGiornoFine(s.getGiornoFine());
        
        List<Pacchetto> pvs = pvService.trovaPV(params);
        assertTrue("PV non nell'elenco dei pv recuperati", pvs.contains(p));
    }
    
    @Test
    public void trovaPVbyGiornoInizioGiornoFine(){
        Rotta r = new Rotta();
        r.setAeroportoPartenza("trovaPBbyParams");
        r.setAeroportoArrivo("Aeroporto di Arrivo");
        r.setCittaPartenza("Citta di Partenza");
        r.setCittaArrivo("Citta di Arrivo");
        r.setNazionePartenza("Nazione di Partenza");
        r.setNazioneArrivo("Nazione di Arrivo");
        r.setCompagniaAerea("Aereo in compagnia");
        r = edbService.salvaRotta(r);
        System.out.println("PVServiceTest.trovaPVbyParams -> r: " + r);
        
        Volo v = new Volo();
        v.setCosto(100f);
        v.setRotta(r);
        v.setDataOra(new Date());
//        v.setNumPasseggeri(3);
        v.setAbilitato(true);
        v = pbService.saveVolo(v);
        v = pbService.getVoloByID(v.getIdVoce());
        System.out.println("PVServiceTest.trovaPVbyParams -> v: " + v);

        
        Albergo a = new Albergo();
        a.setCitta("Padova");
        a.setNome("Albergo a Padova");
        a.setStelle(5);
        a.setUrlFoto(TestUtilities.getRandomImageLink());
        edbService.salvaAlbergo(a);
        
        Soggiorno s = new Soggiorno();
        s.setAbilitato(true);
        s.setAlbergo(a);
        s.setCosto(20f);
        s.setGiornoInizio(new Date());
        s.setGiornoFine(new Date(s.getGiornoInizio().getTime() + 3 * 24 * 60 * 60 * 1000));
        s.setNumeroPersone(3);
        s = pbService.saveSoggiorno(s);
        s = pbService.getSoggiornoByID(s.getIdVoce());
        
        Utente u = new Utente(rnd.nextInt() + "@ServiceTest.polimi.it","polimi");
        u = utenteService.registrazione(u);
        
        Pacchetto p = new Pacchetto();
        p.setNome("trovaPVbyGiornoInizioGiornoFine");
        p.setAbilitato(true);
        p.setProprietario(u);
        p.setDataOraCreazione(new Date());
        p.setNumeroPersone(3);
        p.setTipo(Pacchetto.PREDEFINITO);
        p.setVoci(new ArrayList<Voce>());
        p.getVoci().add(v);
        p.getVoci().add(s);
        pvService.salvaPV(p);
        
        ParametriRicercaPV params = new ParametriRicercaPV();
        params.setGiornoInizio(s.getGiornoInizio());
        params.setGiornoFine(s.getGiornoFine());
        System.out.println("********************************************");
        System.out.println("PVService.trovaPVbyParams -> p.giornoInizio: " + p.getGiornoInizio());
        List<Pacchetto> pvs = pvService.trovaPV(params);
        assertTrue("PV non nell'elenco dei pv recuperati", pvs.contains(p));
    }
    
    @Test 
    public void trovaPVbyNome(){
        Rotta r = new Rotta();
        r.setAeroportoPartenza("trovaPBbyParams");
        r.setAeroportoArrivo("Aeroporto di Arrivo");
        r.setCittaPartenza("Citta di Partenza");
        r.setCittaArrivo("Citta di Arrivo");
        r.setNazionePartenza("Nazione di Partenza");
        r.setNazioneArrivo("Nazione di Arrivo");
        r.setCompagniaAerea("Aereo in compagnia");
        r = edbService.salvaRotta(r);
        System.out.println("PVServiceTest.trovaPVbyParams -> r: " + r);
        
        Volo v = new Volo();
        v.setCosto(100f);
        v.setRotta(r);
        v.setDataOra(new Date());
//        v.setNumPasseggeri(3);
        v.setAbilitato(true);
        v = pbService.saveVolo(v);
        v = pbService.getVoloByID(v.getIdVoce());
        System.out.println("PVServiceTest.trovaPVbyParams -> v: " + v);

        
        Albergo a = new Albergo();
        a.setCitta("Padova");
        a.setNome("Albergo a Padova");
        a.setStelle(5);
        a.setUrlFoto(TestUtilities.getRandomImageLink());
        edbService.salvaAlbergo(a);
        
        Soggiorno s = new Soggiorno();
        s.setAbilitato(true);
        s.setAlbergo(a);
        s.setCosto(20f);
        s.setGiornoInizio(new Date());
        s.setGiornoFine(new Date(s.getGiornoInizio().getTime() + 3 * 24 * 60 * 60 * 1000));
        s.setNumeroPersone(3);
        s = pbService.saveSoggiorno(s);
        s = pbService.getSoggiornoByID(s.getIdVoce());
        
        Utente u = new Utente(rnd.nextInt() + "@ServiceTest.polimi.it","polimi");
        u = utenteService.registrazione(u);
        
        Pacchetto p = new Pacchetto();
        p.setNome("trovaPVbyNome");
        p.setAbilitato(true);
        p.setProprietario(u);
        p.setDataOraCreazione(new Date());
        p.setNumeroPersone(3);
        p.setTipo(Pacchetto.PREDEFINITO);
        p.setVoci(new ArrayList<Voce>());
        p.getVoci().add(v);
        p.getVoci().add(s);
        pvService.salvaPV(p);
        
        ParametriRicercaPV params = new ParametriRicercaPV();
        params.setNome(p.getNome());
        
        System.out.println("********************************************");
        System.out.println("PVService.trovaPVbyParams -> p.giornoInizio: " + p.getGiornoInizio());
        List<Pacchetto> pvs = pvService.trovaPV(params);
        assertTrue("PV non nell'elenco dei pv recuperati", pvs.contains(p));
    }
    
//    @Test @Ignore
//    public void testAddVoloESoggiornoToPV(){
//        
//    }
//    
//    @Test @Ignore
//    public void testAddVoloESoggiornoEVisitaToPV(){
//        
//    }
//    
//    @Test @Ignore
//    public void testAddVoloEVisitaToPV(){
//        
//    }
//    
//    @Test @Ignore
//    public void testAddSoggiornoToPV(){
//        
//    }
//    
//    @Test @Ignore
//    public void testAddVisitaToPV(){
//        
//    }
//    
//    @Test @Ignore
//    public void testAddVoloDisabilitatoESoggiornoToPV(){
//        
//    }
//    
//    @Test @Ignore
//    public void testAddVoloESoggiornoDisabilitatoToPV(){
//        
//    }
//    
//    @Test @Ignore
//    public void testAddVoloESoggiornoEVisitaDiabilitataToPV(){
//        
//    }
//    
//    @Test @Ignore
//    public void testAddVoloEVisitaDisabilitataToPV(){
//        
//    }
//    
//    @Test @Ignore
//    public void testAddVoloDisasbilitatoEVisitaToPV(){
//        
//    }
//    
//    @Test @Ignore
//    public void testAddSoggiornoDisabilitatoToPV(){
//        
//    }
//    
//    @Test @Ignore
//    public void testAddVisitaDisabilitataToPV(){
//        
//    }
}
