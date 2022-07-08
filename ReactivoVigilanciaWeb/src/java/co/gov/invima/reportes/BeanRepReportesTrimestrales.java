/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.invima.reportes;

import co.gov.invima.VO.ReactivoReporteCeroVO;
import co.gov.invima.VO.UsuariosVO;
import co.gov.invima.negocio.ConsultasReactivoRemote;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import co.gov.invima.reactivo.dto.reports.ReporteTrimestralEvento;
import co.gov.invima.service.ServiceLocator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.event.ChangeEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author jgutierrezme
 */
@ViewScoped
@ManagedBean(name = "beanRepReportesTrimestrales")
public class BeanRepReportesTrimestrales implements Serializable {

    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    private final static Logger logBeanWebReactivo = Logger.getLogger(BeanRepReportesTrimestrales.class.getName());
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    private List<ReporteTrimestralEvento> listadoReportesTrimestralesEvento;
    //****************************************************************************
    //****************************************************************************
    @EJB
    private ConsultasReactivoRemote servicioEJBReportesReactivoRemote;
    //****************************************************************************
    //****************************************************************************
    private String usuario;
    private java.util.Date fechaInicial;
    private java.util.Date fechaFinal;
    private String idRolFuncionarioAprobador;
    private String deptoSecretaria;
    private boolean editar_tabla = false;

    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    public BeanRepReportesTrimestrales() {
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("Iniciando constructor del Managed Bean Web BeanReporteReactivoCero");
        //logBeanWebReactivo.info("Conectandose al contexto");
        ServiceLocator service = new ServiceLocator();
        servicioEJBReportesReactivoRemote = service.getEJBReportesReactivo();
        //logBeanWebReactivo.info("Estado del EJB DEL REPORTE PARA EL MANAGED BEAN de BeanRepReportesTrimestrales: " + this.servicioEJBReactivoReporteCero);
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
        //logBeanWebReactivo.info  ("---------------------------------------------------->");
    }

    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    @PostConstruct
    public void init() {
        //***********************************************************
        //***********************************************************
        /*
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest)context.getExternalContext().getRequest();
         */
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession sesionUsuario = request.getSession();
        String nombreUsuario = "";
        List<ReporteTrimestralEvento> registros = null;
        //***********************************************************
        //***********************************************************
        UsuariosVO userSesion = null;
        String idRolAprobador = "";
        String idDeptoSecretaria = "";

        try {
            //**********************************************************************
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("----------------------------------------------------------------------");
            //logBeanWebReactivo.info ("----------------------------------------------------------------------");
            //logBeanWebReactivo.info ("----------------------------------------------------------------------");
            //**********************************************************************
            //**********************************************************************
            userSesion = (UsuariosVO) sesionUsuario.getAttribute("usuario");
            //logBeanWebReactivo.info ("USUARIO PARA LA BUSQUEDA DE PRECOLS = " + userSesion);
            if (userSesion != null) {
                //logBeanWebReactivo.info ("USUARIO CORRECTO DE LA SESIÓN");
                nombreUsuario = userSesion.getUsuario();
                idRolAprobador = userSesion.getIdRol();
                idDeptoSecretaria = userSesion.getCodDepart();
                this.setDeptoSecretaria(idDeptoSecretaria);
            } else {
                //logBeanWebReactivo.info ("USUARIO DE EMERGENCIA ADMINISTRATIVO INVIMA");
                nombreUsuario = "USUARIO ADMINISTRADOR INVIMA";
                idRolAprobador = "1";
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
            this.setUsuario(nombreUsuario);
            //logBeanWebReactivo.info ("USUARIO EN REQUEST = " + this.getUsuario());
            //registros = getServicioEJBReactivoReporteCero().generarConsultaReportesMasivosCero(null,null);
            this.setListadoReportesTrimestralesEvento(registros);
            //**********************************************************************
            //**********************************************************************
            //logBeanWebReactivo.info ("----------------------------------------------------------------------");
            //logBeanWebReactivo.info ("REPORTES EN CERO DEL USUARIO " + nombreUsuario + " = " + registros);
            //logBeanWebReactivo.info ("----------------------------------------------------------------------");
            //logBeanWebReactivo.info ("----------------------------------------------------------------------");
            //logBeanWebReactivo.info ("----------------------------------------------------------------------");
        } //***********************************************************
        //***********************************************************
        catch (Exception errorinit) {
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

    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    public void consultarReportesCargados() {
        List<ReactivoReporteCeroVO> registros = null;
        String nombreUsuario = "";
        //HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        try {
            //logBeanWebReactivo.info ("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            //logBeanWebReactivo.info ("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            nombreUsuario = this.getUsuario();
            //logBeanWebReactivo.info ("TRAYENDO REPORTE DEL USUARIO = " + nombreUsuario);
            //logBeanWebReactivo.info ("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            //logBeanWebReactivo.info ("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        } catch (Exception errorReportes) {
            //logBeanWebReactivo.info ("Error en consulta = " + errorReportes.getMessage());
            errorReportes.printStackTrace();
        }
    }

    public void generarReporte() {
        String fechaIn = null;
        String fechaFin = null;
        java.util.Date fechaInicialRep = null;
        java.util.Date fechaFinalRep = null;
        List<ReporteTrimestralEvento> registros = null;

        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
            fechaIn = myRequest.getParameter("frmReporteRepReportesTrimestralesEvento:fechaInicial_input");
            fechaFin = myRequest.getParameter("frmReporteRepReportesTrimestralesEvento:fechaFinal_input");
            this.setFechaInicial(new java.util.Date(fechaIn));
            this.setFechaFinal(new java.util.Date(fechaFin));
            
            if (this.getIdRolFuncionarioAprobador().equals("1")) {
                logBeanWebReactivo.info("Ejecutando consulta como superadministrador");
                registros = getServicioEJBReportesReactivoRemote().generarConsultaReportesTrimestralesConEvento(this.getFechaInicial(), this.getFechaFinal());
            } else {
                logBeanWebReactivo.info("Ejecutando consulta como secretaria de salud");
                registros = getServicioEJBReportesReactivoRemote().generarConsultaReportesTrimestralesConEventoSecretaria(this.getFechaInicial(), this.getFechaFinal(), this.getDeptoSecretaria());
            }
            this.setListadoReportesTrimestralesEvento(registros);
        } catch (Exception errorobtenerIdProgramaVivienda) {
            errorobtenerIdProgramaVivienda.printStackTrace();
        }
    }

    //****************************************************************************************
    //****************************************************************************************
    //****************************************************************************************
    public void limpiarCampos() {
        try {
            //logBeanWebReactivo.info  ("****************************************************");
            //logBeanWebReactivo.info  ("****************************************************");
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
            //logBeanWebReactivo.info ("RESETEANDO CAMPOS DEL REPORTE");
            //******************************************************************
            //******************************************************************
            this.setFechaInicial(null);
            this.setFechaFinal(null);
            this.setListadoReportesTrimestralesEvento(null);
            //******************************************************************
            //******************************************************************
            //logBeanWebReactivo.info  ("****************************************************");
            //logBeanWebReactivo.info  ("****************************************************");
        } catch (Exception errorobtenerIdProgramaVivienda) {
            //logBeanWebReactivo.info("------------------------Error------------------------");
            //logBeanWebReactivo.info("No fue posible ejecutar el método limpiarCampos del Bean " + this.getClass().getCanonicalName() + ": " + errorobtenerIdProgramaVivienda.getMessage());
            //logBeanWebReactivo.info("------------------------Fin Error------------------------");
            errorobtenerIdProgramaVivienda.printStackTrace();
        }
    }
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************
    //****************************************************************************

    /**
     * @return the servicioEJBReportesReactivoRemote
     */
    public ConsultasReactivoRemote getServicioEJBReportesReactivoRemote() {
        return servicioEJBReportesReactivoRemote;
    }

    /**
     * @param servicioEJBReportesReactivoRemote the
     * servicioEJBReportesReactivoRemote to set
     */
    public void setServicioEJBReportesReactivoRemote(ConsultasReactivoRemote servicioEJBReportesReactivoRemote) {
        this.servicioEJBReportesReactivoRemote = servicioEJBReportesReactivoRemote;
    }

    /**
     * @return the listadoReportesTrimestralesEvento
     */
    public List<ReporteTrimestralEvento> getListadoReportesTrimestralesEvento() {
        return listadoReportesTrimestralesEvento;
    }

    /**
     * @param listadoReportesTrimestralesEvento the
     * listadoReportesTrimestralesEvento to set
     */
    public void setListadoReportesTrimestralesEvento(List<ReporteTrimestralEvento> listadoReportesTrimestralesEvento) {
        this.listadoReportesTrimestralesEvento = listadoReportesTrimestralesEvento;
    }

    public boolean isEditar_tabla() {
        return editar_tabla;
    }

    public void setEditar_tabla(boolean editar_tabla) {
        this.editar_tabla = editar_tabla;
    }

    public void activar_editar_tabla(ReporteTrimestralEvento x) {
        logBeanWebReactivo.info("BeanRepReportesTrimestrales - activar_editar_tabla");
        try {
            String sDate1 = x.getFechaGestionEnte();
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
            x.setFechaGestionEnte_temp(date1);

            String sDate2 = x.getFechaGestionInvima();
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
            x.setFechaGestionInvima_temp(date2);
        } catch (Exception e) {
            logBeanWebReactivo.info("BeanRepReportesTrimestrales - activar_editar_tabla CATCH:" + e.getMessage());
            e.printStackTrace();
        }

        x.setEnEdicion(true);
    }

    public void desactivar_editar_tabla(ReporteTrimestralEvento x) {
        logBeanWebReactivo.info("BeanRepReportesTrimestrales - desactivar_editar_tabla");
        try {
            Date date = x.getFechaGestionEnte_temp();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
            logBeanWebReactivo.info("setFechaGestionEnte=" + strDate);
            x.setFechaGestionEnte(strDate);

            Date date2 = x.getFechaGestionInvima_temp();
            String strDate2 = dateFormat.format(date2);
            logBeanWebReactivo.info("setFechaGestionInvima=" + strDate2);
            x.setFechaGestionInvima(strDate2);
        } catch (Exception e) {
            logBeanWebReactivo.info("BeanRepReportesTrimestrales - desactivar_editar_tabla CATCH:" + e.getMessage());
            e.printStackTrace();
        }
        //Update
        servicioEJBReportesReactivoRemote.actualizar_REPORTE_MASIVO_TRIMESTRAL_CON_EVENTO(x);
        x.setEnEdicion(false);
    }

    public void fechaGestionEnteSelect(ChangeEvent event) {
        logBeanWebReactivo.info("BeanRepReportesTrimestrales - fechaGestionEnteSelect, event.getObject()" + event.toString());
    }
}
