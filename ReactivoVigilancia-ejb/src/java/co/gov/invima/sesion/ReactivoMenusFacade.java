/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoMenus;
import org.apache.log4j.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mgualdrond
 */
@Stateless
public class ReactivoMenusFacade extends AbstractReactivoFacade<ReactivoMenus> implements ReactivoMenusFacadeLocal {

    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    public EntityManager em;
    
    private final static Logger logEJBReactivo = Logger.getLogger(ReactivoMenusFacade.class.getName());
    
    public ReactivoMenusFacade() {
        super(ReactivoMenus.class);
    }

    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }


    
}
