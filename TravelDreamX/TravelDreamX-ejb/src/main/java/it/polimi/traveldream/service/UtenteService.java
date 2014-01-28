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
import org.mindrot.jbcrypt.BCrypt;
//import org.springframework.security.crypto.bcrypt.BCrypt;

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
        System.out.println("UtenteService.registrazione -> utente.password: " + utente.getPassword());
        String pw_hash = BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt());
        utente.setPassword(pw_hash);
        System.out.println("UtenteService.registrazione -> utente.password: " + utente.getPassword());
        utente = utenteDAO.saveAndFlush(utente);
        if (utente != null) utente.setPassword(null);
        return utente;
    }

    @Transactional
    @Override
    public Utente login(Utente u) {
        System.out.println("UtenteService.login() -> u: " + u);
        System.out.println("UtenteService.login() -> u.getEmail(): " + u.getEmail());
        Utente ret = utenteDAO.findOne(u.getEmail());       
        System.out.println("UtenteService.login -> u.password  : " + u.getPassword());
        
        if (ret != null){
            System.out.println("UtenteService.login -> ret.password  : " + ret.getPassword());
            if (!BCrypt.checkpw(u.getPassword(), ret.getPassword()))
                ret = null;
            else
                ret.setPassword(null);
        }
        return ret;
    }

    @Override
    public List<Utente> recuperaUtenti() {
        return utenteDAO.findAll();
    }

    @Transactional
    @Override
    public Boolean disattivaUtente(Utente utente) {
        Utente u = utenteDAO.findOne(utente.getEmail());
        if (u == null) return null;
        u.setAbilitato(false);
        utente = utenteDAO.save(u);
        return utente.getAbilitato();
    }

    @Transactional
    @Override
    public Boolean attivaUtente(Utente utente) {
        Utente u = utenteDAO.findOne(utente.getEmail());
        if (u == null) return null;
        u.setAbilitato(true);
        utente = utenteDAO.save(u);
        return utente.getAbilitato();
    }

    @Transactional
    @Override
    public Integer modificaLivello(Utente utente) {
        System.out.println("UtenteService.modificaLivello -> utente.livello:" + utente.getLivello());
        Utente u = utenteDAO.findOne(utente.getEmail());
        if (u == null) return null;
//        if (!u.getAbilitato().equals(utente.getAbilitato())) return null;
        System.out.println("UtenteService.modificaLivello -> u.livello:" + u.getLivello());
        u.setLivello(utente.getLivello());
        System.out.println("UtenteService.modificaLivello -> utente.livello:" + utente.getLivello());

//        if (!u.getPassword().equals(utente.getPassword())) return null;
        utente = utenteDAO.save(u);
        System.out.println("UtenteService.modificaLivello -> utente.livello:" + utente.getLivello());
        return utente.getLivello();
    }


}
