package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.EDB;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dario
 */
@Repository
public interface AlbergoDAO extends JpaRepository<Albergo, Integer>{
    
    /*public List<EDB> findByParams(
                                            @Param("nome") String nome,
                                            @Param("citta") String città
                                          );*/
    public List<EDB> findByParams(
                                            @Param("nome") String nome,
                                            @Param("citta") String città,
                                            @Param("stelle") Integer stelle
                                          );
}