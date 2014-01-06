package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Rotta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dario
 */
@Repository
public interface RottaDAO extends JpaRepository<Rotta, Integer>{
    
}
