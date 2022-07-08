/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.Departamentos;
import java.util.List;
import java.util.logging.Level;
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
public class DepartamentosFacade extends AbstractReactivoFacade<Departamentos> implements DepartamentosFacadeLocal {
    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager em;

    private final static Logger logEJBReactivo = Logger.getLogger(DepartamentosFacade.class.getName());
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartamentosFacade() {
        super(Departamentos.class);
    }
    
    /**
     * Obtiene la lista de departamentos ordenado por nombre
     * @return Listado de departamentos ordenado
     */
    @Override
    public List<Departamentos> obtenerDptoOrdenado(){
        List<Departamentos> departamentoses = null;
        try {
            String consulta ="SELECT d FROM Departamentos d ORDER BY d.descripcion";
            Query q = em.createQuery(consulta);
            departamentoses = q.getResultList();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener departamentos ordenados:", e);
        } finally {
            return departamentoses;
        }
    }
    
    /**
     * Obtiene los datos del departamento por el nombre
     * @param nombre
     * @return
     */
    @Override
    public Departamentos obtenerPorNombre(String nombre){
        Departamentos d = null;
        try {
            Query q = em.createNamedQuery("Departamentos.findByDescripcion");
            q.setParameter("descripcion", nombre);
            d = (Departamentos) q.getSingleResult();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener departamento por nombre", e);
        } finally {
            return d;
        }
    }
    
    @Override
    public Departamentos obtenerPorCdgTerritorio(String cdgTerritorio){
        Departamentos d = null;
        try {
            Query q = em.createNamedQuery("Departamentos.findByCdgTerritorio");
            q.setParameter("cdgTerritorio", cdgTerritorio);
            d = (Departamentos) q.getSingleResult();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener departamento por nombre", e);
        } finally {
            return d;
        }
    }
}
