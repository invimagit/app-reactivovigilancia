/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoEventos;
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
public class ReactivoEventosFacade extends AbstractReactivoFacade<ReactivoEventos> implements ReactivoEventosFacadeLocal {
    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager em;
    private final static Logger logEJBReactivo = Logger.getLogger(ReactivoEventosFacade.class.getName());
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReactivoEventosFacade() {
        super(ReactivoEventos.class);
    }
    
    public List<String> findAll_id() {
        List<String> retorno = new ArrayList<String>();
        try {
            String sqlString = "SELECT DISTINCT * FROM " +
                                "(SELECT reporte FROM reactivo_eventos " +
                                "UNION " +
                                "SELECT reporte FROM reactivo_institucion " +
                                "UNION " +
                                "SELECT reporte FROM reactivo_paciente " +
                                "UNION " +
                                "SELECT reporte FROM reactivo_producto " +
                                "UNION " +
                                "SELECT reporte FROM reactivo_gestionrealizada " +
                                "UNION " +
                                "SELECT reporte from reactivo_reportante " +
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
    
}
