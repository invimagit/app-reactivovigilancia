/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.sesion;

import co.gov.invima.entities.ReactivoMonitoreo;
import co.gov.invima.negocio.ReactivoVigilanciaBean;
import java.util.Date;
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
public class ReactivoMonitoreoFacade extends AbstractFacade<ReactivoMonitoreo> {

    @PersistenceContext(unitName = "ReactivoVigilancia-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReactivoMonitoreoFacade() {
        super(ReactivoMonitoreo.class);
    }
    private static final Logger logEJBReactivo = Logger.getLogger(ReactivoMonitoreoFacade.class.getName());
    public List<String> findAll_id() {
        try {      
           List <String> lsFriarh = em.createQuery("SELECT r.friarh FROM ReactivoMonitoreo r")
                   .getResultList();
          
           return lsFriarh;
           
        } catch (Exception e) {
                logEJBReactivo.log(Priority.ERROR, "findAll_id, Error al obtener Lista de reportes FRIARH: ", e);
        }
        return null;
    }
    
    public ReactivoMonitoreo obtenerReactivoMonitoreoporID_no_funciona(String FRIARH){
        logEJBReactivo.log(Priority.INFO, "obtenerReactivoMonitoreoporID, FRIARH = "+FRIARH);
        ReactivoMonitoreo d = new ReactivoMonitoreo();
        try {
            
            /*Object o = em.createQuery("SELECT r FROM ReactivoMonitoreo r where r.friarh = '"+FRIARH+"'")
                   .getSingleResult();*/
            Query q = em.createNamedQuery("ReactivoMonitoreo.findByFriarh");
            q.setParameter("friarh", FRIARH);
            Object o =  q.getSingleResult();
            
            d = (ReactivoMonitoreo) o;
            
            
            /*
            Query q = em.createNamedQuery("ReactivoMonitoreo.findByFriarh");
            q.setParameter("friarh", FRIARH);
            d = (ReactivoMonitoreo) q.getSingleResult();*/
            logEJBReactivo.log(Priority.INFO, "obtenerReactivoMonitoreoporID, FRIARH, d.getDetalles() "+d.getDetalles());
            
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener obtenerListaReportesFRIARHporID", e);
            logEJBReactivo.log(Priority.ERROR, "Error al obtener obtenerListaReportesFRIARHporID"+ e.getMessage());
            logEJBReactivo.log(Priority.ERROR, "Error al obtener obtenerListaReportesFRIARHporID"+ e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            return d;
        }
    }
    public ReactivoMonitoreo obtenerReactivoMonitoreoporID(String FRIARH){
        logEJBReactivo.log(Priority.INFO, "obtenerReactivoMonitoreoporID, FRIARH = "+FRIARH);
        ReactivoMonitoreo d = new ReactivoMonitoreo();
        try {
            String sqlString = "Select  friarh,fecha_ingreso,agencia_sanitaria,detalles" +
                ",aplica,tipo,funcionario_monitoreo,pagina_web,internet,fuente from reactivo_monitoreo where friarh='"+FRIARH+"'";
            Query q = em.createNativeQuery(sqlString);
            //funcionarios = q.getResultList();
                        
            List<Object[]> list = q.getResultList();
            
            ReactivoMonitoreo  x = new ReactivoMonitoreo();
            x.setFriarh((list.get(0)[0].toString()));
            x.setFechaIngreso((Date)list.get(0)[1] );
            if(list.get(0)[2]!=null)
            {x.setAgenciaSanitaria((list.get(0)[2].toString()));}
            x.setDetalles((list.get(0)[3].toString()));
            x.setAplica((list.get(0)[4].toString()));
            x.setTipo((list.get(0)[5].toString()));
            x.setFuncionarioMonitoreo(Short.parseShort(list.get(0)[6].toString()));
            x.setPaginaWeb((list.get(0)[7].toString()));
            x.setInternet((list.get(0)[8].toString()));
            x.setFuente(Integer.parseInt(list.get(0)[9].toString()));
           
            d=x;

            logEJBReactivo.log(Priority.INFO, "obtenerReactivoMonitoreoporID, FRIARH, d.getDetalles() "+d.getDetalles());
            
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener obtenerListaReportesFRIARHporID", e);
            logEJBReactivo.log(Priority.ERROR, "Error al obtener obtenerListaReportesFRIARHporID"+ e.getMessage());
            logEJBReactivo.log(Priority.ERROR, "Error al obtener obtenerListaReportesFRIARHporID"+ e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            return d;
        }
    }
    
    
    public void actualizarReactivoMonitoreoporID(ReactivoMonitoreo reactivoMonitoreo){
        logEJBReactivo.log(Priority.INFO, "actualizarReactivoMonitoreoporID, FRIARH = "+reactivoMonitoreo.getFriarh());
        try {
            
            String sqlString="UPDATE reactivo_monitoreo SET "
                    + "agencia_sanitaria = '"+reactivoMonitoreo.getAgenciaSanitaria()
                    + "',detalles = '"+reactivoMonitoreo.getDetalles()
                    + "',aplica = '"+reactivoMonitoreo.getAplica()
                    + "',tipo = '"+reactivoMonitoreo.getTipo()
                    + "',funcionario_monitoreo = '"+reactivoMonitoreo.getFuncionarioMonitoreo()
                    + "',pagina_web = '"+reactivoMonitoreo.getFuncionarioMonitoreo()
                    + "',internet = '"+reactivoMonitoreo.getInternet()
                    + "',fuente = '"+reactivoMonitoreo.getFuente()
                    + "' WHERE friarh='"+reactivoMonitoreo.getFriarh()+"'";
            
            Query q = em.createNativeQuery(sqlString);
            //funcionarios = q.getResultList();
            q.executeUpdate();
                        
           

            logEJBReactivo.log(Priority.INFO, "actualizarReactivoMonitoreoporID, FIN ");
            
        } catch (Exception e) {
            logEJBReactivo.log(Priority.ERROR, "Error al obtener actualizarReactivoMonitoreoporID", e);
            logEJBReactivo.log(Priority.ERROR, "Error al obtener actualizarReactivoMonitoreoporID"+ e.getMessage());
            logEJBReactivo.log(Priority.ERROR, "Error al obtener actualizarReactivoMonitoreoporID"+ e.getLocalizedMessage());
            e.printStackTrace();
        } 
    }
    
}
