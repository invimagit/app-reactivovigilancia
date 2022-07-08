/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoRed;
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
public class ReactivoRedFacade extends AbstractReactivoFacade<ReactivoRed> implements ReactivoRedFacadeLocal {

    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager em;

    private static final Logger logEJBReactivo = Logger.getLogger(ReactivoRedFacade.class.getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReactivoRedFacade() {
        super(ReactivoRed.class);
    }

    /**
     * Obtiene el maximo id para insertar el id al momento de almacenar una
     * inscripcion en la red
     *
     * @return Máximo de ID de la tabal ReactivoRed
     */
    @Override
    public Integer obtenerIdRed() {
        Integer maximo = 0;
        try {
            String consulta = "select max(r.id) from reactivo_red r";
            Query q = em.createNativeQuery(consulta);
                maximo = (Integer) q.getSingleResult();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al calular ID de red", e);
        } finally {
            return maximo;
        }
    }

    /**
     * Obtiene el objeto del maximo
     * @return
     */
    @Override
    public ReactivoRed obtenerIdRedObj(){
        ReactivoRed rr = null;
        try {
          String consulta = "select r from ReactivoRed r where r.id=(select max(r1.id) FROM ReactivoRed r1)";
            Query q = em.createQuery(consulta);
            rr = (ReactivoRed) q.getResultList().get(0);
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al Obtener ID de red", e);
        } finally {
            return rr;
        }
    }
    
    /**
     * Obtiene la entidad por nit
     *
     * @param nit: numero de nit
     * @return: objeto con los datos de la entidad en la red
     */
    @Override
    public ReactivoRed obtenerEntidadPorNit(String nit) {
        ReactivoRed red = null;
        try {
            Query q = em.createNamedQuery("ReactivoRed.findByNit");
            q.setParameter("nit", nit);
            red = (ReactivoRed) q.getSingleResult();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR,"Error " + red.toString());
        } finally {
            return red;
        }
    }
}
