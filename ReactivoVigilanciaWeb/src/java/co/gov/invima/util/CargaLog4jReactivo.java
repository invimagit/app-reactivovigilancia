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
import java.io.File;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
//***************************************************************************
//***************************************************************************
//***************************************************************************
//***************************************************************************
//***************************************************************************
//***************************************************************************
@WebServlet(name = "CargaLog4jReactivo", urlPatterns = {"/cargalog4jreactivo"}, 
        initParams = {@WebInitParam(name = "log4jPropertiesFile",
        value = "/WEB-INF/log4j.properties")}, loadOnStartup = 1)
public class CargaLog4jReactivo extends HttpServlet 
{
    //***************************************************************************
    //***************************************************************************
    //***************************************************************************
    //***************************************************************************
    private final Logger logBeanWebReactivo = Logger.getLogger(CargaLog4jReactivo.class);
    
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        //logBeanWebReactivo.info ("*******************************************************");
        //logBeanWebReactivo.info ("*******************************************************");
        //logBeanWebReactivo.info ("*******************************************************");
        //logBeanWebReactivo.info ("SISTEMA DE GESTIÓN DE REACTIVOS QUÍMICOS - REACTIVOVIGILANCIA");
        //logBeanWebReactivo.info ("CARGA DEL logBeanWebReactivo DE APLICACIÓN");
        // Obtiene el parametro de inicio
        String log4jFile = config.getInitParameter("log4jPropertiesFile");
        //***************************************************************************
        //***************************************************************************
        // Obtiene la ruta real del archivo (ruta absoluta)
        ServletContext context = config.getServletContext();
        log4jFile = context.getRealPath(log4jFile);
        //logBeanWebReactivo.info ("Ruta del LOG4J: " + log4jFile);
        //***************************************************************************
        //***************************************************************************
        // Carga el log4j.properties si existe y sino carga BasicConfigurator
        if (new File(log4jFile).isFile()) {
            PropertyConfigurator.configure(log4jFile);
        } 
        else 
        {
            BasicConfigurator.configure();
        }
        //***************************************************************************
        //***************************************************************************
        super.init(config);
        //***************************************************************************
        //***************************************************************************
        //logBeanWebReactivo.info ("*******************************************************");
        //logBeanWebReactivo.info ("*******************************************************");
        //logBeanWebReactivo.info ("*******************************************************");
    }
    //***************************************************************************
    //***************************************************************************
    //***************************************************************************
    //***************************************************************************
}
//***************************************************************************
//***************************************************************************
//***************************************************************************
//***************************************************************************
//***************************************************************************
//***************************************************************************
