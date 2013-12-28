package it.polimi.traveldream.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="utenti")
public class Utente implements Serializable {
    @Id
    private String email;
    
    @Basic(optional=false)
    private String password;
    
    @Basic(optional=false)
    private String stato;
    
    
    public Utente() {
    }

    public Utente(String email, String password) {
        this.email = email;
        this.password = password;
    }

   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

   
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + (this.email != null ? this.email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utente other = (Utente) obj;
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        return true;
    }    
}
