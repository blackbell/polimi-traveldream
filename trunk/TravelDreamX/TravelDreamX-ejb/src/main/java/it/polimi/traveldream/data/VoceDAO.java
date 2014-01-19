/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.Museo;
import it.polimi.traveldream.model.Rotta;
import it.polimi.traveldream.model.Soggiorno;
import it.polimi.traveldream.model.Visita;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.model.Volo;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Classe che si interfaccia col database per il recupero delle entità di tipo Voce (Volo/Visita o Soggiorno).
 * @author rh0x
 */
@Repository
public interface VoceDAO extends JpaRepository<Voce, Integer>{
    
    public List<Volo> findByParams(@Param("rotta") Rotta rotta,
                                       @Param("dataOra") Date dataOra,
                                       //@Param("costo") Float costo,
                                       @Param("numPasseggeri") Integer numPasseggeri);
    public List<Soggiorno> findByParams(@Param("albergo") Albergo albergo,
                                       @Param("giornoInizio") Date giornoInizio,
                                       @Param("citta") String citta,
                                       @Param("numeroPersone") Integer numeroPersone);
    public List<Visita> findByParams(@Param("museo") Museo museo,
                                       @Param("data") Date data,
                                       @Param("citta") String citta,
                                       @Param("numeroPersone") Integer numeroPersone);
}
