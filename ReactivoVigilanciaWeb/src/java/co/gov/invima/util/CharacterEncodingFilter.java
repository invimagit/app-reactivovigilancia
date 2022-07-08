/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Jaime Alberto Gutiérrez Mejía
 */
public class CharacterEncodingFilter  implements Filter 
{
    
    private final Logger logBeanWebReactivo = Logger.getLogger(CharacterEncodingFilter.class);
 
    protected String encoding = null;
    protected FilterConfig filterConfig = null;
    protected boolean ignore = true;
 
    /**
     * Take this filter out of service.
     */
    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;
    }
 
    /**
     * Select and set (if specified) the character encoding to be used to
     * interpret request parameters for this request.
     * @param request The servlet request we are processing
     * @param result The servlet response we are creating
     * @param chain The filter chain we are processing
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
 throws IOException, ServletException 
    {
        /*
        // Conditionally select and set the character encoding to be used
        if (ignore || (request.getCharacterEncoding() == null)) {
            String encoding = selectEncoding(request);
            if (encoding != null)
                request.setCharacterEncoding(encoding);
        }
        */
 
        //logBeanWebReactivo.info ("----------------------------------------------");
        //*********************************************************
        //*********************************************************
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //*********************************************************
        //*********************************************************
        request.setCharacterEncoding("UTF-8");
        //logBeanWebReactivo.info ("CODIFICACION DEL RESPONSE: "+ response.getCharacterEncoding());
        //logBeanWebReactivo.info ("CODIFICACION DEL REQUEST: "+ request.getCharacterEncoding());
        //logBeanWebReactivo.info ("----------------------------------------------");
        chain.doFilter(request, response);
    }
 
    /**
     * Place this filter into service.
     * @param filterConfig The filter configuration object
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
 this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
        String value = filterConfig.getInitParameter("ignore");
        if (value == null)
            this.ignore = true;
        else if (value.equalsIgnoreCase("true"))
            this.ignore = true;
        else if (value.equalsIgnoreCase("yes"))
            this.ignore = true;
        else
            this.ignore = false;
    }
 
    /**
     * Select an appropriate character encoding to be used, based on the
     * characteristics of the current request and/or filter initialization
     * parameters.  If no character encoding should be set, return
     * <code>null</code>.
     * * The default implementation unconditionally returns the value configured
     * by the <strong>encoding</strong> initialization parameter for this
     * filter.
     *
     * @param request The servlet request we are processing
     */
    protected String selectEncoding(ServletRequest request) {
        return (this.encoding);
    }
    
}
