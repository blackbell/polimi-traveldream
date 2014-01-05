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
    
    

    @Transactional
    @Override
    public Utente registrazione(Utente utente) {
        return utenteDAO.saveAndFlush(utente);
    }
    
    
    
}
