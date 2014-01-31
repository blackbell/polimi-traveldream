/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.EDB;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Classe che si interfaccia col database per il recupero delle entità Albergo.
 *
 * @author Dario
 */
@Repository
public interface AlbergoDAO extends JpaRepository<Albergo, Integer> {

    public List<EDB> findByParams(
            @Param("nome") String nome,
            @Param("citta") String città,
            @Param("stelle") Integer stelle
    );
}
