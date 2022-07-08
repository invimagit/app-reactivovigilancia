/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.Ciudades;
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
public class CiudadesFacade extends AbstractReactivoFacade<Ciudades> implements CiudadesFacadeLocal {
    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager em;

    private final static Logger logEJBReactivo = Logger.getLogger(CiudadesFacade.class.getName());
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadesFacade() {
        super(Ciudades.class);
    }
    
    /**
     * Obtiene la lista de ciudades por departamento
     * @param cdgTerritorio: codigo del departamento
     * @return Lsitado de ciudades por departamento
     */
    @Override
    public List<Ciudades> obtenerCiudadPorDepartamento(String cdgTerritorio){
        List<Ciudades> ciudadeses = null;
        try {
            String consulta="SELECT c FROM Ciudades c WHERE c.ciudadesPK.cdgterritorio = :cdgterritorio"
                    +" ORDER BY c.codMun";
            Query q = em.createQuery(consulta);
            q.setParameter("cdgterritorio", cdgTerritorio);
            ciudadeses = q.getResultList();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener ciudades por dptos: ", e);
        } finally{
            return ciudadeses;
        }
    }
    
    /**
     * Obtiene la ciudad por departamento y ciudad
     * @param cdgTerritorio: Codigo del territorio
     * @param codMun: codigo dle municipio
     * @return Objeto ciudad
     */
    @Override
    public Ciudades obtenerCiudadPorDptoYCiudad(String cdgTerritorio,String codMun){
        Ciudades c = null;
        try {
            String  consulta ="SELECT c FROM Ciudades c WHERE c.ciudadesPK.cdgterritorio = :cdgterritorio "
                    + "and c.codMun = :codMun";
            Query q = em.createQuery(consulta);
            q.setParameter("cdgterritorio", cdgTerritorio);
            q.setParameter("codMun", codMun);
            c = (Ciudades) q.getSingleResult();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener ciudad: ", e);
        } finally {
            return c;
        }
    }
    
    @Override
    public Ciudades obtenerCiudadPorCodMun(String codMun){
        Ciudades c = null;
        try {
            String  consulta ="SELECT c FROM Ciudades c WHERE c.codMun = :codMun";
            Query q = em.createQuery(consulta);
            q.setParameter("codMun", codMun);
            c = (Ciudades) q.getSingleResult();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener ciudad: ", e);
        } finally {
            return c;
        }
    }
    
    /**
     * Obtener la ciudad por nombre y codigo de departamento
     * @param cdgterritorio: codigo del departamento
     * @param ciudad: nombre de la ciudad
     * @return Datos d ela ciudad
     */
    @Override
    public Ciudades obtenerCiudadPorDptoYNombre(String cdgterritorio,String ciudad){
        Ciudades c = null;
        try {
            String consulta = "SELECT c FROM Ciudades c WHERE c.ciudadesPK.cdgterritorio = :cdgterritorio "
                    + "and c.ciudadesPK.ciudad = :ciudad";
            Query q = em.createQuery(consulta);
            q.setParameter("cdgterritorio", cdgterritorio);
            q.setParameter("ciudad", ciudad);
            c = (Ciudades) q.getSingleResult();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error a obtener ciudad por nombre", e);
        } finally {
            return c;
        }
    }
}
