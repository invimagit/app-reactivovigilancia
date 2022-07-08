/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.util;

/**
 *
 * @author jgutierrezme
 */
import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class FiltroSeguridadXSS_SQLI  implements Filter 
{
    private final Logger logBeanWebReactivo = Logger.getLogger(FiltroSeguridadXSS_SQLI.class);
    /*********************************************/
    /*********************************************/
    /*********************************************/
    private FilterConfig filterConfig;
    private StringBuffer errorMessage;
    /*********************************************/
    private String nombreUsuario;
    private String idUsuario;
    /*********************************************/
    /*********************************************/
    /*********************************************/
    /*********************************************/
    /**
     * @param filterConfig*
     * @throws javax.servlet.ServletException******************************************/
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.setFilterConfig(filterConfig);
        //logBeanWebReactivo.info  ("Arrancando el filtro de seguridad para la aplicación Web de ReactivoVigilanciaWeb en la clase " + this.getClass().getCanonicalName());
    }
	/*********************************************/
	/*********************************************/
    @Override
    public void destroy() {
        //logBeanWebReactivo.info  ("Destruyendo el el filtro de seguridad para la aplicación Web de ReactivoVigilanciaWeb en la clase " + this.getClass().getCanonicalName());
        this.setFilterConfig(null);
    }
    /*********************************************/
    /*********************************************/
    /**
     * @param request*
     * @param response
     * @param chain
     * @throws java.io.IOException*
     * @throws javax.servlet.ServletException
    */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
    {
                HttpServletRequest peticion = (HttpServletRequest)request;
                String errorEncontrado = "";
                //********************************************************
                //********************************************************
                    this.nombreUsuario = "anonimo";
                    this.idUsuario = "1";
                //********************************************************
                //********************************************************
                                if (!validateParameters(request)) 
    		{
                                                errorEncontrado = "Se detecto error de detección XSS o SQL Injection: " + errorMessage.toString();
                                                logBeanWebReactivo.info ("request.toString() = "+request.toString());
                                                logBeanWebReactivo.info ("*********************************************");
                                                logBeanWebReactivo.info ("ERROR DE SEGURIDAD");
    			logBeanWebReactivo.info  (errorEncontrado);
                                                logBeanWebReactivo.info ("*********************************************");
                                                logBeanWebReactivo.info ("*********************************************");
	    		request.setAttribute("mensajeErrorS", errorEncontrado);
                                                //*********************************************************
                                                //*********************************************************
                                                //*********************************************************
                                                logBeanWebReactivo.info ("Error de Seguridad del Sistema de Información");
                                                //*********************************************************
                                                //*********************************************************
	    		RequestDispatcher dispatcher = request.getRequestDispatcher("/ControladorErrores?error=sqlinjection");
	    		dispatcher.forward(request, response);
	    		errorMessage = null;
    		}
    		else
    		{
        		chain.doFilter(request, response);
    		}
	}
    /*********************************************/
    /*********************************************/
    /*********************************************/
    private boolean validateParameters(ServletRequest request) 
    {
        return true;
        /*
    	Enumeration<String> params = request.getParameterNames();
    	String paramName;
    	
    	while (params.hasMoreElements()) 
    	{
    		paramName = params.nextElement();
    		
    		if (searchReservedChars(request.getParameter(paramName), paramName)) 
    		{
    			logBeanWebReactivo.info  ("Se encontro error XSS");
    			return false;
    		}
    	}
    	
    	return true;*/
    }
    /*********************************************/
    /*********************************************/
    /*********************************************/
    /**
    *
    * @param value
    * @param paramName
    * @return
    *
    * This method search for the suspicious patterns and constructs an
    * error message comprising of the pattern.
    *
    * paramName to be used if specific fields are to be skipped
    */
    private boolean searchReservedChars(String value, String paramName) 
    {
    		//logBeanWebReactivo.info  ("Analizando expresión contra patrón");
    		value=value.toLowerCase();
    		
    		String patron = 
                                "[\\w]*((%27)|(‘))\\s*((%6F)|o|(%4F))((%72)|r|(%52))" + 
                                "|[\\w]*((%27)|(‘))\\s*((%61)|a|(%41))((%6E)|n|(%4E))((%64)|d|(%44))" +
                                "|(((%3E)|>|(%3C)|<))" +
                                "|(((%3E)|>|(%3C)|<)+.*[://.=/(/);’\"&#-]+.*)"+
                                "|(.*[://.=/(/);’\"&#-]+.*((%3E)|>|(%3C)|<)+)"+
                                "|(((%3C)|<)((%69)|i|(%49))((%6D)|m|(%4D))((%67)|g|(%47))[^\\n]+((%3E)|>))" +
                                "|(update|delete|create|drop|grant|revoke|select|truncate|partial-response)+";
                                //"|(insert|update|delete|select|create|drop|alter|grant|revoke)+";
    	    	    
		Pattern xsspattern = Pattern.compile(patron);
                                //logBeanWebReactivo.info  ("Valor enviado a la expresion: "  + value);
		Matcher match = xsspattern.matcher(value);
    
		    if(match.find()) 
		    {
		    	    logBeanWebReactivo.info  ("Hubo error de coincidencia: " + value);
			    errorMessage = new StringBuffer();
			    String charstr = value.substring(match.start(), match.end());
			    //charstr = charstr.replaceAll(">", "&gt;");
			    //charstr = charstr.replaceAll("<", "&lt;");
			    errorMessage.append("Entrada Sospechosa XSS/SQL Injection [").append(charstr).append("]. Emplee la tecla Retroceder del Navegador para regresar y resolver este problema.");
			    return true;
		    }
    
		    return false;
    }    
    
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
        
    /**
     * @return the usuarioSesion
     */
    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the errorMessage
     */
    public StringBuffer getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(StringBuffer errorMessage) {
        this.errorMessage = errorMessage;
    }

    /*********************************************/
    /*********************************************/
    /*********************************************/
    /*********************************************/
    /*********************************************/
    /*********************************************/
}
