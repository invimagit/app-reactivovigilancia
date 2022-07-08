/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.util;

/************************************************************************/
/************************************************************************/
/************************************************************************/
/************************************************************************/
/************************************************************************/
/************************************************************************/
import co.gov.invima.VO.UsuariosVO;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
/**
 *
 * @author Ingeniero Jaime Alberto Gutiérrez Mejía
 * Filtro de Permisos de Seguridad
 */
/************************************************************************/
/************************************************************************/
/************************************************************************/
public class FiltroPermisosRoles implements Filter 
{
    private final Logger logBeanWebReactivo = Logger.getLogger(FiltroPermisosRoles.class);
    //****************************************************************
    //****************************************************************
    //****************************************************************
    private FilterConfig configuration;
    //****************************************************************
    //****************************************************************
    //****************************************************************
    @Override
    public void init(FilterConfig filterConfig) throws ServletException 
    {
        //logBeanWebReactivo.info  ("*****************************************************");
        //logBeanWebReactivo.info  ("Arrancando filtro de seguridad de autenticación para " + this.getClass().getCanonicalName());
        this.configuration = filterConfig;
        //logBeanWebReactivo.info  ("*****************************************************");
    }
    //****************************************************************
    //****************************************************************
    //****************************************************************
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
    {
        //**********************************************************************************
        //**********************************************************************************
        UsuariosVO sesionUsuario = null;
        sesionUsuario = (UsuariosVO)((HttpServletRequest) request).getSession().getAttribute("usuario");
        //**********************************************************************************
        //**********************************************************************************
        //logBeanWebReactivo.info  ("Usuario en Sesión de ReactivoVigilanciaWeb: " + sesionUsuario);
        if (sesionUsuario != null)
        {
            //logBeanWebReactivo.info  ("*****************************************************");
            //logBeanWebReactivo.info  ("*****************************************************");
            //logBeanWebReactivo.info  ("usuario: " + sesionUsuario.getUsuario());
            //logBeanWebReactivo.info  ("rol del usuario: " + sesionUsuario.getIdRol());
            //logBeanWebReactivo.info  ("*****************************************************");
            //logBeanWebReactivo.info  ("*****************************************************");
        }
        //**********************************************************************************
        //**********************************************************************************
        if (sesionUsuario == null)
        {
             //logBeanWebReactivo.info  ("El usuario no se encuentra en sesión de ReactivoVigilancia");
            ((HttpServletResponse) response).sendRedirect("/ReactivoVigilanciaWeb/faces/paginaNoAutorizada.xhtml");
        } 
        else 
        {
            //**************************************************************************************
            //**************************************************************************************
            /*
            //logBeanWebReactivo.info  ("El usuario se encuentra en sesión");
            if(!((UserBean)((HttpServletRequest) request).getSession().getAttribute(BeanAutenticacion.USER_KEY)).getRole().equals("SuperAdministrador"))
            {
                //logBeanWebReactivo.info  ("El usuario tiene un rol diferente de superadministrador para acceder a sus recursos");
                ((HttpServletResponse) response).sendRedirect("/MVCTGratuita/errores/paginaNoAutorizadaR.xhtml");
            }
            else
            {
                //logBeanWebReactivo.info  ("El recurso es accesible por este rol del usuario ");
                chain.doFilter(request, response);
            }
            */
            //logBeanWebReactivo.info  ("El recurso es accesible por este rol del usuario ");
            chain.doFilter(request, response);
            //**************************************************************************************
            //**************************************************************************************
        }
        //logBeanWebReactivo.info  ("*****************************************************");
        //logBeanWebReactivo.info  ("*****************************************************");
        //logBeanWebReactivo.info  ("*****************************************************");
    }
    //****************************************************************
    //****************************************************************
    //****************************************************************
    @Override
    public void destroy() 
    {
        //logBeanWebReactivo.info  ("*****************************************************");
        //logBeanWebReactivo.info  ("*****************************************************");
        //logBeanWebReactivo.info  ("Destruyendo filtro de seguridad de autenticación de " + this.getClass().getCanonicalName());
        configuration = null;
        //logBeanWebReactivo.info  ("*****************************************************");
        //logBeanWebReactivo.info  ("*****************************************************");
    }
    //****************************************************************
    //****************************************************************
    //****************************************************************
    public FilterConfig getConfiguration() {
        return configuration;
    }

    public void setConfiguration(FilterConfig configuration) {
        this.configuration = configuration;
    }
    //****************************************************************
    //****************************************************************
    //****************************************************************
    //****************************************************************
    //****************************************************************
    //****************************************************************
}
/************************************************************************/
/************************************************************************/
/************************************************************************/
/************************************************************************/
/************************************************************************/
/************************************************************************/