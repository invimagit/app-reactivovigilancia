/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoFriarh;
import co.gov.invima.entities.ReactivoFriarh01;
import co.gov.invima.entities.ReactivoFriarh02;
import co.gov.invima.entities.ReactivoFriarhPK;
import co.gov.invima.negocio.ReactivoVigilanciaBean;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author mgualdrond
 */
@Stateless
public class ReactivoFriarhFacade extends AbstractReactivoFacade<ReactivoFriarh> implements ReactivoFriarhFacadeLocal {

   @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager em;
   private static final Logger logEJBReactivo = Logger.getLogger(ReactivoVigilanciaBean.class.getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReactivoFriarhFacade() {
        super(ReactivoFriarh.class);
    }

    
    /*public List<ReactivoFriarhPK> findAll_id() {
        try {      
           List <ReactivoFriarhPK> lsFriarh = em.createQuery("SELECT r.reactivoFriarhPK FROM ReactivoFriarh r")
                   .getResultList();
           
          
           return lsFriarh;
           
        } catch (Exception e) {
                logEJBReactivo.log(Priority.ERROR, "obtenerListaReportesFRIARH, Error al obtener Lista de reportes FRIARH: ", e);
        }
        return null;
    }*/
    @Override
    public List<String> findAll_id() {
        List<String> retorno = new ArrayList<String>();
        try {
            String sqlString = "SELECT DISTINCT * FROM " +
                                "(SELECT friarh FROM reactivo_friarh " +
                                "UNION " +
                                "SELECT friarh FROM reactivo_monitoreo " +
                                ") a";
            Query q = em.createNativeQuery(sqlString);

            List<String> list = q.getResultList();

            for(String q1 : list){
                String temp = q1.toString();
                retorno.add(temp);
            }
                logEJBReactivo.log(Priority.INFO, "ReactivoEventosFacade, findAll_id retorno.size(): "+retorno.size() );
 
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "ReactivoEventosFacade, findAll_id CAtch: ", e);
        } finally {
            return retorno;
        }
    }
    
    
    
    
    
    @Override
    public ReactivoFriarh obtenerListaReportesFRIARHporID(String FRIARH){
        logEJBReactivo.log(Priority.INFO, "obtenerListaReportesFRIARHporID, FRIARH = "+FRIARH);
        ReactivoFriarh d = null;
        try {
            Query q = em.createNamedQuery("ReactivoFriarh.findByFriarh");
            q.setParameter("friarh", FRIARH);
            d = (ReactivoFriarh) q.getSingleResult();
            logEJBReactivo.log(Priority.INFO, "obtenerListaReportesFRIARHporID, FRIARH, d.getMedidasCorrectivas() "+d.getMedidasCorrectivas());
            
            Query q2 = em.createNamedQuery("ReactivoFriarh02.findByFRIARH");
            q2.setParameter("friarh", FRIARH);
            List<ReactivoFriarh02> ReactivoFriarh02_manual = q2.getResultList();
            d.setReactivoFriarh02List(ReactivoFriarh02_manual);
            
            Query q3 = em.createNamedQuery("ReactivoFriarh01.findByFRIARH");
            q3.setParameter("friarh", FRIARH);
            List<ReactivoFriarh01> ReactivoFriarh01_manual = q3.getResultList();
            d.setReactivoFriarh01List(ReactivoFriarh01_manual);
            
            
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener obtenerListaReportesFRIARHporID"+(". ReactivoFriarhFacade.obtenerListaReportesFRIARHporID(), " + e.getStackTrace()[0]));
        } finally {
            return d;
        }
    }
    
}
