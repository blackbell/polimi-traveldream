/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Pacchetto;
import it.polimi.traveldream.model.Pagamento;
import it.polimi.traveldream.model.Utente;
import it.polimi.traveldream.model.Voce;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Classe che si interfaccia col database per il recupero delle entità Pagamento.
 * @author Dario
 */
@Repository
public interface PagamentoDAO extends JpaRepository<Pagamento, Integer> {
 
    public List<Pagamento> findByVoce(@Param("voce") Voce pb);
    public List<Pagamento> findByUtente(@Param("utente") Utente u);
    public List<Pagamento> findByParams(
            @Param("da") Date da, 
            @Param("a") Date a,
            @Param("u") Utente u, 
            @Param("pv") Pacchetto pv);
}
