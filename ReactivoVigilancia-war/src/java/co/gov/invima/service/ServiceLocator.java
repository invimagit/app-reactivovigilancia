/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.service;

import co.gov.invima.negocio.ReactivoVigilanciaBeanLocal;
import co.gov.invima.util.PropertiesReader;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author mgualdrond
 */
public class ServiceLocator implements Serializable {

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
            Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Obtiene la instancia para la conexión con el EJB local de reactivovigilancia
     * @return
     */
    public ReactivoVigilanciaBeanLocal getEJBReactivo(){
        ReactivoVigilanciaBeanLocal remoto = null;
        PropertiesReader pr = new PropertiesReader("co.gov.invima.util.recursos");
        String EJBRemoto = pr.getProperty("EJBLocal");
        try {
            remoto = (ReactivoVigilanciaBeanLocal) contexto.lookup("java:global/"+EJBRemoto);
        } catch (NamingException e) {
            Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            return remoto;
        }
    }

}
