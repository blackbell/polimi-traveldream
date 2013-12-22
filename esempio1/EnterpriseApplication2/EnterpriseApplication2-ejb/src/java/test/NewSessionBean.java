/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import javax.ejb.Stateless;

/**
 *
 * @author Dario
 */
@Stateless
public class NewSessionBean implements NewSessionBeanLocal {

    @Override
    public String businessMethod() {
        return "AHAHAHA";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    
}
