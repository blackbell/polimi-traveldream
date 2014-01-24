/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Pacchetto;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Classe che si interfaccia col database per il recupero delle entità PV.
 * @author Dario
 */
@Repository
public interface PVDAO extends JpaRepository<Pacchetto, Integer>{

    public List<Pacchetto> findByParams(
            @Param("nome") String nome, 
            @Param("cittaAlbergo") String cittàAlbergo, 
            @Param("nazionePartenza") String nazionePartenza, 
            @Param("nazioneArrivo") String nazioneArrivo, 
            @Param("dataInizio") Date dataInizio, 
            @Param("dataInizio2") Date dataInizio2, 
            @Param("dataFine") Date dataFine,
            @Param("disabilitatiInclusi") Boolean disabilitatiInclusi
    );

    
}
