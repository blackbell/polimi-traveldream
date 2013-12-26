package it.polimi.traveldream.data;

import it.polimi.traveldream.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteDAO extends JpaRepository<Utente,String>{
    
}
