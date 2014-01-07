package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Albergo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dario
 */
@Repository
public interface AlbergoDAO extends JpaRepository<Albergo, Integer>{
    
}
