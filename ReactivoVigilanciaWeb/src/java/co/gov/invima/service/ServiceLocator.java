/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.service;

import co.gov.invima.negocio.ConsultasReactivoRemote;
import co.gov.invima.negocio.ReactivoVigilanciaBeanLocal;
import co.gov.invima.negocio.ServicioReporteCeroRemote;
import co.gov.invima.negocio.ServicioReporteTrimestralRemote;
import co.gov.invima.tareas.TemporizaLocal;
import co.gov.invima.util.PropertiesReader;
import java.io.Serializable;
import java.util.Properties;
import org.apache.log4j.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.Priority;

/**
 *
 * @author mgualdrond
 */
public class ServiceLocator implements Serializable {

    private final static Logger logBeanWebReactivo = Logger.getLogger(ServiceLocator.class.getName());
    private InitialContext contexto;
    private final Properties props = new Properties();

    public ServiceLocator() {
//        PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
//        String maquina = pr.getProperty("maquina");
//        String userEJB = pr.getProperty("userEJB");
//        String passwordEJB = pr.getProperty("passwordEJB");
//        //props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
//        props.put(Context.PROVIDER_URL, "jnp://localhost:1099");
//        props.put(Context.SECURITY_PRINCIPAL, userEJB);
//        props.put(Context.SECURITY_CREDENTIALS, passwordEJB);
//        props.put("jboss.naming.client.ejb.context", true);
        try {
            //contexto = new InitialContext(props);
            contexto = new InitialContext();

        } catch (NamingException ex) {
            Logger.getLogger(ServiceLocator.class.getName()).log(Priority.ERROR, null, ex);
        }
    }

    /**
     * Obtiene la instancia para la conexión con el EJB local de
     * reactivovigilancia
     *
     * @return
     */
    public ReactivoVigilanciaBeanLocal getEJBReactivo() {
        ReactivoVigilanciaBeanLocal remoto = null;
        PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
        String EJBRemoto = pr.getProperty("EJBLocal");
        try {
            remoto = (ReactivoVigilanciaBeanLocal) contexto.lookup("java:global/" + EJBRemoto);
        } catch (NamingException e) {
            Logger.getLogger(ServiceLocator.class.getName()).log(Priority.ERROR, null, e);
        } finally {
            return remoto;
        }
    }
    //*******************************************************************
    //*******************************************************************
    //*******************************************************************
    public ConsultasReactivoRemote getEJBReportesReactivo() 
    {
        String rutaJNDI = "";
        ConsultasReactivoRemote remoto = null;
        PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
        String EJBRemoto = pr.getProperty("ServicioReportesReactivoEJB");
        try {
            rutaJNDI = "java:global/" + EJBRemoto;
            //logBeanWebReactivo.info ("JNDI REPORTE = " + rutaJNDI);
            remoto = (ConsultasReactivoRemote) contexto.lookup(rutaJNDI);
            //logBeanWebReactivo.info ("Estado del bean = " + remoto);
            
        } catch (NamingException e) {
            //logBeanWebReactivo.info ("Error en el lookup al bean " + e.getExplanation());
            e.printStackTrace();
            Logger.getLogger(ServiceLocator.class.getName()).log(Priority.ERROR, null, e);
        } finally {
            return remoto;
        }
    }
    //*******************************************************************
    //*******************************************************************
    //*******************************************************************
    public TemporizaLocal gtEJBTemporiza() {
        TemporizaLocal remoto = null;
        PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
        String EJBRemoto = pr.getProperty("EJBTemporiza");
        try {
            remoto = (TemporizaLocal) contexto.lookup("java:global/" + EJBRemoto);
        } catch (NamingException e) {
            Logger.getLogger(ServiceLocator.class.getName()).log(Priority.ERROR, null, e);
        } finally {
            return remoto;
        }
    }
    //*******************************************************************
    //*******************************************************************
    //*******************************************************************
    public ServicioReporteCeroRemote getEjbRemotoReactivoReporteCero()
    {
        ServicioReporteCeroRemote remoto =null;
        PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
        String servicioEJBReactivoReporteCero = pr.getProperty("ServicioEJBReactivoReporteCero");
        //logBeanWebReactivo.info ("***************************************************************");
        //logBeanWebReactivo.info ("***************************************************************");
        //logBeanWebReactivo.info ("EJB REMOTO DE REACTIVO REPORTE EN CERO = " + servicioEJBReactivoReporteCero);
        //logBeanWebReactivo.info ("***************************************************************");
        //logBeanWebReactivo.info ("***************************************************************");
        try {
            remoto = (ServicioReporteCeroRemote) contexto.lookup("java:global/" +servicioEJBReactivoReporteCero);
        } catch (NamingException ex) {
            Logger.getLogger(ServiceLocator.class.getName()).log(Priority.ERROR, null, ex);
        }
        return remoto;
    }    
    //*******************************************************************
    //*******************************************************************
    //*******************************************************************
    public ServicioReporteTrimestralRemote getEjbRemotoReactivoReporteTrimestral()
    {
        ServicioReporteTrimestralRemote remoto =null;
        PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
        String servicioEJBReporteReactivoTrimestral = pr.getProperty("ServicioEJBReactivoReporteTrimestral");
        //logBeanWebReactivo.info ("***************************************************************");
        //logBeanWebReactivo.info ("***************************************************************");
        //logBeanWebReactivo.info ("EJB REMOTO DE REACTIVO REPORTE TRIMESTRAL CON EVENTO = " + servicioEJBReporteReactivoTrimestral);
        //logBeanWebReactivo.info ("***************************************************************");
        //logBeanWebReactivo.info ("***************************************************************");
        try {
            remoto = (ServicioReporteTrimestralRemote) contexto.lookup("java:global/" +servicioEJBReporteReactivoTrimestral);
        } catch (NamingException ex) {
            Logger.getLogger(ServiceLocator.class.getName()).log(Priority.ERROR, null, ex);
        }
        return remoto;
    }    
    //*******************************************************************
    //*******************************************************************
    //*******************************************************************
    //*******************************************************************
    //*******************************************************************
    //*******************************************************************
    //*******************************************************************
    //*******************************************************************
    //*******************************************************************
}
