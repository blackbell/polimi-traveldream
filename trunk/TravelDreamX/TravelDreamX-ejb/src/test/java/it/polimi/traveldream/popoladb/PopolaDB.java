/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.popoladb;

import static it.polimi.traveldream.data.TestUtilities.getRandomImageLink;
import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.Museo;
import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Soggiorno;
import it.polimi.traveldream.model.TipoPB;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.model.Visita;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.model.Volo;    
import it.polimi.traveldream.service.EDBServiceLocal;
import it.polimi.traveldream.service.EJBServiceTestSuite;
import static it.polimi.traveldream.service.EJBServiceTestSuite.container;
import it.polimi.traveldream.service.PBServiceLocal;
import it.polimi.traveldream.service.PVServiceLocal;
import it.polimi.traveldream.service.ParametriRicercaPB;
import it.polimi.traveldream.service.UtenteServiceLocal;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dario
 */
public class PopolaDB {

    private static final String PVServiceJdniName = "java:global/classes/PVService";
    private static final String utenteServiceJdniName = "java:global/classes/UtenteService";
    private static final String pbServiceJdniName = "java:global/classes/PBService";
    private static final String edbServiceJdniName = "java:global/classes/EDBService";
    private static PVServiceLocal pvService = null;
    private static UtenteServiceLocal utenteService = null;
    private static PBServiceLocal pbService = null;
    private static EDBServiceLocal edbService = null;
    private static boolean testSuite = false;

    @BeforeClass
    public static void setUpClass() throws NamingException {
        if (container != null) {
            testSuite = true;
        } else {
            EJBServiceTestSuite.setUp();
        }
        pvService = (PVServiceLocal) container.getContext().lookup(PVServiceJdniName);
        utenteService = (UtenteServiceLocal) container.getContext().lookup(utenteServiceJdniName);
        pbService = (PBServiceLocal) container.getContext().lookup(pbServiceJdniName);
        edbService = (EDBServiceLocal) container.getContext().lookup(edbServiceJdniName);
        rnd = new Random(new Date().getTime());
    }

    @AfterClass
    public static void tearDownClass() {
        if (!testSuite) {
            EJBServiceTestSuite.tearDown();
        }
    }

    static Random rnd = new Random(new Date().getTime());

    private static final int nroAlberghiDaGenerare = 500;
    private static final int nroSoggiorniDaGenerare = 1000;
    private static final int nroVoliDaGenerare = 1000;
    private static final int nroMuseiDaGenerare = 500;
    private static final int nroVisiteDaGenerare = 1000;
    private static final int nroPacchettiDaGenerare = 1000;
    private final List<Città> cittàNelMondo = new ArrayList<>();
    private final List<Albergo> alberghiGenerati = new ArrayList<>();
    private final List<Museo> museiGenerati = new ArrayList<>();
    private final List<Rotta> rotteLette = new ArrayList<>();
    private final List<Pacchetto> pacchettiGenerati = new ArrayList<>();

    @Test
    public void popolaDB() throws Exception {

        leggiCittàDaFile("città.csv");
        leggiFioriDaFile("fiori.txt");
        leggiColoriDaFile("colori.txt");
        leggiRotteDaFile("unporotte.csv");
        leggiCittàDaRotte();
        leggiScienziatiDaFile("nomi.txt");

        generaAlberghi(nroAlberghiDaGenerare);
        generaSoggiorni(nroSoggiorniDaGenerare, 2, 14, 180, 30, 300);
        salvaRotte();
        generaVoli(nroVoliDaGenerare, 180, 30, 300);
        salvaUtentiImpiegatoEdAdmin();
        generaMusei(nroMuseiDaGenerare);
        generaVisite(nroVisiteDaGenerare, 180, 30, 300);
        generaPacchetti(nroPacchettiDaGenerare);
    }

    private void generaPacchetti(int nroPacchettiDaGenerare) {
        for (int i = 0; i < nroPacchettiDaGenerare; i++) {

            Pacchetto p = new Pacchetto();
            p.setTipo(Pacchetto.PREDEFINITO);
            p.setAbilitato(true);
            p.setNome("Pacchetto bello " + pacchettiGenerati.size());
            p.setDataOraCreazione(new Date());
            p.setProprietario(impiegato);
            p.setVoci(new ArrayList<Voce>());
            Città c = cittàNelMondo.get(rnd.nextInt(cittàNelMondo.size()));
            System.out.println("PopolaDB.generaPacchetti -> scelta città:" + c.getCittà());
            ParametriRicercaPB params = new ParametriRicercaPB();
            params.setCittaAlbergo(c.getCittà());

            params.setCittaArrivoVolo(c.getCittà());
            params.setTipo(TipoPB.Volo);

            List<Voce> v = pbService.trovaPB(params);
            if (v == null) {
                continue;
            }
            Volo andata = (Volo) v.get(rnd.nextInt(v.size()));
            System.out.println("PopolaDB.generaPacchetti -> scelto volo andata:" + andata + " con la compagnia " + andata.getRotta().getCompagniaAerea());
            p.getVoci().add(andata);

            params = new ParametriRicercaPB();
            params.setTipo(TipoPB.Volo);
            params.setCittaPartenzaVolo(c.getCittà());

            v = pbService.trovaPB(params);
            if (v == null) {
                continue;
            }
            Volo ritorno = (Volo) v.get(rnd.nextInt(v.size()));
            System.out.println("PopolaDB.generaPacchetti -> scelto volo ritorno:" + ritorno + " con la compagnia " + ritorno.getRotta().getCompagniaAerea());
            p.getVoci().add(ritorno);

            if (andata.getDataOra().after(ritorno.getDataOra())) {
                Volo x = andata;
                andata = ritorno;
                ritorno = x;
            }

            params = new ParametriRicercaPB();
            params.setTipo(TipoPB.Soggiorno);
            params.setCittaAlbergo(c.getCittà());
            v = pbService.trovaPB(params);
            if (v == null) {
                continue;
            }
            Soggiorno soggiorno = (Soggiorno) v.get(rnd.nextInt(v.size()));
            System.out.println("PopolaDB.generaPacchetti -> scelto soggiorno:" + soggiorno + " in albergo " + soggiorno.getAlbergo().getNome());
            
            andata.setDataOra(new Date(soggiorno.getGiornoInizio().getTime() + (andata.getDataOra().getTime() % (24 * 60 * 60 * 1000))));
            ritorno.setDataOra(new Date(soggiorno.getGiornoFine().getTime() + (ritorno.getDataOra().getTime() % (24 * 60 * 60 * 1000))));
//            soggiorno.setGiornoFine(ritorno.getDataOra());
            pbService.salvaPB(andata);
            pbService.salvaPB(ritorno);
            p.getVoci().add(soggiorno);
            p.setNumeroPersone(soggiorno.getNumeroPersone());

            params = new ParametriRicercaPB();
            params.setTipo(TipoPB.Visita);
            params.setCittaMuseo(c.getCittà());
            v = pbService.trovaPB(params);
            if (v == null) {
                continue;
            }
            Visita visita = (Visita) v.get(rnd.nextInt(v.size()));
            System.out.println("PopolaDB.generaPacchetti -> scelta visita:" + visita + " al " + visita.getMuseo().getNome());
//            long giornoInizio = soggiorno.getGiornoFine().getTime()/(24*60*60*1000);
//            long giornoFine = soggiorno.getGiornoInizio().getTime()/(24*60*60*1000);
//            long dataVisita = giornoInizio + rnd.nextInt((int)((giornoFine-1)-giornoInizio));
//            dataVisita *= 24*60*60*1000;
//            visita.setDataOra(new Date(dataVisita + (visita.getDataOra().getTime() % (24 * 60 * 60 * 1000))));
            pbService.salvaPB(visita);
            p.getVoci().add(visita);

            pacchettiGenerati.add(pvService.salvaPV(p));
            System.out.println("PopolaDB.generaPacchetti -> p:"+ p);
            System.out.println("PopolaDB.generaPacchetti -> nome:"+ p.getNome());
            System.out.println("PopolaDB.generaPacchetti ===================================================:"+ p.getNome());
        }

        System.out.println("PopolaDB.generaPacchetti -> generati " + pacchettiGenerati.size() + " su " + nroPacchettiDaGenerare + "richiesti");
    }
    Utente impiegato;

    private void salvaUtentiImpiegatoEdAdmin() {
        impiegato = new Utente("impiegato@traveldream.it", "impiegato");
        impiegato.setLivello(Utente.LIVELLO_IMPIEGATO);
        impiegato.setAbilitato(true);
        Utente admin = new Utente("admin@traveldream.it", "admin");
        admin.setLivello(Utente.LIVELLO_AMMINISTRATORE);
        admin.setAbilitato(true);

        utenteService.registrazione(admin);
        utenteService.registrazione(impiegato);
    }

    private void inserisciVisita(Museo m, Date dataOra, float costo) {
        Visita v = new Visita();
        v.setAbilitato(true);
        v.setCosto(costo);
        v.setMuseo(m);
        v.setDataOra(dataOra);
        pbService.salvaPB(v);
    }

    private void inserisciVolo(Rotta r, Date dataOra, float costo) {
        Volo v = new Volo();
        v.setAbilitato(true);
        v.setCosto(costo);
        v.setDataOra(dataOra);
//        v.setNumPasseggeri(rnd.nextInt(7)+1);
        v.setRotta(r);
        pbService.salvaPB(v);
    }

    private void generaVoli(int n, int dataMassimaGiornoInizio, float costoMinimo, float costoMassimo) {
        for (int i = 0; i < n; i++) {
            Rotta r = rotteLette.get(rnd.nextInt(rotteLette.size()));
            float costo = generaCostoRandom(costoMinimo, costoMassimo);
            long dataOraVolo = rnd.nextInt(dataMassimaGiornoInizio * 24 * 60);
            long adesso = (new Date().getTime() / (60 * 1000)) * 60 * 1000;
            Date dataOra = new Date(adesso + dataOraVolo * 60 * 1000);
            inserisciVolo(r, dataOra, costo);
        }
    }

    private void generaVisite(int n, int dataMassimaGiornoInizio, float costoMinimo, float costoMassimo) {
        int aperturaMusei = 8, chiusuraMusei = 20;
        int nMezzOre = (chiusuraMusei - aperturaMusei) / 2;
        for (int i = 0; i < n; i++) {
            Museo m = museiGenerati.get(rnd.nextInt(museiGenerati.size()));
            float costo = generaCostoRandom(costoMinimo, costoMassimo);
            long giornoDaOggi = rnd.nextInt(dataMassimaGiornoInizio) * 24 * 60 * 60 * 1000;
            long ora = (rnd.nextInt(nMezzOre) + aperturaMusei) * 30 * 60 * 1000;
            long oggi = (new Date().getTime() / (24 * 60 * 60 * 1000)) * 24 * 60 * 60 * 1000;
            long dataOraVisita = oggi + giornoDaOggi + ora;
            Date dataOra = new Date(dataOraVisita);
            inserisciVisita(m, dataOra, costo);
        }
    }

    private void salvaRotte() {
        for (Rotta r : rotteLette) {
            inserisciRotta(r);
        }
    }

    private float generaCostoRandom(float min, float max) {
        float costo;
        float x = rnd.nextFloat();
        costo = x * (max - min) + min;
        costo = ((int) (costo * 100)) / 100;
        return costo;
    }

    private Date daOggi(long n) {
        Date ret = new Date();
        ret = new Date(((long) (ret.getTime() / (24 * 60 * 60 * 1000))) * 24 * 60 * 60 * 1000);
        ret = new Date(ret.getTime() + n * 24 * 60 * 60 * 1000);
        return ret;
    }

    private void generaSoggiorni(int n, int nroMinimoGiorni, int nroMassimoGiorni, int dataMassimaGiornoInizio, float costoMinimo, float costoMassimo) {
        for (int i = 0; i < n; i++) {
            Albergo a = alberghiGenerati.get(rnd.nextInt(nroAlberghiDaGenerare));
            float costo = generaCostoRandom(costoMinimo, costoMassimo);
            int nroGiorni = rnd.nextInt(nroMassimoGiorni - nroMinimoGiorni) + nroMinimoGiorni;
            long nroGiornoInizio = rnd.nextInt(dataMassimaGiornoInizio);
            System.out.println("generaSoggiorni -> nroGiornoInizio:" + nroGiornoInizio);
            long nroGiornoFine = nroGiornoInizio + nroGiorni;
            System.out.println("generaSoggiorni -> nroGiornoFine:" + nroGiornoFine);
            Date giornoInizio = daOggi(nroGiornoInizio);
            Date giornoFine = daOggi(nroGiornoFine);
            inserisciSoggiorno(a, giornoInizio, giornoFine, costo);
        }
    }

    private final String iniziMuseo[] = {"Museo"};
    private final List<String> scienziati = new ArrayList<>();

    private String generaNomeMuseo() {
        String ret = "";
        ret += iniziMuseo[rnd.nextInt(iniziMuseo.length)];
        ret += " ";
        ret += scienziati.get(rnd.nextInt(scienziati.size()));
        return ret;
    }

    private final String iniziAlbergo[] = {"Villa", "Hotel", "Residence", "Ostello", "Hotel Villa"};
    private final List<String> fiori = new ArrayList<>();
    private final List<String> colori = new ArrayList<>();

    private String generaNomeAlbergo() {
        String ret = "";
        ret += iniziAlbergo[rnd.nextInt(iniziAlbergo.length)];
        ret += " ";
        ret += fiori.get(rnd.nextInt(fiori.size()));
        ret += " ";
        ret += colori.get(rnd.nextInt(colori.size()));
        return ret;
    }

    public void generaMusei(int n) {
        for (int i = 0; i < n; i++) {
            Città c = cittàNelMondo.get(rnd.nextInt(cittàNelMondo.size()));
            String nome = generaNomeMuseo();
            String descrizione = "Lorem ipsum dolor sit amet, "
                + "consectetur adipisicing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
                + "Ut enim ad minim veniam, "
                + "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ";
            String url = getRandomImageLink();
            System.out.println("Generato museo nro " + i + ":" + nome + " a " + c.getCittà());
            inserisciMuseo(c, nome, descrizione, url);
        }
    }

    public void generaAlberghi(int n) {
        for (int i = 0; i < n; i++) {
            Città c = cittàNelMondo.get(rnd.nextInt(cittàNelMondo.size()));
            String nome = generaNomeAlbergo();
            int stelle = rnd.nextInt(5) + 1;
            String url = getRandomImageLink();
            System.out.println("Generato albergo nro " + i + ":" + nome + " " + stelle + " stelle a " + c.getCittà());
            inserisciAlbergo(c, nome, stelle, url);
        }
    }

    private void leggiCittàDaRotte() {
        cittàNelMondo.clear();
        for (Rotta r : rotteLette) {
            Città c = new Città(r.getNazionePartenza(), r.getCittaPartenza());
            cittàNelMondo.add(c);
        }
    }

    public void leggiRotteDaFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String campi[] = line.split(";");
            String nazionePartenza = campi[2].replace("\"", "");
            String nazioneArrivo = campi[5].replace("\"", "");
            String cittàPartenza = campi[1].replace("\"", "");
            String cittàArrivo = campi[4].replace("\"", "");
            String aeroportoPartenza = campi[0].replace("\"", "");
            String aeroportoArrivo = campi[3].replace("\"", "");
            String compagniaAerea = campi[6].replace("\"", "");
            Rotta r = new Rotta();
            r.setNazionePartenza(nazionePartenza);
            r.setNazioneArrivo(nazioneArrivo);
            r.setCittaPartenza(cittàPartenza);
            r.setCittaArrivo(cittàArrivo);
            r.setAeroportoPartenza(aeroportoPartenza);
            r.setAeroportoArrivo(aeroportoArrivo);
            r.setCompagniaAerea(compagniaAerea);
            rotteLette.add(r);
        }
    }

    private void leggiCittàDaFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String cc[] = line.split(";");
            String nazione = cc[0].replace("\"", "");
            String città = cc[1].replace("\"", "");
            Città c = new Città(nazione, città);
            cittàNelMondo.add(c);
        }
    }

    private void leggiFioriDaFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            fiori.add(line);
        }
    }

    private void leggiColoriDaFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            colori.add(line);
        }
    }

    private void leggiScienziatiDaFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            scienziati.add(line);
        }
    }

    private void inserisciRotta(Rotta r) {
        edbService.salvaRotta(r);
    }

    private void inserisciMuseo(Città c, String nome, String descrizione, String url) {
        Museo m = new Museo();
        m.setNome(nome);
        m.setCitta(c.getCittà());
        m.setDescrizione(descrizione);
        m.setUrlFoto(url);
        museiGenerati.add(edbService.salvaMuseo(m));
    }

    private void inserisciAlbergo(Città c, String nome, int stelle, String url) {
        Albergo albergo = new Albergo();
        albergo.setNome(nome);
        albergo.setDescrizione("Lorem ipsum dolor sit amet, "
                + "consectetur adipisicing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
                + "Ut enim ad minim veniam, "
                + "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ");
        albergo.setCitta(c.getCittà());
        albergo.setStelle(stelle);
        albergo.setUrlFoto(url);
        alberghiGenerati.add(edbService.salvaAlbergo(albergo));
    }

    private void inserisciSoggiorno(Albergo a, Date giornoInizio, Date giornoFine, float costo) {
        Soggiorno s = new Soggiorno();
        s.setAlbergo(a);
        s.setAbilitato(true);
        s.setCosto(costo);
        s.setGiornoInizio(giornoInizio);
        s.setGiornoFine(giornoFine);
        s.setNumeroPersone(3);
        pbService.salvaPB(s);
    }
}

class Città {

    private String nazione, città;

    public Città(String nazione, String città) {
        this.nazione = nazione;
        this.città = città;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public String getCittà() {
        return città;
    }

    public void setCittà(String città) {
        this.città = città;
    }

}
