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
    
    public Collection<Soggiorno> findByParams(
            @Param("nomeAlbergo") String nomeAlbergo, 
            @Param("cittaAlbergo") String cittaAlbergo, 
            @Param("dataInizioSoggiorno") Date dataInizioSoggiorno, 
            @Param("dataInizioSoggiorno2") Date dataInizioSoggiorno2, 
            @Param("dataFineSoggiorno") Date dataFineSoggiorno, 
            @Param("disabilitatiInclusi") boolean disabilitatiInclusi);

    public Collection<Visita> findByParams(
            @Param("giornoVisita") Date giornoVisita, 
            @Param("nomeMuseo") String nomeMuseo, 
            @Param("cittaMuseo") String cittaMuseo, 
            @Param("disabilitatiInclusi") boolean disabilitatiInclusi);
}
