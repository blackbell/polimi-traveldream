/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.polimi.traveldream.service;

import it.polimi.traveldream.data.PVDAO;
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
public class PVService implements PVServiceLocal {

    @Autowired
    PVDAO pvDAO;
}