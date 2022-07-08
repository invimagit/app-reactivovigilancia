/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoCamposcompTemp;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ingeniero Jaime Alberto Gutiérrez Mejía
 */
@Stateless
public class ReactivoCamposComplementariosTempFacade extends AbstractReactivoFacade<ReactivoCamposcompTemp> implements ReactivoCamposComplementariosTempFacadeLocal 
{
    //********************************************************
    //********************************************************
    //********************************************************
    //********************************************************
    //********************************************************
    //********************************************************
    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager em;
    //********************************************************
    //********************************************************
    //********************************************************
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    //********************************************************
    //********************************************************
    //********************************************************
    public ReactivoCamposComplementariosTempFacade() {
        super(ReactivoCamposcompTemp.class);
    }
    //********************************************************
    //********************************************************
    //********************************************************
    //********************************************************
    //********************************************************
    //********************************************************
}
