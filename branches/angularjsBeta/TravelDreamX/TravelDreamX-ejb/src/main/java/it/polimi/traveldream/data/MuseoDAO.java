/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.data;

import it.polimi.traveldream.model.EDB;
import it.polimi.traveldream.model.Museo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dario
 */
@Repository
public interface MuseoDAO extends JpaRepository<Museo, Integer> {
    public List<EDB> findByParams(@Param("nome") String nome,@Param("citta") String città);
}
