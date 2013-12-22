/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dario
 */
@Stateless
public class NewEntityFacade extends AbstractFacade<NewEntity> {
    @PersistenceContext(unitName = "EnterpriseApplication2-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewEntityFacade() {
        super(NewEntity.class);
    }
    
}
