/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dario
 */
@Repository
public interface PagamentoDAO extends JpaRepository<Pagamento, Integer> {
    
}
