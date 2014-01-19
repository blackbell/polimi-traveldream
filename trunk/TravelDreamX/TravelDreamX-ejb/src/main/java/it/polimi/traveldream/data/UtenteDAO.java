/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 /**
  * Classe che si interfaccia col database per il recupero delle entità Utente.
  * @author Dario
  */
@Repository
public interface UtenteDAO extends JpaRepository<Utente,String>{
    
}
