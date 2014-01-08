/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Composizione;
import it.polimi.traveldream.model.ComposizionePK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Dario
 */
public interface ComposizioneDAO extends JpaRepository<Composizione, ComposizionePK>{
    
    
    public List<Composizione> findByIdPacchetto(@Param("idPacchetto") Integer idPacchetto);
}
