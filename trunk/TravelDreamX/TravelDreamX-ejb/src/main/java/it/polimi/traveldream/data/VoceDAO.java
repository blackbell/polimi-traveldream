package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Voce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rh0x
 */
@Repository
public interface VoceDAO extends JpaRepository<Voce, Integer>{
    
}
