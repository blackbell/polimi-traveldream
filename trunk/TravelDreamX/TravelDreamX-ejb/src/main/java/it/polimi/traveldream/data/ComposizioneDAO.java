/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Composizione;
import it.polimi.traveldream.model.ComposizionePK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Dario
 */
public interface ComposizioneDAO extends JpaRepository<Composizione, ComposizionePK>{
    
}
