/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoRedPersona;
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
public class ReactivoRedPersonaFacade extends AbstractReactivoFacade<ReactivoRedPersona> implements ReactivoRedPersonaFacadeLocal {

    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager em;

    private static final Logger logEJBReactivo = Logger.getLogger(ReactivoRedPersonaFacade.class.getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReactivoRedPersonaFacade() {
        super(ReactivoRedPersona.class);
    }

    /**
     * Obtiene la lista de personas inscritas a lared por diferente compañia
     *
     * @param cedulaSolicitante
     * @return
     */
    @Override
    public List<ReactivoRedPersona> obtenerPersonaRedPorDocumento(String cedulaSolicitante) {
        List<ReactivoRedPersona> persona = null;
        try {
            Query q = em.createNamedQuery("ReactivoRedPersona.findByCedulaSolicitante");
            q.setParameter("cedulaSolicitante", cedulaSolicitante);
            persona = q.getResultList();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Eror al obtener Solicitante", e);
        } finally {
            return persona;
        }
    }

    /**
     * Obtiene la persona que ya este inscrita en una entidad
     * @param cedulaSolicitante
     * @param nit
     * @return
     */
    //Ajuste
    @Override
    public ReactivoRedPersona obtenerPersonaPorDocumentoYEntidad(String cedulaSolicitante, String nit) 
    {
        ReactivoRedPersona persona = null;
        ReactivoRedPersona personaTemp = null;
        //***********************************************************
        //***********************************************************
        //***********************************************************
        //***********************************************************
        
        try 
        {
                //*************************************************************************
                //*************************************************************************
                logEJBReactivo.info ("*******************************************");
                logEJBReactivo.info ("*******************************************");
                logEJBReactivo.info ("Cédula Solicitante:  " + cedulaSolicitante);
                logEJBReactivo.info ("nit empresa = " + nit);
                logEJBReactivo.info ("*******************************************");
                logEJBReactivo.info ("*******************************************");
                //*************************************************************************
                //*************************************************************************
                String consulta = "SELECT r FROM ReactivoRedPersona r WHERE r.cedulaSolicitante = :cedulaSolicitante and"
                    +" r.idRed.nit=:nit";
                //*************************************************************************
                //*************************************************************************
                Query q = em.createQuery(consulta);
                q.setParameter("cedulaSolicitante", cedulaSolicitante);
                q.setParameter("nit", nit);
                persona =(ReactivoRedPersona) q.getSingleResult();
                
                logEJBReactivo.info ("PERSONA UBICADA: " + persona);
                logEJBReactivo.info ("*******************************************");
                logEJBReactivo.info ("*******************************************");
                //*************************************************************************
                //*************************************************************************
                //*************************************************************************
        } 
        
        catch (Exception e) 
        {
            logEJBReactivo.log(Priority.ERROR, "Error al consultar por documento y entidad", e);
            e.printStackTrace();
            return (personaTemp);
        } 
        
        finally 
        {
            if (persona == null)
            {
                persona = personaTemp;
            }
        }
        
        return (persona);
    }
    
    /*
    @Override
    public ReactivoRedPersona obtenerPersonaPorDocumentoYEntidad(String cedulaSolicitante, String nit) {
        ReactivoRedPersona persona = null;
        try {
            String consulta = "SELECT r FROM ReactivoRedPersona r WHERE r.cedulaSolicitante = :cedulaSolicitante and"
                    +" r.idRed.nit=:nit";
            Query q = em.createQuery(consulta);
            q.setParameter("cedulaSolicitante", cedulaSolicitante);
            q.setParameter("nit", nit);
            persona =(ReactivoRedPersona) q.getSingleResult();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al consultar por documento y entidad", e);
        } finally {
            return persona;
        }
    }
    */

    /**
     * Obtiene el maximo ID para el identificador de Red Persona
     * @return El máximo valor del ID de la tabla ReactivoRedPersona
     */
    @Override
    public Integer obtenerId(){
        Integer max = 0;
        try {
            String consulta = "SELECT MAX(r.id) FROM reactivo_red_persona r";
            Query q  = em.createNativeQuery(consulta);
            max = (Integer) q.getSingleResult();
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener maximo: ", e);
        } finally {
            return max;
        }
    }
}
