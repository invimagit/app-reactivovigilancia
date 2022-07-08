/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.util;

 
import javax.servlet.*; 
import javax.servlet.annotation.WebFilter; 
import javax.servlet.http.HttpServletResponse; 
import java.io.IOException; 
import org.apache.log4j.Logger;
/**
 *
 * @author jgutierrezme
 */
@WebFilter(filterName = "XFrameOptionsFilter", urlPatterns = {"/faces/*", "/faces/pages/*"}) 
public class XFrameOptionsFilter implements Filter 
{ 
    //************************************************************************
    //************************************************************************
    //************************************************************************
    private final Logger logBeanWebReactivo = Logger.getLogger(XFrameOptionsFilter.class);
    //************************************************************************
    //************************************************************************
    @Override 
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
                         FilterChain filterChain) throws IOException, ServletException 
    { 
        //*********************************************************
        //*********************************************************
        //logBeanWebReactivo.info("X-Frame-Options header added to response"); 
        //*********************************************************
        //*********************************************************
        HttpServletResponse response = (HttpServletResponse) servletResponse; 
        //response.addHeader("X-Frame-Options", "DENY"); 
        response.addHeader("X-Content-Type-Options", "nosniff"); 
        //*********************************************************
        //*********************************************************
        //logBeanWebReactivo.info ("Denegando acceso para ataques ClickJacking: " + response.getHeader("X-Frame-Options"));
        //logBeanWebReactivo.info ("Configurando X-Content-Type-Options:  " + response.getHeader("X-Content-Type-Options"));
        filterChain.doFilter(servletRequest, response); 
        //*********************************************************
        //*********************************************************
    } 
    //************************************************************************
    //************************************************************************
    //************************************************************************
    @Override 
    public void init(FilterConfig filterConfig) throws ServletException 
    {
        logBeanWebReactivo.info ("***************************************************");
        logBeanWebReactivo.info ("***************************************************");
        logBeanWebReactivo.info ("ACTIVANDO FILTRO DE SEGURIDAD CONTRA ClickJacking");
        logBeanWebReactivo.info ("Nombre del filtro: " + filterConfig.getFilterName());
        logBeanWebReactivo.info ("***************************************************");
        logBeanWebReactivo.info ("***************************************************");
    } 
    //************************************************************************
    //************************************************************************
    //************************************************************************
    @Override 
    public void destroy() 
    {
        logBeanWebReactivo.info ("***************************************************");
        logBeanWebReactivo.info ("***************************************************");
        logBeanWebReactivo.info ("DESACTIVANDO FILTRO DE SEGURIDAD CONTRA ClickJacking");
        logBeanWebReactivo.info ("***************************************************");
        logBeanWebReactivo.info ("***************************************************");
    } 
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
    //************************************************************************
}