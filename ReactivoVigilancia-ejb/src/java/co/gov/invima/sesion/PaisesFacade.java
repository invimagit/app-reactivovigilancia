/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.Paises;
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
public class PaisesFacade extends AbstractReactivoFacade<Paises> implements PaisesFacadeLocal {

    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager em;

    private final static  Logger logEJBReactivo = Logger.getLogger(PaisesFacade.class.getName());
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public PaisesFacade() {
        super(Paises.class);
    }
    
    /**
     * Obtiene la lista de paises ordenados por nombre
     * @return
     */
    @Override
    public List<Paises> obtenerPaisesOrdenado(){
        List<Paises> paiseses = null;
        try {
            String consulta = "SELECT p FROM Paises p ORDER BY p.pais";
            Query q = em.createQuery(consulta);
            paiseses = q.getResultList();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener Paises ordenados", e);
        } finally {
            return paiseses;
        }
    }

}
