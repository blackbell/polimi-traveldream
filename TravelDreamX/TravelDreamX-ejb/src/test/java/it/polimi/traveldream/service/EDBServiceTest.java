/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.EDB;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.TipoEDB;
import static it.polimi.traveldream.service.EJBServiceTestSuite.container;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.naming.NamingException;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Dario
 */
public class EDBServiceTest {
    
    private static final String jdniName = "java:global/classes/EDBService";
    static EDBServiceLocal service = null;
    static Random rnd;
    private static boolean testSuite = false;
    
    @BeforeClass
    public static void setUpClass() throws NamingException {
        if (container != null) testSuite = true;
        else EJBServiceTestSuite.setUp();
        service = (EDBServiceLocal)container.getContext().lookup(jdniName);
        rnd = new Random(new Date().getTime());
    }
    
    @AfterClass
    public static void tearDownClass() {
        if (!testSuite) EJBServiceTestSuite.tearDown();
    }

    @Test
    public void testTrovaAlbergoByNome() {
        Albergo a = new Albergo();
        a.setNome("Hotel Millefiori");
        a.setCitta("Londra");
        a.setStelle(3);
        a.setUrlFoto("/");
        System.out.println("testTrovaAlbergoByNome() -> a:" + ((Albergo)a).getNome() + "[" + a + "]");

        a = service.salvaAlbergo(a);
        System.out.println("testTrovaAlbergoByNome() -> a:" + ((Albergo)a).getNome() + "[" + a + "]");
        
        ParametriRicercaEDB p = new ParametriRicercaEDB();
        p.setTipo(TipoEDB.Albergo);
        p.setNome(a.getNome());
        
        List<EDB> list = service.trovaEntità(p);
        for(EDB l : list){
            System.out.println("testTrovaAlbergoByNome() -> l:" + ((Albergo)l).getNome() + "[" + l + "]");
            assertTrue(l instanceof Albergo);
        }
        System.out.println("testTrovaAlbergoByNome() -> list.size:" + list.size() + "[" + list + "]");        
        assertTrue(list.size() > 0);
        assertTrue(list.contains(a));
    }
    
    @Test
    public void testTrovaAlbergoByCittà() {
        Albergo a = new Albergo();
        a.setNome("Hotel Calandra");
        a.setCitta("Torino");
        a.setStelle(3);
        a.setUrlFoto("/");
        System.out.println("testTrovaAlbergoByCittà() -> a:" + ((Albergo)a).getNome() + "[" + a + "]");

        a = service.salvaAlbergo(a);
        System.out.println("testTrovaAlbergoByCittà() -> a:" + ((Albergo)a).getNome() + "[" + a + "]");
        
        ParametriRicercaEDB p = new ParametriRicercaEDB();
        p.setTipo(TipoEDB.Albergo);
        p.setCitta(a.getCitta());
        
        List<EDB> list = service.trovaEntità(p);
        for(EDB l : list){
            System.out.println("testTrovaAlbergoByCittà() -> l:" + ((Albergo)l).getNome() + "[" + l + "]");
            assertTrue(l instanceof Albergo);
        }
        System.out.println("testTrovaAlbergoByCittà() -> list.size:" + list.size() + "[" + list + "]");        
        assertTrue(list.size() > 0);
        assertTrue(list.contains(a));
    }
    
    @Test
    public void testTrovaAlbergoByStelle() {
        Albergo a = new Albergo();
        a.setNome("Hotel Torre Eiffel");
        a.setCitta("Parigi");
        a.setStelle(4);
        a.setUrlFoto("/");
        System.out.println("testTrovaAlbergoByStelle() -> a:" + ((Albergo)a).getNome() + "[" + a + "]");

        a = service.salvaAlbergo(a);
        System.out.println("testTrovaAlbergoByStelle() -> a:" + ((Albergo)a).getNome() + "[" + a + "]");
        
        ParametriRicercaEDB p = new ParametriRicercaEDB();
        p.setTipo(TipoEDB.Albergo);
        p.setStelle(a.getStelle());
        
        List<EDB> list = service.trovaEntità(p);
        for(EDB l : list){
            System.out.println("testTrovaAlbergoByStelle() -> l:" + ((Albergo)l).getNome() + "[" + l + "]");
            assertTrue(l instanceof Albergo);
        }
        System.out.println("testTrovaAlbergoByStelle() -> list.size:" + list.size() + "[" + list + "]");        
        assertTrue(list.size() > 0);
        assertTrue(list.contains(a));
    }
    
    @Test
    public void testTrovaRottaByCompagniaAerea() {
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Prato Chinese Airport");
        r.setAeroportoArrivo("Pechino International Airport");
        r.setCittaPartenza("Prato");
        r.setCittaArrivo("Pechino");
        r.setNazionePartenza("Italia");
        r.setNazioneArrivo("China");
        r.setCompagniaAerea("Volapiano");
        r = service.salvaRotta(r);
       
        ParametriRicercaEDB p = new ParametriRicercaEDB();
        p.setTipo(TipoEDB.Rotta);
        p.setCompagniaAerea(r.getCompagniaAerea());
        
        List<EDB> list = service.trovaEntità(p);
        for(EDB l : list){
            assertTrue(l instanceof Rotta);
        }
        assertTrue(list.size() > 0);
        assertTrue(list.contains(r));
    }
    
    @Test
    public void testTrovaRottaByCittàPartenza() {
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Prato Chinese Airport");
        r.setAeroportoArrivo("Pechino International Airport");
        r.setCittaPartenza("Prato");
        r.setCittaArrivo("Pechino");
        r.setNazionePartenza("Italia");
        r.setNazioneArrivo("China");
        r.setCompagniaAerea("Volapiano");
        r = service.salvaRotta(r);
        
        ParametriRicercaEDB p = new ParametriRicercaEDB();
        p.setTipo(TipoEDB.Rotta);
        p.setCittàPartenza(r.getCittaPartenza());
        
        List<EDB> list = service.trovaEntità(p);
        for(EDB l : list){
            assertTrue(l instanceof Rotta);
        }
        assertTrue(list.size() > 0);
        assertTrue(list.contains(r));
    }
    
    @Test
    public void testTrovaRottaByCittàArrivo() {
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Prato Chinese Airport");
        r.setAeroportoArrivo("Pechino International Airport");
        r.setCittaPartenza("Prato");
        r.setCittaArrivo("Pechino");
        r.setNazionePartenza("Italia");
        r.setNazioneArrivo("China");
        r.setCompagniaAerea("Volapiano");
        r = service.salvaRotta(r);
        
        ParametriRicercaEDB p = new ParametriRicercaEDB();
        p.setTipo(TipoEDB.Rotta);
        p.setCittàArrivo(r.getCittaArrivo());
        
        List<EDB> list = service.trovaEntità(p);
        for(EDB l : list){
            assertTrue(l instanceof Rotta);
        }
        assertTrue(list.size() > 0);
        assertTrue(list.contains(r));
    }
    
    @Test
    public void testTrovaRottaByNazionePartenza() {
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Prato Chinese Airport");
        r.setAeroportoArrivo("Pechino International Airport");
        r.setCittaPartenza("Prato");
        r.setCittaArrivo("Pechino");
        r.setNazionePartenza("Italia");
        r.setNazioneArrivo("China");
        r.setCompagniaAerea("Volapiano");
        r = service.salvaRotta(r);
        
        ParametriRicercaEDB p = new ParametriRicercaEDB();
        p.setTipo(TipoEDB.Rotta);
        p.setNazionePartenza(r.getNazionePartenza());
        
        List<EDB> list = service.trovaEntità(p);
        for(EDB l : list){
            assertTrue(l instanceof Rotta);
        }
        assertTrue(list.size() > 0);
        assertTrue(list.contains(r));
    }
    
    @Test
    public void testTrovaRottaByNazioneArrivo() {
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Prato Chinese Airport");
        r.setAeroportoArrivo("Pechino International Airport");
        r.setCittaPartenza("Prato");
        r.setCittaArrivo("Pechino");
        r.setNazionePartenza("Italia");
        r.setNazioneArrivo("China");
        r.setCompagniaAerea("Volapiano");
        r = service.salvaRotta(r);
        
        ParametriRicercaEDB p = new ParametriRicercaEDB();
        p.setTipo(TipoEDB.Rotta);
        p.setNazioneArrivo(r.getNazioneArrivo());
        
        List<EDB> list = service.trovaEntità(p);
        for(EDB l : list){
            assertTrue(l instanceof Rotta);
        }
        assertTrue(list.size() > 0);
        assertTrue(list.contains(r));
    }
    
    @Test
    public void testTrovaRottaByAeroportoPartenza() {
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Prato Chinese Airport");
        r.setAeroportoArrivo("Pechino International Airport");
        r.setCittaPartenza("Prato");
        r.setCittaArrivo("Pechino");
        r.setNazionePartenza("Italia");
        r.setNazioneArrivo("China");
        r.setCompagniaAerea("Volapiano");
        r = service.salvaRotta(r);
        
        ParametriRicercaEDB p = new ParametriRicercaEDB();
        p.setTipo(TipoEDB.Rotta);
        p.setAeroportoPartenza(r.getAeroportoPartenza());
        
        List<EDB> list = service.trovaEntità(p);
        for(EDB l : list){
            assertTrue(l instanceof Rotta);
        }
        assertTrue(list.size() > 0);
        assertTrue(list.contains(r));
    }
    
    @Test
    public void testTrovaRottaByAeroportoArrivo() {
        Rotta r = new Rotta();
        r.setAeroportoPartenza("Prato Chinese Airport");
        r.setAeroportoArrivo("Pechino International Airport");
        r.setCittaPartenza("Prato");
        r.setCittaArrivo("Pechino");
        r.setNazionePartenza("Italia");
        r.setNazioneArrivo("China");
        r.setCompagniaAerea("Volapiano");
        r = service.salvaRotta(r);
        
        ParametriRicercaEDB p = new ParametriRicercaEDB();
        p.setTipo(TipoEDB.Rotta);
        p.setAeroportoArrivo(r.getAeroportoArrivo());
        
        List<EDB> list = service.trovaEntità(p);
        for(EDB l : list){
            assertTrue(l instanceof Rotta);
        }
        assertTrue(list.size() > 0);
        assertTrue(list.contains(r));
    }
    
    @Test @Ignore
    public void testSalvaRotta() {

    }
    
    @Test @Ignore
    public void testSalvaAlbergo() {

    }

    @Test @Ignore
    public void testSalvaMuseo() {

    }

    @Test @Ignore
    public void testRetrieveRottaByID() {
        
    }

    @Test @Ignore
    public void testRetrieveAlbergoByID() {
       
    }

    @Test @Ignore
    public void testRetrieveMuseoByID() {

    }
    
}
