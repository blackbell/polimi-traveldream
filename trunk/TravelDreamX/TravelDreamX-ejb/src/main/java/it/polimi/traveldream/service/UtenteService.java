/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */
package it.polimi.traveldream.service;

import it.polimi.traveldream.data.UtenteDAO;
import it.polimi.traveldream.model.Utente;
import java.util.List;
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
        if(isPresent(utente))
            return null;
        return utenteDAO.saveAndFlush(utente);
    }

    @Transactional
    @Override
    public Utente login(Utente u) {
        System.out.println("login() -> u: " + u);
        System.out.println("login() -> u.getEmail(): " + u.getEmail());
        System.out.println("login() -> utenteDAO:" + utenteDAO);
        Utente ret = utenteDAO.findOne(u.getEmail());
        return ret;
    }   

    @Override
    public List<Utente> recuperaUtenti() {
        return utenteDAO.findAll();
    }

    @Override
    public Boolean disattivaUtente(Utente utente) {
        Utente u = utenteDAO.findOne(utente.getEmail());
        u.setAbilitato(false);
        utente = utenteDAO.save(u);
        return utente.getAbilitato();
    }

    @Override
    public Boolean attivaUtente(Utente utente) {
        Utente u = utenteDAO.findOne(utente.getEmail());
        u.setAbilitato(true);
        utente = utenteDAO.save(u);
        return utente.getAbilitato();
    }

    @Override
    public Integer modificaLivello(Utente utente) {
        Utente u = utenteDAO.findOne(utente.getEmail());
        if (u == null) return null;
        if (!u.getAbilitato().equals(utente.getAbilitato())) return null;
//        if (!u.getPassword().equals(utente.getPassword())) return null;
        utente = utenteDAO.save(utente);
        return utente.getLivello();
    }
    
    
}
