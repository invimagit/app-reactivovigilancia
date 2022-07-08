/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoModalidad;
import java.util.List;
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
public class ReactivoModalidadFacade extends AbstractReactivoFacade<ReactivoModalidad> implements ReactivoModalidadFacadeLocal {
    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager em;

    private static Logger logEJBReactivo = Logger.getLogger(ReactivoModalidadFacade.class.getName());
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReactivoModalidadFacade() {
        super(ReactivoModalidad.class);
    }
    
    /**
     * Obtiene el lsitado de modalidades que se encuentran activas
     * @return Listado de las modadlidades activas
     */
    @Override
    public List<ReactivoModalidad> obtenerModalidadActivos(){
        List<ReactivoModalidad> modalidads = null;
        try {
            String consulta = "SELECT r FROM ReactivoModalidad r WHERE r.estado = :estado"
                    +" ORDER BY r.descripcion";
            Query q = em.createQuery(consulta);
            q.setParameter("estado", 'A');
            modalidads = q.getResultList();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener modalidad activa", e);
        }finally{
            return modalidads;
        }
    }
}
