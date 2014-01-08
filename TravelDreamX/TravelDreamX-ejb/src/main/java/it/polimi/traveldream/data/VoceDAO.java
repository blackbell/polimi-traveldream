package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Voce;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rh0x
 */
public interface VoceDAO extends JpaRepository<Voce, Integer>{
    
}
