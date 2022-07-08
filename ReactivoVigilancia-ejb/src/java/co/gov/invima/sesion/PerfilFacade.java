/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.Perfil;
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
public class PerfilFacade extends AbstractReactivoFacade<Perfil> implements PerfilFacadeLocal {
    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    public EntityManager em;
    
    private final static Logger logEJBReactivo = Logger.getLogger(CiudadesFacade.class.getName());
    
    public PerfilFacade() {
        super(Perfil.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Obtiene el perfil por nombre
     * @param nmbPerfil: nombre del perfil
     * @return Objeto con el contenido del perfil
     */
    @Override
    public Perfil obtenerPErfilPorNombre(String nmbPerfil){
        Perfil p = null;
        try {
            String consulta = "SELECT p FROM Perfil p WHERE UPPER(p.nmbPerfil) = :nmbPerfil";
            Query q = em.createQuery(consulta);
            q.setParameter("nmbPerfil", nmbPerfil);
            p = (Perfil) q.getSingleResult();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener Perfil: ", e);
        } finally {
            return p;
        }
    }
}
