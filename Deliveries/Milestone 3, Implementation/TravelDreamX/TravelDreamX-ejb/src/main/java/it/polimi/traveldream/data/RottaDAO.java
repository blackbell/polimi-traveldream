/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.data;

import it.polimi.traveldream.model.EDB;
import it.polimi.traveldream.model.Rotta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Classe che si interfaccia col database per il recupero delle entità Rotta.
 * @author Dario
 */
@Repository
public interface RottaDAO extends JpaRepository<Rotta, Integer>{

    public List<EDB> findByParams(@Param("aeroportoPartenza") String aeroportoPartenza, 
                                  @Param("aeroportoArrivo") String aeroportoArrivo, 
                                  @Param("cittaPartenza") String cittàPartenza, 
                                  @Param("cittaArrivo") String cittàArrivo, 
                                  @Param("nazionePartenza") String nazionePartenza, 
                                  @Param("nazioneArrivo") String nazioneArrivo, 
                                  @Param("compagniaAerea") String compagniaAerea);
    
}