/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Soggiorno;
import it.polimi.traveldream.model.Visita;
import it.polimi.traveldream.model.Voce;
import it.polimi.traveldream.model.Volo;
import java.util.Collection;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Classe che si interfaccia col database per il recupero delle entità di tipo Voce (Volo/Visita o Soggiorno).
 * @author rh0x
 */
@Repository
public interface VoceDAO extends JpaRepository<Voce, Integer>{

    public Collection<Volo> findByParams(
            @Param("cittaPartenzaVolo") String cittaPartenzaVolo, 
            @Param("cittaArrivoVolo") String cittàArrivoVolo, 
            @Param("dataOraVolo") Date dataOraVolo, 
            @Param("disabilitatiInclusi") boolean disabilitatiInclusi);
    
//    public List<Volo> findByParams(@Param("rotte") Collection<Rotta> rotte,
//                                       @Param("dataOra") Date dataOra,
//                                       //@Param("costo") Float costo,
//                                       @Param("numPasseggeri") Integer numPasseggeri,
//                                       @Param("disabilitatiInclusi") boolean disabilitatiInclusi);
//    public List<Soggiorno> findByParams(@Param("albergo") Albergo albergo,
//                                       @Param("giornoInizio") Date giornoInizio,
//                                       @Param("citta") String citta,
//                                       @Param("numeroPersone") Integer numeroPersone,
//                                       @Param("disabilitatiInclusi") boolean disabilitatiInclusi);
//    public List<Visita> findByParams(@Param("museo") Museo museo,
//                                       @Param("data") Date data,
//                                       @Param("citta") String citta,
//                                       @Param("numeroPersone") Integer numeroPersone,
//                                       @Param("disabilitatiInclusi") boolean disabilitatiInclusi);

    public Collection<Soggiorno> findByParams(
            @Param("nomeAlbergo") String nomeAlbergo, 
            @Param("cittaAlbergo") String cittaAlbergo, 
            @Param("dataInizioSoggiorno") Date dataInizioSoggiorno, 
            @Param("dataFineSoggiorno") Date dataFineSoggiorno, 
            @Param("disabilitatiInclusi") boolean disabilitatiInclusi);

    public Collection<Visita> findByParams(
            @Param("giornoVisita") Date giornoVisita, 
            @Param("nomeMuseo") String nomeMuseo, 
            @Param("cittaMuseo") String cittaMuseo, 
            @Param("disabilitatiInclusi") boolean disabilitatiInclusi);
}
