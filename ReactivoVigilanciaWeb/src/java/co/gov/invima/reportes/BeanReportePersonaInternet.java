/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.reportes;

/**
 *
 * @author jgutierrezme
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import co.gov.invima.negocio.ConsultasReactivoRemote;
import co.gov.invima.reactivo.dto.reports.PersonaInternet;
import co.gov.invima.service.ServiceLocator;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/********************************************************************/
/********************************************************************/
/********************************************************************/
/********************************************************************/
/********************************************************************/
/********************************************************************/
@ViewScoped
@ManagedBean(name="beanReportePersonaInternet")
//@RequestScoped
public class BeanReportePersonaInternet implements Serializable  
{
    //****************************************************************************************
    //****************************************************************************************
    private final static Logger logBeanWebReactivo = Logger.getLogger(BeanReportePersonaInternet.class.getName());
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    private static long serialVersionUID = -72825486496747526L;
    //****************************************************************************************
    //****************************************************************************************
    private ConsultasReactivoRemote servicioEJBReportesReactivoRemote;
    //****************************************************************************************
    //****************************************************************************************
    private java.util.Date fechaInicial;
    private java.util.Date fechaFinal;
    //****************************************************************************************
    //****************************************************************************************
    private List<PersonaInternet> datosReporteReactivo;
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    public BeanReportePersonaInternet()
    {
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("Iniciando constructor del Managed Bean Web BeanReportePersonaInternet");
        //logBeanWebReactivo.info("Conectandose al contexto");
        ServiceLocator service = new ServiceLocator();
        servicioEJBReportesReactivoRemote = service.getEJBReportesReactivo();
        //logBeanWebReactivo.info("Estado del EJB DEL REPORTE PARA EL MANAGED BEAN: " + this.servicioEJBReportesReactivoRemote);
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    @PostConstruct
    public void init() 
    {
        //***********************************************************
        //***********************************************************
        /*
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        */
        //***********************************************************
        //***********************************************************
        try
        {
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            this.setDatosReporteReactivo(new ArrayList<PersonaInternet>());
            //**************************************************************************************
            //**************************************************************************************
            //**************************************************************************************
        }
        //***********************************************************
        //***********************************************************
        catch (Exception errorinit)
        {
            //**************************************************************************************
            //logBeanWebReactivo.info("------------------------Error------------------------");
            //logBeanWebReactivo.info("No fue posible ejecutar el método init del Bean " + this.getClass().getCanonicalName() + ": " + errorinit.getMessage());
            //logBeanWebReactivo.info("------------------------Fin Error------------------------");
            errorinit.printStackTrace();
            //**************************************************************************************
        }
        //***********************************************************
        //***********************************************************
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    public void generarReporte()
    {
        String fechaIn = null;
        String fechaFin = null;
        
        try
        {
            //logBeanWebReactivo.info  ("****************************************************");
            //logBeanWebReactivo.info  ("****************************************************");
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();
            HttpSession mySession = myRequest.getSession();     
            //******************************************************************
            //******************************************************************
            //logBeanWebReactivo.info  ("****************************************************");
            //logBeanWebReactivo.info ("REQUEST = " + myRequest.getParameterMap());
            //logBeanWebReactivo.info  ("****************************************************");
            //logBeanWebReactivo.info  ("****************************************************");
            /*
            //logBeanWebReactivo.info ("FECHA 1 = " + myRequest.getParameter("formularioReportes:fechaInicial_input"));
            //logBeanWebReactivo.info ("FECHA 2 = " + myRequest.getParameter("formularioReportes:fechaFinal_input"));
            //logBeanWebReactivo.info ("FECHA 3 = " + this.getFechaInicial());
            //logBeanWebReactivo.info ("FECHA 4 = " + this.getFechaFinal());
            */
            
            fechaIn = myRequest.getParameter("frmPersonaInternet:fechaInicial_input");
            fechaFin = myRequest.getParameter("frmPersonaInternet:fechaFinal_input");
            /*
            fechaIn = this.fechaInicial;
            fechaFin = this.fechaFinal;
            */
            //logBeanWebReactivo.info ("FECHA INICIAL DEL REPORTE = " + fechaIn);
            //logBeanWebReactivo.info ("FECHA FINAL DEL REPORTE = " + fechaFin);
            //******************************************************************
            //******************************************************************
            this.setFechaInicial(new java.util.Date(fechaIn));
            this.setFechaFinal(new java.util.Date(fechaFin));
            //logBeanWebReactivo.info ("FECHA 5 = " + this.getFechaInicial());
            //logBeanWebReactivo.info ("FECHA 6 = " + this.getFechaFinal());
            //******************************************************************
            //******************************************************************
            this.setDatosReporteReactivo(servicioEJBReportesReactivoRemote.generarReportePersonaInternet(this.getFechaInicial(), this.getFechaFinal()));
            //******************************************************************
            //******************************************************************
            if (this.getDatosReporteReactivo() != null)
            {
                //logBeanWebReactivo.info ("TAMAÑO DEL REPORTE = " + this.getDatosReporteReactivo().size());
            }
            //******************************************************************
            //******************************************************************
            //logBeanWebReactivo.info  ("****************************************************");
            //logBeanWebReactivo.info  ("****************************************************");
        }
        
        catch (Exception errorobtenerIdProgramaVivienda)
        {
            //logBeanWebReactivo.info("------------------------Error------------------------");
            //logBeanWebReactivo.info("No fue posible ejecutar el método generarReporte del Bean " + this.getClass().getCanonicalName() + ": " + errorobtenerIdProgramaVivienda.getMessage());
            //logBeanWebReactivo.info("------------------------Fin Error------------------------");
            errorobtenerIdProgramaVivienda.printStackTrace();
        }
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    public void limpiarCampos()
    {
        try
        {
            //logBeanWebReactivo.info  ("****************************************************");
            //logBeanWebReactivo.info  ("****************************************************");
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();
            //logBeanWebReactivo.info ("RESETEANDO CAMPOS DEL REPORTE");
            //******************************************************************
            //******************************************************************
            this.setFechaInicial(null);
            this.setFechaFinal(null);
            this.datosReporteReactivo = null;
            //******************************************************************
            //******************************************************************
            //logBeanWebReactivo.info  ("****************************************************");
            //logBeanWebReactivo.info  ("****************************************************");
        }
        
        catch (Exception errorobtenerIdProgramaVivienda)
        {
            //logBeanWebReactivo.info("------------------------Error------------------------");
            //logBeanWebReactivo.info("No fue posible ejecutar el método limpiarCampos del Bean " + this.getClass().getCanonicalName() + ": " + errorobtenerIdProgramaVivienda.getMessage());
            //logBeanWebReactivo.info("------------------------Fin Error------------------------");
            errorobtenerIdProgramaVivienda.printStackTrace();
        }
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************

    /**
     * @return the ServicioEJBReportesReactivoRemote
     */
    public ConsultasReactivoRemote getServicioEJBReportesReactivoRemote() {
        return servicioEJBReportesReactivoRemote;
    }

    /**
     * @param ServicioEJBReportesReactivoRemote the ServicioEJBReportesReactivoRemote to set
     */
    public void setServicioEJBReportesReactivoRemote(ConsultasReactivoRemote servicioEJBReportesReactivoRemote) {
        this.servicioEJBReportesReactivoRemote = servicioEJBReportesReactivoRemote;
    }

    /**
     * @return the fechaInicial
     */
    public java.util.Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(java.util.Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public java.util.Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(java.util.Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the datosReporteReactivo
     */
    public List<PersonaInternet> getDatosReporteReactivo() {
        return datosReporteReactivo;
    }

    /**
     * @param datosReporteReactivo the datosReporteReactivo to set
     */
    public void setDatosReporteReactivo(List<PersonaInternet> datosReporteReactivo) {
        this.datosReporteReactivo = datosReporteReactivo;
    }
    
    
}
