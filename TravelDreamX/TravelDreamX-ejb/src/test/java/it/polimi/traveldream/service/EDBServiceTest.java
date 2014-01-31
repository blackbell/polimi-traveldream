/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.service;

import it.polimi.traveldream.data.TestUtilities;
import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.EDB;
import it.polimi.traveldream.model.Museo;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.TipoEDB;
import static it.polimi.traveldream.service.EJBServiceTestSuite.container;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.naming.NamingException;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
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
        if (container != null) {
            testSuite = true;
        } else {
            EJBServiceTestSuite.setUp();
        }
        service = (EDBServiceLocal) container.getContext().lookup(jdniName);
        rnd = new Random(new Date().getTime());
    }

    @AfterClass
    public static void tearDownClass() {
        if (!testSuite) {
            EJBServiceTestSuite.tearDown();
        }
    }

    @Test
    public void testTrovaAlbergoByNome() {
        Albergo a = new Albergo();
        a.setNome("Hotel Millefiori");
        a.setCitta("Londra");
        a.setStelle(3);
        a.setUrlFoto(TestUtilities.getRandomImageLink());
        System.out.println("testTrovaAlbergoByNome() -> a:" + ((Albergo) a).getNome() + "[" + a + "]");

        a = service.salvaAlbergo(a);
        System.out.println("testTrovaAlbergoByNome() -> a:" + ((Albergo) a).getNome() + "[" + a + "]");

        ParametriRicercaEDB p = new ParametriRicercaEDB();
        p.setTipo(TipoEDB.Albergo);
        p.setNome(a.getNome());

        List<EDB> list = service.trovaEntità(p);
        for (EDB l : list) {
            System.out.println("testTrovaAlbergoByNome() -> l:" + ((Albergo) l).getNome() + "[" + l + "]");
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
        a.setUrlFoto(TestUtilities.getRandomImageLink());
        System.out.println("testTrovaAlbergoByCittà() -> a:" + ((Albergo) a).getNome() + "[" + a + "]");

        a = service.salvaAlbergo(a);
        System.out.println("testTrovaAlbergoByCittà() -> a:" + ((Albergo) a).getNome() + "[" + a + "]");

        ParametriRicercaEDB p = new ParametriRicercaEDB();
        p.setTipo(TipoEDB.Albergo);
        p.setCitta(a.getCitta());

        List<EDB> list = service.trovaEntità(p);
        for (EDB l : list) {
            System.out.println("testTrovaAlbergoByCittà() -> l:" + ((Albergo) l).getNome() + "[" + l + "]");
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
        a.setUrlFoto(TestUtilities.getRandomImageLink());
        System.out.println("testTrovaAlbergoByStelle() -> a:" + ((Albergo) a).getNome() + "[" + a + "]");

        a = service.salvaAlbergo(a);
        System.out.println("testTrovaAlbergoByStelle() -> a:" + ((Albergo) a).getNome() + "[" + a + "]");

        ParametriRicercaEDB p = new ParametriRicercaEDB();
        p.setTipo(TipoEDB.Albergo);
        p.setStelle(a.getStelle());

        List<EDB> list = service.trovaEntità(p);
        for (EDB l : list) {
            System.out.println("testTrovaAlbergoByStelle() -> l:" + ((Albergo) l).getNome() + "[" + l + "]");
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
        for (EDB l : list) {
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
        p.setCittaPartenza(r.getCittaPartenza());

        List<EDB> list = service.trovaEntità(p);
        for (EDB l : list) {
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
        p.setCittaArrivo(r.getCittaArrivo());

        List<EDB> list = service.trovaEntità(p);
        for (EDB l : list) {
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
        for (EDB l : list) {
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
        for (EDB l : list) {
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
        for (EDB l : list) {
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
        for (EDB l : list) {
            assertTrue(l instanceof Rotta);
        }
        assertTrue(list.size() > 0);
        assertTrue(list.contains(r));
    }

    @Test
    public void testSalvaRotta() {
        Rotta r = new Rotta();
        r.setAeroportoPartenza("San Giorgio International Airport");
        r.setAeroportoArrivo("Malpensa");
        r.setNazionePartenza("Italia");
        r.setNazioneArrivo("Italia");
        r.setCompagniaAerea("PoliMI airways");
        r.setCittaPartenza("Pistoia");
        r.setCittaArrivo("Milano");
        r = service.salvaRotta(r);
        assertNotNull(r);
    }

    @Test
    public void testSalvaAlbergo() {
        Albergo a = new Albergo();
        a.setNome("Hotel Torre Eiffel");
        a.setCitta("Parigi");
        a.setStelle(4);
        a.setUrlFoto(TestUtilities.getRandomImageLink());
        a = service.salvaAlbergo(a);
        assertNotNull(a);
    }

    @Test
    public void testSalvaMuseo() {
        Museo m = new Museo();
        m.setNome("testSalvaMuseo");
        m.setCitta("Città del museo");
        m.setDescrizione("Descrizione del museo.");
        m.setUrlFoto(TestUtilities.getRandomImageLink());
        m = service.salvaMuseo(m);
        assertNotNull(m);
    }

    @Test
    public void testRetrieveRottaByID() {
        Rotta r = new Rotta();
        r.setAeroportoPartenza("San Giorgio International Airport");
        r.setAeroportoArrivo("testRetrieveRottaByID");
        r.setNazionePartenza("Italia");
        r.setNazioneArrivo("Italia");
        r.setCompagniaAerea("PoliMI airways");
        r.setCittaPartenza("Pistoia");
        r.setCittaArrivo("Milano");
        r = service.salvaRotta(r);

        Rotta r2 = service.retrieveRottaByID(r.getIdRotta());
        assertEquals(r.getAeroportoPartenza(), r2.getAeroportoPartenza());
        assertEquals(r.getAeroportoArrivo(), r2.getAeroportoArrivo());
        assertEquals(r.getCittaPartenza(), r2.getCittaPartenza());
        assertEquals(r.getCittaArrivo(), r2.getCittaArrivo());
        assertEquals(r.getNazionePartenza(), r2.getNazionePartenza());
        assertEquals(r.getNazioneArrivo(), r2.getNazioneArrivo());
        assertEquals(r.getCompagniaAerea(), r2.getCompagniaAerea());
    }

    @Test
    public void testRetrieveAlbergoByID() {
        Albergo a = new Albergo();
        a.setNome("testRetrieveAlbergoByID");
        a.setCitta("Parigi");
        a.setStelle(4);
        a.setUrlFoto(TestUtilities.getRandomImageLink());
        service.salvaAlbergo(a);
        Albergo a2 = service.retrieveAlbergoByID(a.getIdAlbergo());
        assertNotNull(a2);
        assertEquals(a.getNome(), a2.getNome());
        assertEquals(a.getCitta(), a2.getCitta());
        assertEquals(a.getStelle(), a2.getStelle());
        assertEquals(a.getUrlFoto(), a2.getUrlFoto());
    }

    @Test
    public void testRetrieveMuseoByID() {
        Museo m = new Museo();
        m.setNome("testRetrieveMuseoByID");
        m.setCitta("Città del museo");
        m.setDescrizione("Descrizione del museo.");
        m.setUrlFoto(TestUtilities.getRandomImageLink());
        Museo m2 = service.salvaMuseo(m);
        assertNotNull(m2);
        assertEquals(m.getNome(), m2.getNome());
        assertEquals(m.getCitta(), m2.getCitta());
        assertEquals(m.getDescrizione(), m2.getDescrizione());
        assertEquals(m.getUrlFoto(), m2.getUrlFoto());
    }

    @Test
    public void testTrovaMuseoByNome() {
        Museo m = new Museo();
        m.setNome("testTrovaMuseoByNome");
        m.setCitta("Città del museo");
        m.setDescrizione("Descrizione del museo.");
        m.setUrlFoto(TestUtilities.getRandomImageLink());
        Museo m2 = service.salvaMuseo(m);

        ParametriRicercaEDB p = new ParametriRicercaEDB();
        p.setTipo(TipoEDB.Museo);
        p.setNome(m.getNome());

        List<EDB> list = service.trovaEntità(p);
        for (EDB l : list) {
            System.out.println("testTrovaMuseoByNome() -> l:" + ((Museo) l).getNome() + "[" + l + "]");
            assertTrue(l instanceof Museo);
        }
        System.out.println("testTrovaMuseoByNome() -> list.size:" + list.size() + "[" + list + "]");
        assertTrue(list.size() > 0);
        assertTrue(list.contains(m));
    }

    @Test
    public void testTrovaMuseoByCittà() {
        Museo m = new Museo();
        m.setNome("testTrovaMuseoByCittà");
        m.setCitta("Città del museo");
        m.setDescrizione("Descrizione del museo.");
        m.setUrlFoto(TestUtilities.getRandomImageLink());
        Museo m2 = service.salvaMuseo(m);

        ParametriRicercaEDB p = new ParametriRicercaEDB();
        p.setTipo(TipoEDB.Museo);
        p.setCitta(m.getCitta());

        List<EDB> list = service.trovaEntità(p);
        for (EDB l : list) {
            System.out.println("testTrovaMuseoByCittà() -> l:" + ((Museo) l).getNome() + "[" + l + "]");
            assertTrue(l instanceof Museo);
        }
        System.out.println("testTrovaMuseoByCittà() -> list.size:" + list.size() + "[" + list + "]");
        assertTrue(list.size() > 0);
        assertTrue(list.contains(m));
    }
}
