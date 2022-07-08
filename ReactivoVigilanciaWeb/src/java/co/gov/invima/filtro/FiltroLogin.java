/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.gov.invima.filtro;

import co.gov.invima.VO.MenusVO;
import co.gov.invima.VO.UsuariosVO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
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
 * @author mgualdrond
 */
public class FiltroLogin implements Filter {

    private final static Logger logBeanWebReactivo = Logger.getLogger(FiltroLogin.class.getName());
    private static final boolean debug = true;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FiltroLogin() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            logBeanWebReactivo("FiltroLogin:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.

        // For example, a logging filter might logBeanWebReactivo items on the request object,
        // such as the parameters.
	/*
         for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         String values[] = request.getParameterValues(name);
         int n = values.length;
         StringBuffer buf = new StringBuffer();
         buf.append(name);
         buf.append("=");
         for(int i=0; i < n; i++) {
         buf.append(values[i]);
         if (i < n-1)
         buf.append(",");
         }
         logBeanWebReactivo(buf.toString());
         }
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            logBeanWebReactivo("FiltroLogin:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.

        // For example, a logging filter might logBeanWebReactivo the attributes on the
        // request object after the request has been processed. 
	/*
         for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
         String name = (String)en.nextElement();
         Object value = request.getAttribute(name);
         logBeanWebReactivo("attribute: " + name + "=" + value.toString());

         }
         */

        // For example, a filter might append something to the response.
	/*
         PrintWriter respOut = new PrintWriter(response.getWriter());
         respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // Obtengo el bean que representa el usuario desde el scope sesión
        UsuariosVO usuarioVO = (UsuariosVO) req.getSession().getAttribute("usuario");
        //Proceso la URL que está requiriendo el cliente  
        String urlStr = req.getRequestURL().toString().toLowerCase();
        boolean noProteger = noProteger(urlStr);
        //logBeanWebReactivo.info(urlStr + " - desprotegido=[" + noProteger + "]");
        //Si no requiere protección continúo normalmente.  
        if (noProteger(urlStr)) {
            chain.doFilter(request, response);
            return;
        }
        //El usuario no está logueado  
        if (usuarioVO == null) {
            res.sendRedirect(req.getContextPath() + "/faces/index.xhtml");
            return;
        }
        //Se verifica que el usuario pueda ingresar a esa URL
//        if(!urlPermitida(req)){
//            res.sendRedirect(req.getContextPath() + "/faces/Principal.xhtml");
//            return;
//        }
        //El recurso requiere protección, pero el usuario ya está logueado.
        chain.doFilter(request, response);

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                logBeanWebReactivo("FiltroLogin:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FiltroLogin()");
        }
        StringBuffer sb = new StringBuffer("FiltroLogin(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void logBeanWebReactivo(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    private boolean noProteger(String urlStr) {
        if (urlStr.endsWith("index.xhtml")) {
            return true;
        }
        if (urlStr.endsWith("ReactivoUsuarioInternet.xhtml")) {
            return true;
        }
        if (urlStr.endsWith("inscripcion.xhtml")) {
            return true;
        }
        if (urlStr.indexOf("/javax.faces.resource/") != -1) {
            return true;
        }
        return false;
    }

    private boolean urlPermitida(HttpServletRequest req) {
        String paginaRequerida = (String )req.getPathInfo();
        if(paginaRequerida.indexOf("Principal.xhtml") >= 0
                || paginaRequerida.indexOf(".css") >= 0){
            return true;
        }
        
        List<MenusVO> listaMenusUsuario = (List<MenusVO>) req.getSession().getAttribute("menus");
        for (MenusVO menu : listaMenusUsuario) {
            if(menu.getUrlMenu().indexOf(paginaRequerida) >= 0){
                return true;
            }
        }
        return false;
    }
    
}
