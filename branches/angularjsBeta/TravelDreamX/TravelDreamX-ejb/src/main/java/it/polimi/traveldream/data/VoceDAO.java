package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.model.Volo;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rh0x
 */
@Repository
public interface VoceDAO extends JpaRepository<Voce, Integer>{
    
    @Deprecated
    public List<Volo> findVoloByRotta(@Param("rotta") Rotta rotta);
    @Deprecated
    public List<Volo> findVoloByDataOra(@Param("dataOra") Date dataOra);
    
    public List<Volo> findByParams(@Param("rotta") Rotta rotta,
                                       @Param("dataOra") Date dataOra,
                                       //@Param("costo") Float costo,
                                       @Param("numPasseggeri") Integer numPasseggeri);
}
