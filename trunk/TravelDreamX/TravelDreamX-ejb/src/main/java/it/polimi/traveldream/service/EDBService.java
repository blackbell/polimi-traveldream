/*
 * Politecnico di Milano, Software Engineering 2 (autumn semester)
 * proj codename: TravelDreamX
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.data.AlbergoDAO;
import it.polimi.traveldream.data.RottaDAO;
import it.polimi.traveldream.model.Albergo;
import it.polimi.traveldream.model.EDB;
import it.polimi.traveldream.model.Museo;
import it.polimi.traveldream.model.Rotta;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

/**
 *
 * @author Dario
 */
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Stateless
public class EDBService implements EDBServiceLocal {

    @Autowired
    private RottaDAO rottaDAO;
    @Autowired
    private AlbergoDAO albergoDAO;
//    @Autowired
//    private MuseoDAO museoDAO;
    
    @Override
    public List<EDB> trovaEntità(ParametriRicercaEDB param){
        List<EDB> ret = null;
        switch(param.getTipo()){
            case Albergo:
                String nome = param.getNome();
                String città = param.getCitta();
                Integer stelle = param.getStelle();
                System.out.println("trovaEntità() -> tipo:" + param.getTipo().toString());
                System.out.println("trovaEntità() -> nome:" + nome);
                System.out.println("trovaEntità() -> città:" + città);
                System.out.println("trovaEntità() -> stelle:" + stelle);
                ret = albergoDAO.findByParams(nome!=null?nome:"%", città!=null?città:"%", stelle);
                break;
            case Rotta:
                String aeroportoPartenza = param.getAeroportoPartenza();
                String aeroportoArrivo = param.getAeroportoArrivo();
                String cittàPartenza = param.getCittàPartenza();
                String cittàArrivo = param.getCittàArrivo();
                String nazionePartenza = param.getNazionePartenza();
                String nazioneArrivo = param.getNazioneArrivo();
                String compagniaAerea = param.getCompagniaAerea();
                ret = rottaDAO.findByParams(aeroportoPartenza !=null ? aeroportoPartenza :"%",
                                            aeroportoArrivo   !=null ? aeroportoArrivo   :"%",
                                            cittàPartenza     !=null ? cittàPartenza     :"%",
                                            cittàArrivo       !=null ? cittàArrivo       :"%",
                                            nazionePartenza   !=null ? nazionePartenza   :"%",
                                            nazioneArrivo     !=null ? nazioneArrivo     :"%",
                                            compagniaAerea    !=null ? compagniaAerea    :"%");
                break;
            default:
                break;
        }
        return ret;
    }

    @Override
    public Rotta salvaRotta(Rotta r) {
        return rottaDAO.saveAndFlush(r);
    }

    @Override
    public Albergo salvaAlbergo(Albergo a) {
        return albergoDAO.saveAndFlush(a);
    }

    @Override
    public Museo salvaMuseo(Museo m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rotta retrieveRottaByID(int id) {
        return rottaDAO.findOne(id);
    }

    @Override
    public Albergo retrieveAlbergoByID(int id) {
        return albergoDAO.findOne(id);
    }

    @Override
    public Museo retrieveMuseoByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public RottaDAO getRottaDAO() {
        return rottaDAO;
    }

    public void setRottaDAO(RottaDAO rottaDAO) {
        this.rottaDAO = rottaDAO;
    }

    public AlbergoDAO getAlbergoDAO() {
        return albergoDAO;
    }

    public void setAlbergoDAO(AlbergoDAO albergoDAO) {
        this.albergoDAO = albergoDAO;
    }

//    public MuseoDAO getMuseoDAO() {
//        return museoDAO;
//    }
//
//    public void setMuseoDAO(MuseoDAO museoDAO) {
//        this.museoDAO = museoDAO;
//    }
    
    

}
