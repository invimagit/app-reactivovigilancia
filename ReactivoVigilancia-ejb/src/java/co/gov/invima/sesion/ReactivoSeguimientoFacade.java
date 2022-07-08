/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoSeguimiento;
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
 * @author jonat
 */
@Stateless
public class ReactivoSeguimientoFacade extends AbstractFacade<ReactivoSeguimiento> {

    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager em;
    private static final Logger logEJBReactivo = Logger.getLogger(ReactivoSeguimientoFacade.class.getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReactivoSeguimientoFacade() {
        super(ReactivoSeguimiento.class);
    }
    
    public long get_siguiente_id(){
        long retorno = 1;
        try{
            String sqlString = "SELECT MAX(id) FROM reactivo_seguimiento";
            Query q = em.createNativeQuery(sqlString);
            String id_maximo = q.getSingleResult().toString();
            retorno = Long.parseLong(id_maximo)+1;
        }
        catch(Exception e){
            logEJBReactivo.log(Priority.ERROR, "ReactivoSeguimientoFacade, get_siguiente_id CAtch: ", e);
        }
        return retorno;
    }
    
    public void insertar_observacion(ReactivoSeguimiento x){
        List<String> retorno = new ArrayList<String>();
        try {
            Long id = get_siguiente_id();
            x.setId(id);
            this.create(x);
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "ReactivoSeguimientoFacade, insertar_observacion CAtch: ", e);
        } 
    }
    
    public List<ReactivoSeguimiento> buscar_seguimientos_old (String categoria, String idReporte){
        List<ReactivoSeguimiento> retorno = new ArrayList();
        try {
            logEJBReactivo.log(Priority.INFO, "buscar_seguimientos, categoria = "+categoria+", idReporte = "+idReporte);
            Query q = em.createNamedQuery("ReactivoSeguimiento.findByCategoriaReporte");
            q.setParameter("categoria", categoria);
            q.setParameter("reporte", idReporte);
            retorno = q.getResultList();
        }
        catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "ReactivoSeguimientoFacade, buscar_seguimientos CAtch: ", e);
        } 
        return retorno;
    }
    
    public List<ReactivoSeguimiento> buscar_seguimientos (String categoria, String idReporte){
    List<ReactivoSeguimiento> retorno = new ArrayList<ReactivoSeguimiento>();
        try {
            String sqlString = "SELECT * FROM reactivo_seguimiento WHERE categoria = '"+categoria+"' AND id_reporte = '"+idReporte+"'";
            Query q = em.createNativeQuery(sqlString);

            List<Object[]> list = q.getResultList();

            for(Object[] q1 : list){
                ReactivoSeguimiento  x = new ReactivoSeguimiento();
                
                x.setDate_string((q1[0].toString()));
                x.setUsuario(q1[1].toString());
                x.setObservacion(q1[2].toString());
                retorno.add(x);
            }
            logEJBReactivo.log(Priority.INFO, "buscar_seguimientos ReactivoSeguimiento.size(): "+retorno.size() );
            
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "buscar_seguimientos CAtch: ", e);
        } finally {
            return retorno;
        }
    }
    
}
