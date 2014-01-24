/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Pagamento;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.model.Voce;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 * EJB che offre i servizi per trattare gli oggetti di tipo "Pagamento"
 * @author Dario
 */
@Local
public interface PagamentoServiceLocal {
    
    /**
     * Effettua il pagamento di ogni voce che compone il PV specificato
     * @param pv il PV da pagare
     * @param u l'utente pagante
     * @return una lista contentente i pagamenti andati a buon fine delle voci contenute nel in pv.voci.
     */
    public List<Pagamento> pagamentoPV(Pacchetto pv, Utente u);
    
    /**
     * Effettua il pagamento di una voce di un PV
     * @param pv il PV dentro cui la voce compare
     * @param pb la voce da pagare
     * @param u l'utente pagante
     * @return un oggetto Pagamento che contiene le informazioni sull'esito del 
     * pagamento nel caso la transazoine vada a buon fine o <i>null</i> nel caso in cui 
     * o il pb non appartenga al pv specificato o nel caso in cui pv.voci sia <i>null</i>.
     */
    public Pagamento pagamentoPB(Pacchetto pv, Voce pb, Utente u);
    
    /**
     * Restituisce tutti i pagamenti conformi ai criteri indicati
     * @param da data di inizio del periodo da considerare. Se <i>null</i> viene considerata tutta la storia del sistema.
     * @param a data di fine del periodo da considerare. Se <i>null</i> viene considerata la storia del sistema fino al momento della chiamata.
     * @param u l'utente di cui si vuole consultare i pagamenti associati
     * @param pv il pv di cui si vuole consultare i pagamenti associati
     * @return una lista di oggetti Pagamento conformi ai parametri indicati in ingresso 
     * o <i>null</i> nel caso in cui l'utente u non esista o il pacchetto pv non esista.
     */
    public List<Pagamento> recuperaPagamenti(Date da, Date a, Utente u, Pacchetto pv);
    
}
