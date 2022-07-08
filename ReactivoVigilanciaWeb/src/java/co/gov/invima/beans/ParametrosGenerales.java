/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.beans;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Priority;

/**
 *
 * @author jgutierrezme
 */

@ViewScoped
@ManagedBean(name="beanParametrosGenerales")
public class ParametrosGenerales implements Serializable  
{
    private final static Logger logBeanWebReactivo = Logger.getLogger(ActivarBean.class.getName());
    
    private String direccionServidor;
    
    @PostConstruct
    public void init()
    {
        //HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String direccionServer = "";
        Map<String, String> mapa = null;
        
        //InetAddress IP;
        try 
        {
            HttpServletRequest extRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            //Se obtienen los atributos del request del contexto para armar la URL
            //String esquema = extRequest.getScheme();
            direccionServer = extRequest.getServerName();
            //IP = InetAddress.getLocalHost();
            //direccionServer = IP.getHostAddress();
            //mapa = getRequestHeadersInMap(request);
            //logBeanWebReactivo.info  ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //logBeanWebReactivo.info  ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //logBeanWebReactivo.info  ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //logBeanWebReactivo.info  (mapa);
            //direccionServer = (String)mapa.get("Host");
            //this.direccionServidor = direccionServer.substring(0,direccionServer.indexOf(":"));
            this.direccionServidor = direccionServer;
            //logBeanWebReactivo.info  ("DIRECCION SERVIDOR FISICO = " + this.direccionServidor);
            //logBeanWebReactivo.info  ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //logBeanWebReactivo.info  ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            //logBeanWebReactivo.info  ("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        } 
        
        catch (Exception ex) 
        {
            Logger.getLogger(ParametrosGenerales.class.getName()).log(Priority.ERROR, null, ex);
        }
        //logBeanWebReactivo.info ("------------------------------------------");
        //logBeanWebReactivo.info ("DIRECCIÓN DEL SERVIDOR = " + this.direccionServidor);
        //logBeanWebReactivo.info ("------------------------------------------");
    }

    /**
     * @return the direccionServidor
     */
    
    private Map<String, String> getRequestHeadersInMap(HttpServletRequest request) {

        Map<String, String> result = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            result.put(key, value);
        }

        return result;
    }
    
    public String obtenerDireccionServidor ()
    {
        String direccionCompleta = "";
        try
        {
            HttpServletRequest extRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            //Se obtienen los atributos del request del contexto para armar la URL
            String esquema = extRequest.getScheme();
            String servidor = extRequest.getServerName();
            int puerto = extRequest.getServerPort();
            //String rutaOrigen = extRequest.getContextPath();
            //String rutaServlet = extRequest.getServletPath();

            if (puerto == 80)
            {
                //logBeanWebReactivo.info ("Usando redireccionamiento por el 80");
                direccionCompleta = esquema + "://" + servidor +  "/";
            }
            else
            {
                //logBeanWebReactivo.info ("Usando redireccionamiento por el puerto " + puerto);
                direccionCompleta = esquema + "://" + servidor + ":" + puerto + "/";
            }
            
            //logBeanWebReactivo.info ("Contexto = " + direccionCompleta);
        
        }
        
        catch (Exception error)
        {
            
            return ("http://localhost:8080/");
        }
        
        return (direccionCompleta);
    }
    
    public String getDireccionServidor() {
        return direccionServidor;
    }

    /**
     * @param direccionServidor the direccionServidor to set
     */
    public void setDireccionServidor(String direccionServidor) {
        this.direccionServidor = direccionServidor;
    }
    
    
    
}
