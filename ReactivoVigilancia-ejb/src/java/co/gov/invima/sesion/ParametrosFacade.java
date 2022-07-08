/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.Parametros;
import org.apache.log4j.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mgualdrond
 */
@Stateless
public class ParametrosFacade extends AbstractReactivoFacade<Parametros> implements ParametrosFacadeLocal {

    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager em;

    private final static Logger logEJBReactivo = Logger.getLogger(DepartamentosFacade.class.getName());
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametrosFacade() {
        super(Parametros.class);
    }
}
