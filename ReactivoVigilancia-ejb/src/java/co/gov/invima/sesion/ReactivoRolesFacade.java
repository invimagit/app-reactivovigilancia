/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoRoles;
import org.apache.log4j.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Priority;

/**
 *
 * @author mgualdrond
 */
@Stateless
public class ReactivoRolesFacade extends AbstractReactivoFacade<ReactivoRoles> implements ReactivoRolesFacadeLocal {

    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    public EntityManager em;
    
    private final static Logger logEJBReactivo = Logger.getLogger(ReactivoRolesFacade.class.getName());

    public ReactivoRolesFacade() {
        super(ReactivoRoles.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Obtioene el rol por su id para asociar los recursos del menu al usuario
     * @param idRol: identificador de Rol
     * @return: retorna la lista de roles
     */
    @Override
    public ReactivoRoles obtenerMenuPorRol(Integer idRol){
        ReactivoRoles roles = null;
        try {
            Query q = em.createNamedQuery("ReactivoRoles.findByIdrol");
            q.setParameter("idrol", idRol);
            roles = (ReactivoRoles) q.getSingleResult();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener rol", e);
        } finally {
            return roles;
        }
    }
}
