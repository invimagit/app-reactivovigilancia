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
import co.gov.invima.BO.ReactivoBO;
import co.gov.invima.VO.UsuariosVO;
import co.gov.invima.entities.ReactivoReporteUsuario;
import co.gov.invima.entities.ReactivoSeguimiento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import co.gov.invima.negocio.ConsultasReactivoRemote;
import co.gov.invima.reactivo.dto.reports.ReporteFriarh;
import co.gov.invima.service.ServiceLocator;
import co.gov.invima.sesion.ReactivoReporteUsuarioFacade;
import co.gov.invima.sesion.ReactivoSeguimientoFacade;
import java.util.Date;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ejb.*;
/********************************************************************/
/********************************************************************/
/********************************************************************/
/********************************************************************/
/********************************************************************/
/********************************************************************/
@ViewScoped
@ManagedBean(name="beanReporteUsuario")
//@RequestScoped
public class BeanReporteUsuario implements Serializable  
{
    //****************************************************************************************
    //****************************************************************************************
    private final static Logger logBeanWebReactivo = Logger.getLogger(BeanReporteUsuario.class.getName());
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    private static long serialVersionUID = -71825486496747525L;
    //****************************************************************************************

    @EJB
    private ReactivoReporteUsuarioFacade reactivoReporteUsuarioFacade;
    @EJB
    private ReactivoSeguimientoFacade reactivoSeguimientoFacade;
    
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    private java.util.Date fechaInicial;
    private java.util.Date fechaFinal;
    //****************************************************************************************
    //****************************************************************************************
    private List<ReactivoReporteUsuario> datosReporteReactivo;
    private String idRolFuncionarioAprobador;
    private String deptoSecretaria;
    private String nitEmpresa;
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    public BeanReporteUsuario()
    {
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("Iniciando constructor del Managed Bean Web BeanReporteFriahr");
        //logBeanWebReactivo.info("Conectandose al contexto");
        //ServiceLocator service = new ServiceLocator();
        //reactivoReporteUsuarioFacade = service.getEJBReportesReactivo();
        //logBeanWebReactivo.info("Estado del EJB DEL REPORTE PARA EL MANAGED BEAN: " + this.servicioEJBReportesReactivoRemote);
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    UsuariosVO userSesion = null;
    @PostConstruct
    public void init() 
    {
        //***********************************************************
        //***********************************************************
        /*
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        */
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sesionUsuario = request.getSession();
        //***********************************************************
        //***********************************************************
        userSesion = null;
        String idRolAprobador = "";
        String idDeptoSecretaria = "";
        String nitEmpresaFabricante = "";
        
        try
        {
            userSesion = (UsuariosVO)sesionUsuario.getAttribute("usuario");
            //logBeanWebReactivo.info ("USUARIO PARA LA BUSQUEDA DE PRECOLS = " + userSesion);
            if (userSesion != null)
            {
                //logBeanWebReactivo.info ("USUARIO CORRECTO DE LA SESIÓN");
                idRolAprobador = userSesion.getIdRol();
                idDeptoSecretaria = userSesion.getCodDepart();
                nitEmpresaFabricante = String.valueOf(userSesion.getIdentificacionEmpresa());
            }
            else
            {
                //logBeanWebReactivo.info ("USUARIO DE EMERGENCIA ADMINISTRATIVO INVIMA");
                idDeptoSecretaria = "11";
                idRolAprobador = "1";
                nitEmpresaFabricante = "0";
            }
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("NOMBRE DEL REPORTANTE QUE APROBARÁ LOS REGISTROS = " + this.getNombreAprobador());
            //logBeanWebReactivo.info ("ID DEPTO USUARIO SESION = " + idDepto);
            //logBeanWebReactivo.info ("ID MUNICIPIO USUARIO SESION = " + idMunicipio);
            //logBeanWebReactivo.info ("ID ROL DEL APROBADOR DE PRECOLES = " + idRolAprobador);
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            this.setIdRolFuncionarioAprobador(idRolAprobador);
            this.setDeptoSecretaria(idDeptoSecretaria);
            this.setNitEmpresa(nitEmpresaFabricante);
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            this.setDatosReporteReactivo(new ArrayList<ReactivoReporteUsuario>());
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
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();
            HttpSession mySession = myRequest.getSession();     
            fechaIn = myRequest.getParameter("fmrRegistrosFriahr:fechaInicial_input");
            fechaFin = myRequest.getParameter("fmrRegistrosFriahr:fechaFinal_input");
            if (this.getIdRolFuncionarioAprobador().equals("1"))
            {
                this.setFechaInicial(new java.util.Date(fechaIn));
                this.setFechaFinal(new java.util.Date(fechaFin));
                logBeanWebReactivo.info ("GENERANDO CONSULTA FRIAHR COMO ADMINISTRADOR");
                this.setDatosReporteReactivo(reactivoReporteUsuarioFacade.generarRegistro(this.getFechaInicial(), this.getFechaFinal()));
            }
            else
            {
                logBeanWebReactivo.info ("GENERANDO CONSULTA FRIAHR COMO USUARIO DE FABRICANTE = " + this.getNitEmpresa());
                this.setDatosReporteReactivo(reactivoReporteUsuarioFacade.generarRegistro(this.getFechaInicial(), this.getFechaFinal()));
            }
            //Obteniendo seguimiento
            for(ReactivoReporteUsuario x : getDatosReporteReactivo())
            {
                ReactivoBO reactivoBO = new ReactivoBO();
                List <ReactivoSeguimiento> reactivoSeguimiento_lista =  reactivoBO.obtenerSeguimientosFRIARH(x.getId()+"","reporte_usuario");
                x.setSeguimiento(reactivoSeguimiento_lista);   
            }
            
        }
        
        catch (Exception errorobtenerIdProgramaVivienda)
        {
            logBeanWebReactivo.info("------------------------Error------------------------");
            logBeanWebReactivo.info("No fue posible ejecutar el método generarReporte del Bean " + this.getClass().getCanonicalName() + ": " + errorobtenerIdProgramaVivienda.getMessage());
            logBeanWebReactivo.info("------------------------Fin Error------------------------");
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
    
    public void guardar_seguimiento(ReactivoReporteUsuario x)
    {
       ReactivoSeguimiento  reactivoSeguimiento = new ReactivoSeguimiento();
       reactivoSeguimiento.setCategoria("reporte_usuario");
       reactivoSeguimiento.setIdReporte(x.getId()+"");
       reactivoSeguimiento.setFechaIngreso(new Date());
       reactivoSeguimiento.setUsuario(userSesion.getNombrePersona());
       reactivoSeguimiento.setObservacion(x.getNuevo_Seguimiento());
       reactivoSeguimientoFacade.insertar_observacion(reactivoSeguimiento);
       x.getSeguimiento().add(reactivoSeguimiento);
    }
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************


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
    public List<ReactivoReporteUsuario> getDatosReporteReactivo() {
        return datosReporteReactivo;
    }

    /**
     * @param datosReporteReactivo the datosReporteReactivo to set
     */
    public void setDatosReporteReactivo(List<ReactivoReporteUsuario> datosReporteReactivo) {
        this.datosReporteReactivo = datosReporteReactivo;
    }

    /**
     * @return the idRolFuncionarioAprobador
     */
    public String getIdRolFuncionarioAprobador() {
        return idRolFuncionarioAprobador;
    }

    /**
     * @param idRolFuncionarioAprobador the idRolFuncionarioAprobador to set
     */
    public void setIdRolFuncionarioAprobador(String idRolFuncionarioAprobador) {
        this.idRolFuncionarioAprobador = idRolFuncionarioAprobador;
    }

    /**
     * @return the deptoSecretaria
     */
    public String getDeptoSecretaria() {
        return deptoSecretaria;
    }

    /**
     * @param deptoSecretaria the deptoSecretaria to set
     */
    public void setDeptoSecretaria(String deptoSecretaria) {
        this.deptoSecretaria = deptoSecretaria;
    }

    /**
     * @return the nitEmpresa
     */
    public String getNitEmpresa() {
        return nitEmpresa;
    }

    /**
     * @param nitEmpresa the nitEmpresa to set
     */
    public void setNitEmpresa(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }
    
    
    
}
