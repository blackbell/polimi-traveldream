/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.popoladb;


import static it.polimi.traveldream.data.TestUtilities.getRandomImageLink;
import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Soggiorno;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.model.Volo;
import it.polimi.traveldream.service.EDBServiceLocal;
import it.polimi.traveldream.service.EJBServiceTestSuite;
import static it.polimi.traveldream.service.EJBServiceTestSuite.container;
import it.polimi.traveldream.service.PBServiceLocal;
import it.polimi.traveldream.service.PVServiceLocal;
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
public class PopolaDB{

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
    
    static Random rnd = new Random(new Date().getTime());
        
    private static final int nroAlberghiDaGenerare = 50;
    private static final int nroSoggiorniDaGenerare = 50;
    private static final int nroVoliDaGenerare = 50;
    private final List<Città> cittàNelMondo = new ArrayList<>();
    private final List<Albergo> alberghiGenerati = new ArrayList<>();
    private final List<Rotta> rotteLette = new ArrayList<>();
    
    @Test
    public void popolaDB() throws Exception{
        
        leggiCittàDaFile("città.csv");
        leggiFioriDaFile("fiori.txt");
        leggiColoriDaFile("colori.txt");
        leggiRotteDaFile("rotte.csv");

        generaAlberghi(nroAlberghiDaGenerare);
        generaSoggiorni(nroSoggiorniDaGenerare,  2, 14, 180, 30, 300);
        salvaRotte();
        generaVoli(nroVoliDaGenerare, 180, 30, 300);
        salvaUtentiImpiegatoEdAdmin();
    }
    
    private void salvaUtentiImpiegatoEdAdmin(){
        Utente impiegato = new Utente("impiegato@traveldream.it", "impiegato");
        impiegato.setLivello(Utente.LIVELLO_IMPIEGATO);
        impiegato.setAbilitato(true);
        Utente admin = new Utente("admin@traveldream.it", "admin");
        admin.setLivello(Utente.LIVELLO_AMMINISTRATORE);
        admin.setAbilitato(true);
    
        utenteService.registrazione(admin);
        utenteService.registrazione(impiegato);
    }
    
    private void inserisciVolo(Rotta r, Date dataOra, float costo){
        Volo v = new Volo();
        v.setAbilitato(true);
        v.setCosto(costo);
        v.setDataOra(dataOra);
//        v.setNumPasseggeri(rnd.nextInt(7)+1);
        v.setRotta(r);
        pbService.salvaPB(v);
    }
    
    private void generaVoli(int n, int dataMassimaGiornoInizio, float costoMinimo, float costoMassimo) {
        for (int i = 0; i < n; i++){
            Rotta r = rotteLette.get(rnd.nextInt(rotteLette.size()));
            float costo = generaCostoRandom(costoMinimo, costoMassimo);
            long dataOraVolo = rnd.nextInt(dataMassimaGiornoInizio * 24 * 60);
            long adesso = (new Date().getTime() / (60 * 1000)) * 60 * 1000;
            Date dataOra = new Date(adesso + dataOraVolo * 60 * 1000);
            inserisciVolo(r, dataOra, costo);
        }
    }
    
    private void salvaRotte(){
        for(Rotta r : rotteLette)
            inserisciRotta(r);
    }
    
    private float generaCostoRandom(float min, float max){
        float costo;
        float x = rnd.nextFloat();
        costo = x * (max-min) + min;
        costo = ((int)(costo * 100)) / 100;
        return costo;
    }
    
    private Date daOggi(long n){
        Date ret = new Date();
        ret = new Date(((long)(ret.getTime() / (24 * 60 * 60 * 1000))) * 24 * 60 * 60 * 1000);
        ret = new Date(ret.getTime() + n * 24 * 60 * 60 * 1000);
        return ret;
    }
    
    private void generaSoggiorni(int n, int nroMinimoGiorni, int nroMassimoGiorni, int dataMassimaGiornoInizio, float costoMinimo, float costoMassimo) {
        for (int i = 0; i < n; i++){
            Albergo a = alberghiGenerati.get(rnd.nextInt(nroAlberghiDaGenerare));
            float costo = generaCostoRandom(costoMinimo, costoMassimo);
            int nroGiorni = rnd.nextInt(nroMassimoGiorni-nroMinimoGiorni) + nroMinimoGiorni;
            long nroGiornoInizio = rnd.nextInt(dataMassimaGiornoInizio);
            System.out.println("generaSoggiorni -> nroGiornoInizio:" + nroGiornoInizio);
            long nroGiornoFine = nroGiornoInizio+nroGiorni;
            System.out.println("generaSoggiorni -> nroGiornoFine:" + nroGiornoFine);
            Date giornoInizio = daOggi(nroGiornoInizio);
            Date giornoFine = daOggi(nroGiornoFine);
            inserisciSoggiorno(a, giornoInizio, giornoFine, costo);
        }
    }
        
    private final String iniziAlbergo[] = {"Villa", "Hotel", "Residence", "Ostello"};
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
    
    private void inserisciRotta(Rotta r){
        edbService.salvaRotta(r);
    }

    private void inserisciAlbergo(Città c, String nome, int stelle, String url) {
        Albergo albergo = new Albergo();
        albergo.setNome(nome);
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
