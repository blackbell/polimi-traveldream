package it.polimi.traveldream.service;

import it.polimi.traveldream.data.UtenteDAO;
import it.polimi.traveldream.model.Utente;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rh0x
 */
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Stateless
public class UtenteService implements UtenteServiceLocal {

    
    @Autowired
    private UtenteDAO utenteDAO;

    public UtenteDAO getUtenteDAO() {
        return utenteDAO;
    }
    
    public void setUtenteDAO(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;
    }
    
    public boolean isPresent(Utente utente) {
        Utente utenteDaCercare = utenteDAO.findOne(utente.getEmail());
        return utenteDaCercare != null; 
    }

    @Transactional
    @Override
    public Utente registrazione(Utente utente) {
        try{
            if(isPresent(utente))
                return null;
            return utenteDAO.saveAndFlush(utente);
        }catch(Exception ex){
            System.err.println("UtenteService:Exception " + ex.getMessage());
            return null;
        }
    }
    
    
    
}
