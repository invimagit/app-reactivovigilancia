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
/**
 *
 * @author jgutierrez
 */
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
 
public class CacheControlPhaseListenerReactivo implements PhaseListener
{
    //********************************************************************
    //********************************************************************
    //********************************************************************
    private final Logger logBeanWebReactivo = Logger.getLogger(CacheControlPhaseListenerReactivo.class);
    //********************************************************************
    //********************************************************************
    //********************************************************************
    @Override
    public PhaseId getPhaseId()
    {
        return PhaseId.RENDER_RESPONSE;
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    @Override
    public void afterPhase(PhaseEvent event)
    {
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
    @Override
    public void beforePhase(PhaseEvent event)
    {
        
        try
        {
            FacesContext facesContext = event.getFacesContext();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            //logBeanWebReactivo.info ("***********************************************");
            //logBeanWebReactivo.info ("***********************************************");
            //logBeanWebReactivo.info ("RESETEANDO CACHE DE LA APLICACIÓN DE REACTIVOVIGILANCIA");
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            // Stronger according to blog comment below that references HTTP spec
            response.addHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "must-revalidate");
            // some date in the past
            response.addHeader("Expires", "0");
            //****************************************************************
            //****************************************************************
            //****************************************************************
            //logBeanWebReactivo.info ("STATUS DE CACHE PRAGMA: " + response.getHeader("Pragma"));
            //logBeanWebReactivo.info ("STATUS DE CACHE Cache-Control: " + response.getHeader("Cache-Control"));
            //logBeanWebReactivo.info ("STATUS DE CACHE Expires: " + response.getHeader("Expires"));
            //****************************************************************
            //****************************************************************
            //****************************************************************
            //logBeanWebReactivo.info ("***********************************************");
            //logBeanWebReactivo.info ("***********************************************");
        }
        
        catch (Exception errorLimpiezaCache)
        {
            //logBeanWebReactivo.info ("Error reseteando cache: "  + errorLimpiezaCache.getLocalizedMessage());
            errorLimpiezaCache.printStackTrace();
        }
    }
    //********************************************************************
    //********************************************************************
    //********************************************************************
}
/************************************************************************/
/************************************************************************/
/************************************************************************/
/************************************************************************/
/************************************************************************/
/************************************************************************/
